/**
 * Given a string s, check if it can be constructed by taking a substring of it and appending multiple copies of the substring together.
 *
 * Constraints:
 * 1 <= s.length <= 10^4
 * s consists of lowercase English letters.
 */

fun main() {
    val s = "abab"

    val result = Solution().repeatedSubstringPattern(s)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N^2)
    // 공간 복잡도 : O(1)
    fun repeatedSubstringPattern(s: String): Boolean {
        val n = s.length
        
        for (length in 1..n / 2) {
            if (n % length != 0) continue

            val sub = s.substring(0, length)
            val times = n / length
            val built = sub.repeat(times)

            if (built == s) return true
        }

        return false
    }
}
