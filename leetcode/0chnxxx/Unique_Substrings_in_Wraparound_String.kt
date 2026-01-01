/**
 * We define the string base to be the infinite wraparound string of "abcdefghijklmnopqrstuvwxyz", so base will look like this:
 * "...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd....".
 * Given a string s, return the number of unique non-empty substrings of s are present in base.
 *
 * Constraints:
 * 1 <= s.length <= 10^5
 * s consists of lowercase English letters.
 */

fun main() {
    val s = "a"

    val result = Solution().findSubstringInWraproundString(s)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun findSubstringInWraproundString(s: String): Int {
        val maxLength = IntArray(26)
        var currentLength = 0

        fun isNext(a: Char, b: Char): Boolean {
            return (a == 'z' && b == 'a') || (b - a == 1)
        }

        for (i in s.indices) {
            if (i > 0 && isNext(s[i - 1], s[i])) {
                currentLength++
            } else {
                currentLength = 1
            }

            val idx = s[i] - 'a'

            maxLength[idx] = maxOf(maxLength[idx], currentLength)
        }

        return maxLength.sum()
    }
}
