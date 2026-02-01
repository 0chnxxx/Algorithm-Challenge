/**
 * Given a string s, find the longest palindromic subsequence's length in s.
 * A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without changing the order of the remaining elements.
 *
 * Constraints:
 * 1 <= s.length <= 1000
 * s consists only of lowercase English letters.
 */

fun main() {
    val s = "bbbab"

    val result = Solution().longestPalindromeSubseq(s)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N^2)
    // 공간 복잡도 : O(N^2)
    fun longestPalindromeSubseq(s: String): Int {
        val n = s.length
        val dp = Array(n) { IntArray(n) }

        for (i in 0 until n) {
            dp[i][i] = 1
        }

        for (len in 2..n) {
            for (i in 0..n - len) {
                val j = i + len - 1

                if (s[i] == s[j]) {
                    dp[i][j] = if (len == 2) 2 else dp[i + 1][j - 1] + 2
                } else {
                    dp[i][j] = maxOf(dp[i + 1][j], dp[i][j - 1])
                }
            }
        }

        return dp[0][n - 1]
    }
}
