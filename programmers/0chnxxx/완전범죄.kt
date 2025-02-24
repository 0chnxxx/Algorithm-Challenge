/**
 * A도둑과 B도둑이 팀을 이루어 모든 물건을 훔치려고 합니다.
 * 단, 각 도둑이 물건을 훔칠 때 남기는 흔적이 누적되면 경찰에 붙잡히기 때문에, 두 도둑 중 누구도 경찰에 붙잡히지 않도록 흔적을 최소화해야 합니다.
 *
 * 물건을 훔칠 때 조건은 아래와 같습니다.
 * - 물건 i를 훔칠 때,
 *  - A도둑이 훔치면 info[i][0]개의 A에 대한 흔적을 남깁니다.
 *  - B도둑이 훔치면 info[i][1]개의 B에 대한 흔적을 남깁니다.
 * - 각 물건에 대해 A도둑과 B도둑이 남기는 흔적의 개수는 1 이상 3 이하입니다.
 *
 * 경찰에 붙잡히는 조건은 아래와 같습니다.
 * - A도둑은 자신이 남긴 흔적의 누적 수가 n개 이상이면 경찰에 붙잡힙니다.
 * - B도둑은 자신이 남긴 흔적의 누적 수가 m개 이상이면 경찰에 붙잡힙니다.
 *
 * 각 물건을 훔칠 때 생기는 흔적에 대한 정보를 담은 2차원 정수 배열 info
 * A도둑이 경찰에 붙잡히는 최소 흔적 개수를 나타내는 정수 n
 * B도둑이 경찰에 붙잡히는 최소 흔적 개수를 나타내는 정수 m
 *
 * 두 도둑 모두 경찰에 붙잡히지 않도록 모든 물건을 훔쳤을 때
 * A 도둑이 남긴 흔적의 누적 개수의 최솟값을 return하도록 solution 함수를 완성해 주세요.
 * 만약 어떠한 방법으로도 두 도둑 모두 경찰에 붙잡히지 않게 할 수 없다면 -1을 return 해주세요.
 *
 * 1 <= info의 길이 <= 40
 * info[i]는 물건 i를 훔칠 때 생기는 흔적의 개수를 나타내며, [A에 대한 흔적 개수, B에 대한 흔적 개수]의 형태입니다.
 * 1 <= 흔적 개수 <= 3
 * 1 <= n <= 120
 * 1 <= m <= 120
 */

fun main() {
    val info = arrayOf(
        intArrayOf(1, 2),
        intArrayOf(2, 3),
        intArrayOf(2, 1)
    )
    val n = 4
    val m = 4

    val solution = Solution().solution(info, n, m)

    println(solution)
}

class Solution {
    fun solution(info: Array<IntArray>, n: Int, m: Int): Int {
        // 모든 경우에 대해 A의 누적 흔적 수를 기억
        val dp = Array(info.size) { Array(n) { IntArray(m) { -1 } } }

        fun dfs(i: Int, a: Int, b: Int): Int {
            // 경찰에게 붙잡히는 경우 가지 치기
            if (a >= n || b >= m) return Int.MAX_VALUE
            // 모든 물건을 훔친 경우 가지 제한
            if (i == info.size) return a
            // 이미 탐색한 가지인 경우 중복 방지
            if (dp[i][a][b] != -1) return dp[i][a][b]

            val (evidenceA, evidenceB) = info[i]

            // A가 훔치는 경우, B가 훔치는 경우 나눠서 가지 뻗기
            val pickA = dfs(i + 1, a + evidenceA, b)
            val pickB = dfs(i + 1, a, b + evidenceB)

            // A의 누적 흔적 수가 더 적은 경우를 기억
            dp[i][a][b] = Math.min(pickA, pickB)

            return dp[i][a][b]
        }

        val result = dfs(0, 0, 0)

        // 모든 물건을 훔칠 수 없는 경우 -1
        return if (result == Int.MAX_VALUE) -1 else result
    }
}
