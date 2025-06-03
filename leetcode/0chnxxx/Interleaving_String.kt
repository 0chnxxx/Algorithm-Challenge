/**
 * Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1 and s2.
 *
 * An interleaving of two strings s and t is a configuration where s and t are divided into n and m substrings respectively, such that:
 *
 * s = s1 + s2 + ... + sn
 * t = t1 + t2 + ... + tm
 * |n - m| <= 1
 * The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 + ...
 *
 * Note: a + b is the concatenation of strings a and b.
 *
 * Constraints:
 * 0 <= s1.length, s2.length <= 100
 * 0 <= s3.length <= 200
 * s1, s2, and s3 consist of lowercase English letters.
 */

fun main() {
    val s1 = "aabcc"
    val s2 = "dbbca"
    val s3 = "aadbbcbcac"

    val solution = Solution().isInterleave(s1, s2, s3)

    println(solution)
}

class Solution {
    // 시간 복잡도 : O(N * M)
    // 공간 복잡도 : O(N)
    fun isInterleave(s1: String, s2: String, s3: String): Boolean {
        if (s1.length + s2.length != s3.length) return false

        val dp = BooleanArray(s2.length + 1)

        dp[0] = true

        // dp[i][j] = (dp[i - 1][j] && s1[i - 1] == s3[i + j - 1]) || (dp[i][j - 1] && s2[j - 1] == s3[i + j - 1])
        for (i in 1..s2.length) {
            dp[i] = dp[i - 1] && s2[i - 1] == s3[i - 1]
        }

        for (i in 1..s1.length) {
            dp[0] = dp[0] && s1[i - 1] == s3[i - 1]

            for (j in 1..s2.length) {
                dp[j] = (dp[j] && s1[i - 1] == s3[i + j - 1]) || (dp[j - 1] && s2[j - 1] == s3[i + j - 1])
            }
        }

        return dp[s2.length]
    }

//    // 시간 복잡도 : O(N * M)
//    // 공간 복잡도 : O(N * M)
//    fun isInterleave(s1: String, s2: String, s3: String): Boolean {
//        if (s1.length + s2.length != s3.length) return false
//
//        val dp = Array(s1.length + 1) { BooleanArray(s2.length + 1) }
//
//        dp[0][0] = true
//
//        // dp[i][j] = (dp[i - 1][j] && s1[i - 1] == s3[i + j - 1]) || (dp[i][j - 1] && s2[j - 1] == s3[i + j - 1])
//        for (i in 0..s1.length) {
//            for (j in 0..s2.length) {
//                if (i > 0 && s1[i - 1] == s3[i + j - 1]) {
//                    dp[i][j] = dp[i][j] || dp[i - 1][j]
//                }
//
//                if (j > 0 && s2[j - 1] == s3[i + j - 1]) {
//                    dp[i][j] = dp[i][j] || dp[i][j - 1]
//                }
//            }
//        }
//
//        return dp[s1.length][s2.length]
//    }
}
