import kotlin.math.min

/**
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 * You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
 * Return the maximum profit you can achieve from this transaction.
 * If you cannot achieve any profit, return 0.
 *
 * Constraints:
 * 1 <= prices.length <= 10^5
 * 0 <= prices[i] <= 10^4
 */

fun main() {
    val prices = intArrayOf(7, 1, 5, 3, 6, 4)

    val solution = Solution().maxProfit(prices)

    println(solution)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun maxProfit(prices: IntArray): Int {
        var minPrice = prices[0]
        var maxProfit = 0

        for (i in 1 until prices.size) {
            val price = prices[i]

            if (price < minPrice) {
                minPrice = price
            } else {
                maxProfit = maxOf(maxProfit, price - minPrice)
            }
        }

        return maxProfit
    }

//    // 시간 복잡도 : O(N)
//    // 공간 복잡도 : O(1)
//    fun maxProfit(prices: IntArray): Int {
//        var minPrice = Int.MAX_VALUE
//        var maxProfit = 0
//
//        for (price in prices) {
//            if (price < minPrice) {
//                minPrice = price
//            } else {
//                val profit = price - minPrice
//                maxProfit = maxOf(maxProfit, profit)
//            }
//        }
//
//        return maxProfit
//    }
}
