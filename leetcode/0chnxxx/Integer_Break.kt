/**
 * Given an integer n, break it into the sum of k positive integers, where k >= 2, and maximize the product of those integers.
 * Return the maximum product you can get.
 *
 * Constraints:
 * 2 <= n <= 58
 */

fun main() {
    val n = 2

    val reuslt = Solution().integerBreak(n)

    println(reuslt)
}

class Solution {
    // 시간 복잡도 : O(N^2)
    // 공간 복잡도 : O(N)
    fun integerBreak(n: Int): Int {
        val dp = IntArray(n + 1)

        dp[1] = 1

        for (i in 2..n) {
            for (j in 1 until i) {
                dp[i] = maxOf(dp[i], maxOf(j * (i - j), j * dp[i - j]))
            }
        }

        return dp[n]
    }
}
