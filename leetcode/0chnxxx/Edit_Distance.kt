/**
 * Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.
 *
 * You have the following three operations permitted on a word:
 *
 * Insert a character
 * Delete a character
 * Replace a character
 *
 * Constraints:
 * 0 <= word1.length, word2.length <= 500
 * word1 and word2 consist of lowercase English letters.
 */

fun main() {
    val word1 = "horse"
    val word2 = "ros"

    val solution = Solution().minDistance(word1, word2)

    println(solution)
}

class Solution {
    // 시간 복잡도 : O(N * M)
    // 공간 복잡도 : O(N * M)
    fun minDistance(word1: String, word2: String): Int {
        val n = word1.length
        val m = word2.length

        val dp = Array(n + 1) { IntArray(m + 1) }

        // word2 초기값 세팅
        for (i in 0..m) {
            dp[0][i] = i
        }

        // word1 초기값 세팅
        for (i in 0..n) {
            dp[i][0] = i
        }

        // word1과 word2의 앞 문자부터 탐색
        for (i in 1..n) {
            for (j in 1..m) {
                // 같은 문자라면
                if (word1[i - 1] == word2[j - 1]) {
                    // 그대로 사용
                    dp[i][j] = dp[i - 1][j - 1]
                // 같은 문자가 아니라면
                } else {
                    // 삽입, 교체, 삭제 중 최소한의 변경을 선택
                    // 각각의 연산을 수행하기 위한 이전 상태에 + 1 을 하여 연산 횟수 추가
                    dp[i][j] = minOf(
                        dp[i][j - 1] + 1,  // 삽입을 위해선 word1 위치는 유지 word2 위치를 이전 상태로
                        dp[i - 1][j - 1] + 1, // 교체를 위해선 word1 위치와 word2 위치를 둘 다 이전 상태로
                        dp[i - 1][j] + 1 // 삭제를 위해선 word1 위치를 이전 상태로 하고 word2 위치는 유지
                    )
                }
            }
        }

        // 최종 연산 횟수 반환
        return dp[n][m]
    }
}
