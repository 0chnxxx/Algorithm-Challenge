/**
 * 인천 앞바다에는 1부터 n까지 서로 다른 번호가 매겨진 등대 n개가 존재합니다.
 * 등대와 등대 사이르 오가는 뱃길이 n-1개 존재하여, 어느 등대에서 출발해도 다른 모든 등대까지 이동할 수 있습니다.
 * 등대 관리자 윤성이는 전력을 아끼기 위하여, 이 중 몇 개의 등대만 켜 두려고 합니다.
 * 하지만 등대를 아무렇게나 꺼버리면, 뱃길을 오가는 배들이 위험할 수 있습니다.
 * 한 뱃길의 양쪽 끝 등대 중 적어도 하나는 켜져 있도록 등대를 켜 두어야 합니다.
 *
 * 등대의 개수 n
 * 각 뱃길이 연결된 등대의 번호를 담은 이차원 배열 lighthouse
 * 윤성이가 켜 두어야 하는 등대 개수의 최솟값을 return하도록 solution 함수를 작성해주세요.
 *
 * 2 <= n <= 100000
 * lighthouse의 길이 = n - 1
 * lighthouse 배열의 각 행 [a, b]는 a번 등대와 b번 등대가 뱃길로 연결되어 있다는 의미입니다.
 * 1 <= a != b <= n
 */

fun main() {
    val n = 8
    val lighthouse = arrayOf(
        intArrayOf(1, 2),
        intArrayOf(1, 3),
        intArrayOf(1, 4),
        intArrayOf(1, 5),
        intArrayOf(5, 6),
        intArrayOf(5, 7),
        intArrayOf(5, 8)
    )

    val solution = Solution().solution(n, lighthouse)

    println(solution)
}

class Solution {
    fun solution(n: Int, lighthouse: Array<IntArray>): Int {
        val graph = Array(n + 1) { mutableListOf<Int>() }

        // 그래프 양방향 연결
        for ((a, b) in lighthouse) {
            graph[a].add(b)
            graph[b].add(a)
        }

        // 각 노드를 루트로 했을 때 불을 킨 경우와 키지 않은 경우에 대한 최소 등대 수
        val dp = Array(n + 1) { IntArray(2) }
        val visited = BooleanArray(n + 1)

        fun dfs(node: Int) {
            visited[node] = true
            dp[node][0] = 0 // 불이 꺼진 경우
            dp[node][1] = 1 // 불이 켜진 경우

            for (child in graph[node]) {
                if (!visited[child]) {
                    dfs(child)

                    // 현재 노드가 불이 꺼진 경우라면 자식 노드는 반드시 불이 켜져 있어야 함
                    dp[node][0] += dp[child][1]
                    // 현재 노드가 불이 켜진 경우라면 자식 노드의 점등 여부는 상관 없으나 최솟값으로 선택해야 함
                    dp[node][1] += Math.min(dp[child][0], dp[child][1])
                }
            }
        }

        // 루트 노드 지정
        dfs(1)

        // 루트 노드에 불이 켜진 경우와 꺼진 경우 중 점등한 등대의 수의 최솟값을 반환
        return Math.min(dp[1][0], dp[1][1])
    }
}
