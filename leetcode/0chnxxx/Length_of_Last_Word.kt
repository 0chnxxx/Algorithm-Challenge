/**
 * Given a string s consisting of words and spaces, return the length of the last word in the string.
 *
 * A word is a maximal substring consisting of non-space characters only.
 *
 * 1 <= s.length <= 104
 * s consists of only English letters and spaces ' '.
 * There will be at least one word in s.
 */

fun main() {
    val s = "Hello world"

    val solution = Solution().lengthOfLastWord(s)

    println(solution)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun lengthOfLastWord(s: String): Int {
        var result = 0
        var index = s.length - 1

        while (index >= 0 && s[index] == ' ') {
            index--
        }

        while (index >= 0 && s[index] != ' ') {
            result++
            index--
        }

        return result
    }

//    // 시간 복잡도 : O(N)
//    // 공간 복잡도 : O(N)
//    fun lengthOfLastWord(s: String): Int {
//        val result = StringBuilder()
//        val trimmedString = s.trim()
//
//        for (i in trimmedString.length - 1 downTo 0) {
//            val char = trimmedString[i]
//
//            if (char != ' ') {
//                result.append(char)
//            } else {
//                break
//            }
//        }
//
//        return result.length
//    }
}
