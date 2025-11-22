/**
 * Given a string s which consists of lowercase or uppercase letters, return the length of the longest palindrome that can be built with those letters.
 * Letters are case sensitive, for example, "Aa" is not considered a palindrome.
 *
 * Constraints:
 * 1 <= s.length <= 2000
 * s consists of lowercase and/or uppercase English letters only.
 */

fun main() {
    val s = "abccccdd"

    val result = Solution().longestPalindrome(s)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun longestPalindrome(s: String): Int {
        val count = IntArray(128)

        for (c in s) {
            count[c.code]++
        }

        var result = 0
        var hasOdd = false

        for (c in count) {
            if (c % 2 == 0) {
                result += c
            } else {
                result += c - 1
                hasOdd = true
            }
        }

        return if (hasOdd) result + 1 else result
    }
}
