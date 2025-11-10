/**
 * Given two strings s and t, return true if s is a subsequence of t, or false otherwise.
 * A subsequence of a string is a new string that is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters.
 * (i.e., "ace" is a subsequence of "abcde" while "aec" is not).
 *
 * Constraints:
 * 0 <= s.length <= 100
 * 0 <= t.length <= 10^4
 * s and t consist only of lowercase English letters.
 *
 * Follow up:
 * Suppose there are lots of incoming s, say s1, s2, ..., sk where k >= 109, and you want to check one by one to see if t has its subsequence.
 * In this scenario, how would you change your code?
 */

fun main() {
    val s = "abc"
    val t = "ahbgdc"

    val result = Solution().isSubsequence(s, t)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 ; O(1)
    fun isSubsequence(s: String, t: String): Boolean {
        var i = 0
        var j = 0

        while (i < s.length && j < t.length) {
            if (s[i] == t[j]) {
                i++
            }

            j ++
        }

        return i == s.length
    }
}
