/**
 * Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.
 * In other words, return true if one of s1's permutations is the substring of s2.
 *
 * Constraints:
 * 1 <= s1.length, s2.length <= 10^4
 * s1 and s2 consist of lowercase English letters.
 */

fun main() {
    val s1 = "ab"
    val s2 = "eidbaooo"

    val result = Solution().checkInclusion(s1, s2)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun checkInclusion(s1: String, s2: String): Boolean {
        if (s1.length > s2.length) return false

        val target = IntArray(26)
        val window = IntArray(26)

        for (c in s1) {
            target[c - 'a']++
        }

        val size = s1.length

        for (i in s2.indices) {
            window[s2[i] - 'a']++

            if (i >= size) {
                window[s2[i - size] - 'a']--
            }

            if (target.contentEquals(window)) {
                return true
            }
        }

        return false
    }
}
