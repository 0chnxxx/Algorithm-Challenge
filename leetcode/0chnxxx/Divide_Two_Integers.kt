/**
 * Given two integers dividend and divisor, divide two integers without using multiplication, division, and mod operator.
 *
 * The integer division should truncate toward zero, which means losing its fractional part.
 * For example, 8.345 would be truncated to 8, and -2.7335 would be truncated to -2.
 *
 * Return the quotient after dividing dividend by divisor.
 *
 * Note:
 * Assume we are dealing with an environment that could only store integers within the 32-bit signed integer range: [−2^31, 2^31 − 1].
 * For this problem, if the quotient is strictly greater than 2^31 - 1, then return 2^31 - 1, and if the quotient is strictly less than -2^31, then return -2^31.
 *
 * -2^31 <= dividend, divisor <= 2^31 - 1
 * divisor != 0
 */

fun main() {
    val dividend = -2147483648
    val divisor = -1

    val solution = Solution().divide(dividend, divisor)

    println(solution)
}

class Solution {
    fun divide(dividend: Int, divisor: Int): Int {
        val result = dividend.toLong() / divisor.toLong()

        return if (result > Int.MAX_VALUE) {
            Int.MAX_VALUE
        } else if (result < Int.MIN_VALUE) {
            Int.MIN_VALUE
        } else {
            result.toInt()
        }
    }
}
