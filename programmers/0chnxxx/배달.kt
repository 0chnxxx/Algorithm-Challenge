/**
 * N개의 마을로 이루어진 나라가 있습니다.
 * 이 나라의 각 마을에는 1부터 N까지의 번호가 각각 하나씩 부여되어 있습니다.
 * 각 마을은 양방향으로 통행할 수 있는 도로로 연결되어 있는데,
 * 서로 다른 마을 간에 이동할 때는 이 도로를 지나야 합니다.
 * 도로를 지날 때 걸리는 시간은 도로별로 다릅니다.
 * 현재 1번 마을에 있는 음식점에서 각 마을로 음식 배달을 하려고 합니다.
 * 각 마을로부터 음식 주문을 받으려고 하는데, N개의 마을 중에서 K 시간 이하로 배달이 가능한 마을에서만 주문을 받으려고 합니다.
 *
 * 마을의 개수 N, 각 마을을 연결하는 도로의 정보 road, 음식 배달이 가능한 시간 K가 매개변수로 주어질 때,
 * 음식 주문을 받을 수 있는 마을의 개수를 return 하도록 solution 함수를 완성해주세요.
 *
 * 1 <= N <= 50
 * 1 <= road의 길이 <= 2000
 * 1 <= k <= 500000
 *
 * road는 (a, b, c)이며 a, b는 도로가 연결하는 두 마을의 번호, c는 도로를 지나는데 걸리는 시간입니다.
 */

fun main() {
    val n = 5
    val road = arrayOf(
        intArrayOf(1, 2, 1),
        intArrayOf(2, 3, 3),
        intArrayOf(5, 2, 2),
        intArrayOf(1, 4, 2),
        intArrayOf(5, 3, 1),
        intArrayOf(5, 4, 2)
    )
    val k = 3

    val result = Solution().solution(n, road, k)

    println(result)
}

class Solution {
    fun solution(n: Int, road: Array<IntArray>, k: Int): Int {
        // 양방향 그래프 탐색을 위한 배열
        val villages = Array(n + 1) { mutableSetOf<Pair<Int, Int>>() }

        // 양방향 그래프의 간선 연결
        for ((from, to, time) in road) {
            villages[from].add(Pair(to, time))
            villages[to].add(Pair(from, time))
        }

        // 마을 간 거리 및 방문 여부
        val distances = IntArray(n + 1) { Int.MAX_VALUE }
        val visited = BooleanArray(n + 1) { false }

        distances[1] = 0

        // 다익스트라 알고리즘
        for (i in 1..n) {
            // 마을을 순회하며 방문하지 않은 마을 중 최소 거리 선택
            // (PriorityQueue로 대체 가능)
            var minDistance = Int.MAX_VALUE
            var currentVillage = -1

            for (village in 1..n) {
                if (!visited[village] && distances[village] < minDistance) {
                    minDistance = distances[village]
                    currentVillage = village
                }
            }

            // 방문할 수 있는 마을이 없다면 종료 포인트
            if (currentVillage == -1) {
                break
            }

            // 찾아낸 최소 거리 마을을 방문 처리
            visited[currentVillage] = true

            // 인접 마을까지의 거리 업데이트
            for ((nextVillage, travelTime) in villages[currentVillage]) {
                val newDistance = distances[currentVillage] + travelTime

                if (newDistance < distances[nextVillage]) {
                    distances[nextVillage] = newDistance
                }
            }
        }

        // 1번 마을부터 모든 마을까지의 거리를 누적으로 저장하기 때문에
        // k 이하 거리의 마을들의 갯수를 세면 됨
        return distances.count { it <= k }
    }
}
