/**
 * Given two strings word1 and word2, return the minimum number of steps required to make word1 and word2 the same.
 * In one step, you can delete exactly one character in either string.
 *
 * Constraints:
 * 1 <= word1.length, word2.length <= 500
 * word1 and word2 consist of only lowercase English letters.
 */

fun main() {
    val word1 = "sea"
    val word2 = "eat"

    val result = Solution().minDistance(word1, word2)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(M * N)
    // 공간 복잡도 : O(M * N)
    fun minDistance(word1: String, word2: String): Int {
        val m = word1.length
        val n = word2.length

        val dp = Array(m + 1) { IntArray(n + 1) }

        for (i in 1..m) {
            for (j in 1..n) {
                if (word1[i - 1] == word2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1
                } else {
                    dp[i][j] = maxOf(dp[i - 1][j], dp[i][j - 1])
                }
            }
        }

        val lcs = dp[m][n]

        return (m - lcs) + (n - lcs)
    }
}