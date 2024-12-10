/**
 * 네트워크란 컴퓨터 상호 간에 정보를 교환할 수 있도록 연결된 형태를 의미합니다.
 * 예를 들어, 컴퓨터 A와 컴퓨터 B가 직접적으로 연결되어있고, 컴퓨터 B와 컴퓨터 C가 직접적으로 연결되어 있을 때 컴퓨터 A와 컴퓨터 C도 간접적으로 연결되어 정보를 교환할 수 있습니다.
 * 따라서 컴퓨터 A, B, C는 모두 같은 네트워크 상에 있다고 할 수 있습니다.
 * 컴퓨터의 개수 n, 연결에 대한 정보가 담긴 2차원 배열 computers가 매개변수로 주어질 때, 네트워크의 개수를 return 하도록 solution 함수를 작성하시오.
 *
 * 1 <= n <= 200
 * 각 컴퓨터는 0부터 n-1인 정수로 표현
 * i번 컴퓨터와 j번 컴퓨터가 연결되어 있으면 computers[i][j]를 1로 표현
 * computer[i][i]는 항상 1
 */

fun main() {
    val n = 3
    val computers = arrayOf(
        intArrayOf(1, 1, 0),
        intArrayOf(1, 1, 0),
        intArrayOf(0, 0, 1)
    )
    val solution = Solution().solution(n, computers)

    println(solution)
}

class Solution {
    fun solution(n: Int, computers: Array<IntArray>): Int {
        val visited = BooleanArray(n)
        var count = 0

        fun dfs(node: Int) {
            visited[node] = true

            for (neighbor in 0 until n) {
                if (computers[node][neighbor] == 1 && !visited[neighbor]) {
                    dfs(neighbor)
                }
            }
        }

        for (i in 0 until n) {
            if (!visited[i]) {
                dfs(i)
                count++
            }
        }

        return count
    }
}
