/**
 * You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
 * Return the number of combinations that make up that amount.
 * If that amount of money cannot be made up by any combination of the coins, return 0.
 * You may assume that you have an infinite number of each kind of coin.
 * The answer is guaranteed to fit into a signed 32-bit integer.
 *
 * Constraints:
 * 1 <= coins.length <= 300
 * 1 <= coins[i] <= 5000
 * All the values of coins are unique.
 * 0 <= amount <= 5000
 */

fun main() {
    val amount = 5
    val coins = intArrayOf(1, 2, 5)

    val result = Solution().change(amount, coins)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(coins.length * amount)
    // 공간 복잡도 : O(amount)
    fun change(amount: Int, coins: IntArray): Int {
        val dp = IntArray(amount + 1)

        dp[0] = 1

        for (coin in coins) {
            for (i in coin..amount) {
                dp[i] += dp[i - coin]
            }
        }

        return dp[amount]
    }
}