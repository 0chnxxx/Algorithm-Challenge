/**
 * Given an input string s, reverse the order of the words.
 * A word is defined as a sequence of non-space characters.
 * The words in s will be separated by at least one space.
 * Return a string of the words in reverse order concatenated by a single space.
 * Note that s may contain leading or trailing spaces or multiple spaces between two words.
 * The returned string should only have a single space separating the words.
 * Do not include any extra spaces.
 *
 * Constraints:
 * 1 <= s.length <= 10^4
 * s contains English letters (upper-case and lower-case), digits, and spaces ' '.
 * There is at least one word in s.
 *
 * Follow-up:
 * If the string data type is mutable in your language, can you solve it in-place with O(1) extra space?
 */

fun main() {
    val s = "  hello world  "

    val result = Solution().reverseWords(s)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun reverseWords(s: String): String {
        val chars = s.toCharArray()
        val n = chars.size

        fun reverse(arr: CharArray, start: Int, end: Int) {
            var i = start
            var j = end

            while (i < j) {
                val tmp = arr[i]

                arr[i] = arr[j]
                arr[j] = tmp
                i++
                j--
            }
        }

        // 전체 문자 뒤집기
        reverse(chars, 0, n - 1)

        // 단어별로 문자 뒤집기
        var start = 0

        while (start < n) {
            if (chars[start] == ' ') {
                start++
                continue
            }

            var end = start

            while (end < n && chars[end] != ' ') end++

            reverse(chars, start, end - 1)
            start = end
        }

        // 공백 정리
        val sb = StringBuilder()
        var i = 0

        while (i < n) {
            while (i < n && chars[i] == ' ') i++
            if (i >= n) break

            if (sb.isNotEmpty()) sb.append(' ')

            while (i < n && chars[i] != ' ') {
                sb.append(chars[i])
                i++
            }
        }

        return sb.toString()
    }

//    // 시간 복잡도 : O(N)
//    // 공간 복잡도 : O(N)
//    fun reverseWords(s: String): String {
//        return s.trim()
//            .split("\\s+".toRegex())
//            .reversed()
//            .joinToString(" ")
//    }
}
