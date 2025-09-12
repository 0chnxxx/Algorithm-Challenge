/**
 * Given an integer n, return the least number of perfect square numbers that sum to n.
 * A perfect square is an integer that is the square of an integer;
 * in other words, it is the product of some integer with itself.
 * For example, 1, 4, 9, and 16 are perfect squares while 3 and 11 are not.
 *
 * Constraints:
 * 1 <= n <= 10^4
 */

fun main() {
    val n = 12

    val result = Solution().numSquares(n)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N rootN)
    // 공간 복잡도 : O(N)
    fun numSquares(n: Int): Int {
        val dp = IntArray(n + 1) { Int.MAX_VALUE }

        dp[0] = 0

        for (i in 1..n) {
            var j = 1

            while (j * j <= i) {
                dp[i] = minOf(dp[i], dp[i - j * j] + 1)
                j++
            }
        }

        return dp[n]
    }
}