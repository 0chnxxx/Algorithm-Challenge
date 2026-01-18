/**
 * You are given a license key represented as a string s that consists of only alphanumeric characters and dashes.
 * The string is separated into n + 1 groups by n dashes. You are also given an integer k.
 * We want to reformat the string s such that each group contains exactly k characters, except for the first group, which could be shorter than k but still must contain at least one character.
 * Furthermore, there must be a dash inserted between two groups, and you should convert all lowercase letters to uppercase.
 * Return the reformatted license key.
 *
 * Constraints:
 * 1 <= s.length <= 10^5
 * s consists of English letters, digits, and dashes '-'.
 * 1 <= k <= 10^4
 */

fun main() {
    val s = "5F3Z-2e-9-w"
    val k = 4

    val result = Solution().licenseKeyFormatting(s, k)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(N)
    fun licenseKeyFormatting(s: String, k: Int): String {
        val sb = StringBuilder()
        var count = 0

        for (i in s.length - 1 downTo 0) {
            val c = s[i]

            if (c == '-') continue

            if (count == k) {
                sb.append('-')
                count = 0
            }

            sb.append(c.uppercaseChar())
            count++
        }

        return sb.reverse().toString()
    }
}