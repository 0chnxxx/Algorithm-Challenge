/**
 * Given a string s, reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.
 *
 * Constraints:
 * 1 <= s.length <= 5 * 10^4
 * s contains printable ASCII characters.
 * s does not contain any leading or trailing spaces.
 * There is at least one word in s.
 * All the words in s are separated by a single space.
 */

fun main() {
    val s = "Let's take LeetCode contest"

    val result = Solution().reverseWords(s)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(N)
    fun reverseWords(s: String): String {
        val chars = s.toCharArray()
        var start = 0

        for (i in chars.indices) {
            if (chars[i] == ' ') {
                reverse(chars, start, i - 1)
                start = i + 1
            }
        }

        reverse(chars, start, chars.lastIndex)

        return String(chars)
    }

    private fun reverse(chars: CharArray, left: Int, right: Int) {
        var l = left
        var r = right

        while (l < r) {
            val temp = chars[l]
            chars[l] = chars[r]
            chars[r] = temp
            l++
            r--
        }
    }
}
