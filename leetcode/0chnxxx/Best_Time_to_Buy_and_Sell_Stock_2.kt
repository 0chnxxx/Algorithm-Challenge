/**
 * You are given an integer array prices where prices[i] is the price of a given stock on the ith day.
 *
 * On each day, you may decide to buy and/or sell the stock.
 * You can only hold at most one share of the stock at any time.
 *
 * However, you can buy it then immediately sell it on the same day.
 *
 * Find and return the maximum profit you can achieve.
 *
 * Constraints:
 * 1 <= prices.length <= 3 * 10^4
 * 0 <= prices[i] <= 10^4
 */

fun main() {
    val prices = intArrayOf(1, 2, 3, 4, 5)

    val solution = Solution().maxProfit(prices)

    println(solution)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun maxProfit(prices: IntArray): Int {
        var profit = 0

        for (i in 0 until prices.size - 1) {
            // 수익 실현이 가능한 경우 지속적으로 누적합을 구함
            if (prices[i + 1] > prices[i]) {
                profit += prices[i + 1] - prices[i]
            }
        }

        return profit
    }

//    // 시간 복잡도 : O(N)
//    // 공간 복잡도 : O(1)
//    fun maxProfit(prices: IntArray): Int {
//        var profit = 0
//        var minPrice = Int.MAX_VALUE
//
//        for (i in 0 until prices.size) {
//            val price = prices[i]
//            val nextPrice = if (i == prices.size - 1) Int.MIN_VALUE else prices[i + 1]
//
//            if (price < minPrice) {
//                minPrice = price
//            } else if (price > nextPrice) {
//                profit += price - minPrice
//                minPrice = Int.MAX_VALUE
//            }
//        }
//
//        return profit
//    }
}