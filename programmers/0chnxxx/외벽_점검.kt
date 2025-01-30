/**
 * 레스토랑을 운영하고 있는 스카피는 레스토랑 내부가 너무 낡아 친구들과 함께 직접 리모델링 하기로 했습니다.
 * 레스토랑이 있는 곳은 스노우타운으로 매우 추운 지역이어서 내부 공사를 하는 도중에 주기적으로 외벽의 상태를 점검해야 할 필요가 있습니다.
 *
 * 레스토랑의 구조는 완전히 동그란 모양이고 외벽의 총 둘레는 n미터이며, 외벽의 몇몇 지점은 추위가 심할 경우 손상될 수도 있는 취약한 지점들이 있습니다.
 * 따라서 내부 공사 도중에도 외벽의 취약 지점들이 손상되지 않았는지, 주기적으로 친구들을 보내서 점검을 하기로 했습니다.
 * 다만, 빠른 공사 진행을 위해 점검 시간을 1시간으로 제한했습니다.
 * 친구들이 1시간동안 이동할 수 있는 거리는 제각각이기 때문에, 최소한의 친구들을 투입해 취약 지점을 점검하고 나머지 친구들은 내부 공사를 돕도록 하려고 합니다.
 * 편의상 레스토랑의 정북 방향 지점을 0으로 나타내며, 취약 지점의 위치는 정북 방향 지점으로부터 시계 방향으로 떨어진 거리로 나타냅니다.
 * 또 친구들은 출발 지점부터 시계, 혹은 반시계 방향으로 외벽을 따라서만 이동합니다.
 *
 * 외벽의 길이 n
 * 취약 지점의 위치가 담긴 배열 weak
 * 각 친구가 1시간 동안 이동할 수 있는 거리가 담긴 배열 dist
 *
 * 취약 지점을 점검하기 위해 보내야 하는 친구 수의 최소값을 return 하도록 solution 함수를 완성해주세요.
 * 친구들을 모두 투입해도 취약 지점을 전부 점검할 수 없는 경우에는 -1을 return 해주세요.
 *
 * 1 <= n <= 200
 * 1 <= weak의 길이 <= 15
 * 0 <= weak의 원소 <= n - 1
 * 취약 지점의 위치는 오름차순으로 정렬되어 주어집니다.
 * 1 <= dist의 길이 <= 8
 * 1 <= dist의 원소 <= 100
 */

fun main() {
    val n = 12
    val weak = intArrayOf(1, 5, 6, 10)
    val dist = intArrayOf(1, 2, 3, 4)

    val solution = Solution().solution(n, weak, dist)

    println(solution)
}

class Solution {
    fun solution(n: Int, weak: IntArray, dist: IntArray): Int {
        // 원형을 펼쳐서 한 방향으로 탐색할 수 있도록 배열을 늘림
        val weaks = weak + weak.map { it + n }
        // 친구들이 이동할 수 있는 거리를 내림차순 후 순열을 생성
        val distances = dist.sortedDescending().toList().permutations()
        var min = Int.MAX_VALUE

        for (distance in distances) {
            for (i in 0 until weak.size) {
                var count = 1
                var position = weaks[i] + distance[0]

                for (j in i until i + weak.size) {
                    if (weaks[j] > position) {
                        count++

                        if (count > distance.size) {
                            break
                        }

                        position = weaks[j] + distance[count - 1]
                    }
                }

                min = Math.min(min, count)
            }
        }

        return if (min > dist.size) {
            -1
        } else {
            min
        }
    }

    private fun List<Int>.permutations(): List<List<Int>> {
        if (size == 1) {
            return listOf(this)
        }

        val result = mutableListOf<List<Int>>()

        for (i in indices) {
            val remaining = this.toMutableList().apply { removeAt(i) }

            for (perm in remaining.permutations()) {
                result.add(listOf(this[i]) + perm)
            }
        }

        return result
    }
}
