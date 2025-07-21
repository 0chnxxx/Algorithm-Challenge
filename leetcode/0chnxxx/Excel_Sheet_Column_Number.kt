/**
 * Given a string columnTitle that represents the column title as appears in an Excel sheet, return its corresponding column number.
 *
 * For example:
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
 * 1 <= columnTitle.length <= 7
 * columnTitle consists only of uppercase English letters.
 * columnTitle is in the range ["A", "FXSHRXW"].
 */

fun main() {
    val columnTitle = "AB"

    val result = Solution().titleToNumber(columnTitle)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun titleToNumber(columnTitle: String): Int {
        var number = 0

        for (title in columnTitle) {
            val value = title - 'A' + 1

            // 한 자리 왼쪽으로 밀고 새로운 value를 더함
            number = number * 26 + value
        }

        return number
    }
}