import kotlin.math.abs

/**
 * n개의 송전탑이 전선을 통해 하나의 트리 형태로 연결되어 있습니다.
 * 당신은 이 전선들 중 하나를 끊어서 현재의 전력망 네트워크를 2개로 분할하려고 합니다.
 * 이때, 두 전력망이 갖게 되는 송전탑의 개수를 최대한 비슷하게 맞추고자 합니다.
 * 송전탑의 개수 n, 그리고 전선 정보 wires가 매개변수로 주어집니다.
 * 전선들 중 하나를 끊어서 송전탑 개수가 가능한 비슷하도록 두 전력망으로 나누었을 때, 두 전력망이 가지고 있는 송전탑 개수의 차이(절대값)를 return하도록 solution 함수를 완성해주세요.
 *
 * 2 <= n <= 100
 * wires는 길이가 n-1인 정수형 2차원 배열
 * wires의 각 원소는 [v1, v2] 2개의 자연수로 이루어져 있으며, 이는 전력망에서 v1번 송전탑과 v2번 송전탑이 전선으로 연결되어 있다는 것을 의미합니다.
 * 1 <= v1 <= v2 <= n
 * 전력망 네트워크가 하나의 트리 형태가 아닌 경우는 입력으로 주어지지 않는다.
 */

fun main() {
    val n = 9
    val wires = arrayOf(
        intArrayOf(1, 3),
        intArrayOf(2, 3),
        intArrayOf(3, 4),
        intArrayOf(4, 5),
        intArrayOf(4, 6),
        intArrayOf(4, 7),
        intArrayOf(7, 8),
        intArrayOf(7, 9)
    )

    val result = Solution().solution(n, wires)

    println(result)
}

class Solution {
    fun solution(n: Int, wires: Array<IntArray>): Int {
        var min = Int.MAX_VALUE

        for (i in wires.indices) {
            val graph = Array(n + 1) { mutableListOf<Int>() }

            for (j in wires.indices) {
                if (i == j) {
                    continue
                }

                val (from, to) = wires[j]

                graph[from].add(to)
                graph[to].add(from)
            }

            val visited = BooleanArray(n + 1)

            fun dfs(current: Int): Int {
                var count = 1

                visited[current] = true

                for (neighbor in graph[current]) {
                    if (!visited[neighbor]) {
                        count += dfs(neighbor)
                    }
                }

                return count
            }

            val count1 = dfs(1)
            val count2 = n - count1

            min = Math.min(min, Math.abs(count1 - count2))
        }

        return min
    }
}
