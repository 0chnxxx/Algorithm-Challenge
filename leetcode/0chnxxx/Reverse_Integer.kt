/**
 * Given a signed 32-bit integer x, return x with its digits reversed. If reversing x causes the value to go outside the signed 32-bit integer range [-231, 231 - 1], then return 0.
 *
 * Assume the environment does not allow you to store 64-bit integers (signed or unsigned).
 *
 * -2^31 <= x <= 2^31 - 1
 */

fun main() {
    val x = 1534236469

    val solution = Solution().reverse(x)

    println(solution)
}

class Solution {
    fun reverse(x: Int): Int {
        try {
            return if (x < 0) {
                0 - Math.abs(x).toString().reversed().toInt()
            } else {
                x.toString().reversed().toInt()
            }
        } catch (e: NumberFormatException) {
            return 0
        }
    }
}
