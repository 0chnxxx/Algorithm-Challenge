/**
 * Given a non-negative integer c, decide whether there're two integers a and b such that a2 + b2 = c.
 *
 * Constraints:
 * 0 <= c <= 2^31 - 1
 */

fun main() {
    val c = 5

    val result = Solution().judgeSquareSum(c)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(root C)
    // 공간 복잡도 : O(1)
    fun judgeSquareSum(c: Int): Boolean {
        var left = 0L
        var right = kotlin.math.sqrt(c.toDouble()).toLong()

        while (left <= right) {
            val sum = left * left + right * right

            when {
                sum == c.toLong() -> return true
                sum > c -> right--
                else -> left++
            }
        }

        return false
    }
}
