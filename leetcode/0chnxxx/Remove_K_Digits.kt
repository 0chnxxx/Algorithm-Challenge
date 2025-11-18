/**
 * Given string num representing a non-negative integer num, and an integer k, return the smallest possible integer after removing k digits from num.
 *
 * Constraints:
 * 1 <= k <= num.length <= 10^5
 * num consists of only digits.
 * num does not have any leading zeros except for the zero itself.
 */

fun main() {
    val num = "1432219"
    val k = 3

    val result = Solution().removeKdigits(num, k)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(N)
    fun removeKdigits(num: String, k: Int): String {
        val stack = ArrayDeque<Char>()
        var remove = k

        for (digit in num) {
            while (remove > 0 && stack.isNotEmpty() && stack.last() > digit) {
                stack.removeLast()
                remove--
            }

            stack.addLast(digit)
        }

        while (remove > 0) {
            stack.removeLast()
            remove--
        }

        val result = stack.joinToString("").trimStart('0')

        return if (result.isEmpty()) "0" else result
    }
}