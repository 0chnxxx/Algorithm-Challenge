/**
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 *
 * Find the maximum profit you can achieve. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times) with the following restrictions:
 * After you sell your stock, you cannot buy stock on the next day (i.e., cooldown one day).
 *
 * Note:
 * You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 *
 * Constraints:
 * 1 <= prices.length <= 5000
 * 0 <= prices[i] <= 1000
 */

fun main() {
    val prices = intArrayOf(1, 2, 3, 0, 2)

    val result = Solution().maxProfit(prices)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(N)
    fun maxProfit(prices: IntArray): Int {
        if (prices.isEmpty()) return 0

        val n = prices.size
        val hold = IntArray(n)
        val sold = IntArray(n)
        val rest = IntArray(n)

        hold[0] = -prices[0]
        sold[0] = 0
        rest[0] = 0

        for (i in 1 until n) {
            hold[i] = maxOf(hold[i - 1], rest[i - 1] - prices[i])
            sold[i] = hold[i - 1] + prices[i]
            rest[i] = maxOf(rest[i - 1], sold[i - 1])
        }

        return maxOf(sold[n - 1], rest[n - 1])
    }
}