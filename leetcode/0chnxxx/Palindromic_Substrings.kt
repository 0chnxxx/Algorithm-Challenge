/**
 * Given a string s, return the number of palindromic substrings in it.
 * A string is a palindrome when it reads the same backward as forward.
 * A substring is a contiguous sequence of characters within the string.
 *
 * Constraints:
 * 1 <= s.length <= 1000
 * s consists of lowercase English letters.
 */

fun main() {
    val s = "abc"

    val result = Solution().countSubstrings(s)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N^2)
    // 공간 복잡도 : O(1)
    fun countSubstrings(s: String): Int {
        var count = 0

        for (i in s.indices) {
            count += expand(s, i, i)     // 홀수 길이
            count += expand(s, i, i + 1) // 짝수 길이
        }

        return count
    }

    private fun expand(s: String, left: Int, right: Int): Int {
        var l = left
        var r = right
        var cnt = 0

        while (l >= 0 && r < s.length && s[l] == s[r]) {
            cnt++
            l--
            r++
        }

        return cnt
    }
}