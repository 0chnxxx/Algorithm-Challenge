/**
 * Given an integer columnNumber, return its corresponding column title as it appears in an Excel sheet.
 *
 * For example:
 *
 * A -> 1
 * B -> 2
 * C -> 3
 * ...
 * Z -> 26
 * AA -> 27
 * AB -> 28
 * ...
 *
 * Constraints:
 * 1 <= columnNumber <= 2^31 - 1
 */

fun main() {
    val columnNumber = 28

    val result = Solution().convertToTitle(columnNumber)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(log(columnNumber))
    // 공간 복잡도 : O(log(columnNumber))
    fun convertToTitle(columnNumber: Int): String {
        var number = columnNumber
        val sb = StringBuilder()

        while (number > 0) {
            number--

            val remainder = number % 26

            sb.append(('A'.code + remainder).toChar())

            number /= 26
        }

        return sb.reverse().toString()
    }
}