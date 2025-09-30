/**
 * You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
 * Return the fewest number of coins that you need to make up that amount.
 * If that amount of money cannot be made up by any combination of the coins, return -1.
 * You may assume that you have an infinite number of each kind of coin.
 *
 * Constraints:
 * 1 <= coins.length <= 12
 * 1 <= coins[i] <= 2^31 - 1
 * 0 <= amount <= 10^4
 */

fun main() {
    val coins = intArrayOf(1, 2, 5)
    val amount = 11

    val result = Solution().coinChange(coins, amount)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(amount * N)
    // 공간 복잡도 : O(amount)
    fun coinChange(coins: IntArray, amount: Int): Int {
        val dp = IntArray(amount + 1) { amount + 1 }

        dp[0] = 0

        for (i in 1..amount) {
            for (coin in coins) {
                if (i - coin >= 0) {
                    dp[i] = minOf(dp[i], dp[i - coin] + 1)
                }
            }
        }

        return if (dp[amount] == amount + 1) -1 else dp[amount]
    }
}