/**
 * Given a string s and an integer k, return the length of the longest substring of s such that the frequency of each character in this substring is greater than or equal to k.
 * if no such substring exists, return 0.
 *
 * Constraints:
 * 1 <= s.length <= 10^4
 * s consists of only lowercase English letters.
 * 1 <= k <= 10^5
 */

fun main() {
    val s = "aaabb"
    val k = 3

    val result = Solution().longestSubstring(s, k)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(N)
    fun longestSubstring(s: String, k: Int): Int {
        if (s.length < k) {
            return 0
        }

        val frequency = IntArray(26)

        for (c in s) {
            frequency[c - 'a']++
        }

        for (i in s.indices) {
            if (frequency[s[i] - 'a'] in 1 until k) {
                val left = longestSubstring(s.substring(0, i), k)
                val right = longestSubstring(s.substring(i + 1), k)

                return maxOf(left, right)
            }
        }

        return s.length
    }
}