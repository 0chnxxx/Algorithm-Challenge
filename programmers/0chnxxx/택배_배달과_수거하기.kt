/**
 * 당신은 일렬로 나열된 n개의 집에 택배를 배달하려 합니다.
 * 배달할 물건은 모두 크기가 같은 재활용 택배 상자에 담아 배달하며, 배달을 다니면서 빈 재활용 택배 상자들을 수거하려 합니다.
 * 배달할 택배들은 모두 재활용 택배 상자에 담겨서 물류창고에 보관되어 있고, i번째 집은 물륯아고에서 거리 i만큼 떨어져 있습니다.
 * 또한 i번째 집은 j번째 집과 거리 j - i만큼 떨어져 있습니다. (1 <= i <= j <= n)
 * 트럭에는 재활용 택배 상자를 최대 cap개 실을 수 있습니다.
 * 트럭은 배달할 재활용 택배 상자들을 실어 물류창고에서 출발해 각 집에 배달하면서, 빈 재활용 택배 상자들을 수거해 물류창고에 내립니다.
 * 각 집마다 배달할 재활용 택배 상자들을 실어 물류창고에서 출발해 각 집에 배달하면서, 빈 재활용 택배 상자들을 수거해 물류창고에 내립니다.
 * 각 집마다 배달할 재활용 택배 상자의 개수와 수거할 빈 재활용 택배 상자의 개수를 알고 있을 때, 트럭 하나로 모든 배달과 수거를 마치고 물류창고까지 돌아올 수 있는 최소 이동 거리를 구하려 합니다.
 * 각 집에 배달 및 수거할 때, 원하는 개수만큼 택배를 배달 및 수거할 수 있습니다.
 *
 * 트럭에 실을 수 있는 재활용 택배 상자의 최대 개수를 나타내는 정수 cap
 * 배달할 집의 개수를 나타내는 정수 n
 * 각 집에 배달할 재활용 택배 상자의 개수를 담은 1차원 정수 배열 deliveries
 * 각 집에서 수거할 빈 재활용 택배 상자의 개수를 담은 1차원 정수 배열 pickups
 *
 * 이때, 트럭 하나로 모든 배달과 수거를 마치고 물류창고까지 돌아올 수 있는 최소 이동 거리를 return하도록 solution 함수를 완성해 주세요.
 *
 * 1 <= cap <= 50
 * 1 <= n <= 100000
 * deliveries의 길이 = pickups의 길이 = n
 * 0 <= deliveries의 원소 <= 50
 * 0 <= pickups의 원소 <= 50
 * 트럭의 초기 위치는 물류창고입니다.
 */

fun main() {
    val cap = 4
    val n = 5
    val deliveries = intArrayOf(1, 0, 3, 1, 2)
    val pickups = intArrayOf(0, 3, 0, 4, 0)

    val solution = Solution().solution(cap, n, deliveries, pickups)

    println(solution)
}

class Solution {
    fun solution(cap: Int, n: Int, deliveries: IntArray, pickups: IntArray): Long {
        var distance = 0L
        var i = n - 1

        fun work(cap: Int, items: IntArray, index: Int) {
            var i = index
            var capUsed = cap

            // 배달 또는 수거 처리
            while (i >= 0) {
                if (capUsed >= items[i]) {
                    capUsed -= items[i]
                    items[i] = 0
                } else {
                    items[i] -= capUsed
                    break // 더 이상 배달이나 수거가 불가능하면 멈춤
                }

                i--
            }
        }

        // 배달 및 수거가 모두 완료될 때까지 반복
        while (i >= 0) {
            // 배달할 물건과 수거할 물건이 모두 없으면 스킵
            if (deliveries[i] == 0 && pickups[i] == 0) {
                i--
                continue
            }

            // 배달 및 수거 작업 수행
            work(cap, deliveries, i)
            work(cap, pickups, i)

            // 배달과 수거가 같이 이루어지므로 (이동 거리 * 2)가 총 이동 거리
            distance += (i + 1) * 2
        }

        return distance
    }
}
