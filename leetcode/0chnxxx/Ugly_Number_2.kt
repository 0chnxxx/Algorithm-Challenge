/**
 * An ugly number is a positive integer whose prime factors are limited to 2, 3, and 5.
 * Given an integer n, return the nth ugly number.
 *
 * Constraints:
 * 1 <= n <= 1690
 */

fun main() {
    val n = 10

    val result = Solution().nthUglyNumber(n)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(N)
    fun nthUglyNumber(n: Int): Int {
        val dp = IntArray(n)

        dp[0] = 1

        var num2 = 0
        var num3 = 0
        var num5 = 0

        for (i in 1 until n) {
            val next2 = dp[num2] * 2
            val next3 = dp[num3] * 3
            val next5 = dp[num5] * 5

            val nextUgly = minOf(next2, next3, next5)

            dp[i] = nextUgly

            if (nextUgly == next2) num2++
            if (nextUgly == next3) num3++
            if (nextUgly == next5) num5++
        }

        return dp[n - 1]
    }
}
