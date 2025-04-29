/**
 * Given a non-negative integer x, return the square root of x rounded down to the nearest integer.
 * The returned integer should be non-negative as well.
 *
 * You must not use any built-in exponent function or operator.
 *
 * For example, do not use pow(x, 0.5) in c++ or x ** 0.5 in python.
 *
 * 0 <= x <= 2^31 - 1
 */

fun main() {
    val x = 4

    val solution = Solution().mySqrt(x)

    println(solution)
}

class Solution {
    // 시간 복잡도 : O(log N)
    // 공간 복잡도 : O(1)
    fun mySqrt(x: Int): Int {
        var left = 0
        var right = x

        while (left <= right) {
            val mid = (left + right) / 2
            val square = mid.toLong() * mid.toLong()

            if (square == x.toLong()) {
                return mid
            } else if (square < x.toLong()) {
                left = mid + 1
            } else {
                right = mid - 1
            }
        }

        return right
    }
}
