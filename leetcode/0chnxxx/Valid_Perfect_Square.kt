/**
 * Given a positive integer num, return true if num is a perfect square or false otherwise.
 * A perfect square is an integer that is the square of an integer.
 * In other words, it is the product of some integer with itself.
 * You must not use any built-in library function, such as sqrt.
 *
 * Constraints:
 * 1 <= num <= 2^31 - 1
 */

fun main() {
    val num = 16

    val result = Solution().isPerfectSquare(num)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(log(N))
    // 공간 복잡도 : O(1)
    fun isPerfectSquare(num: Int): Boolean {
        if (num < 2) return true

        var left = 1L
        var right = num.toLong()

        while (left <= right) {
            val mid = (left + right) / 2
            val square = mid * mid

            when {
                square == num.toLong() -> return true
                square < num -> left = mid + 1
                else -> right = mid - 1
            }
        }

        return false
    }
}
