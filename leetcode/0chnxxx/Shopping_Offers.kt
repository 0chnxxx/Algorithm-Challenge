/**
 * In LeetCode Store, there are n items to sell.
 * Each item has a price.
 * However, there are some special offers, and a special offer consists of one or more different kinds of items with a sale price.
 * You are given an integer array price where price[i] is the price of the ith item, and an integer array needs where needs[i] is the number of pieces of the ith item you want to buy.
 * You are also given an array special where special[i] is of size n + 1 where special[i][j] is the number of pieces of the jth item in the ith offer and special[i][n] (i.e., the last integer in the array) is the price of the ith offer.
 * Return the lowest price you have to pay for exactly certain items as given, where you could make optimal use of the special offers.
 * You are not allowed to buy more items than you want, even if that would lower the overall price.
 * You could use any of the special offers as many times as you want.
 *
 * Constraints:
 * n == price.length == needs.length
 * 1 <= n <= 6
 * 0 <= price[i], needs[i] <= 10
 * 1 <= special.length <= 100
 * special[i].length == n + 1
 * 0 <= special[i][j] <= 50
 * The input is generated that at least one of special[i][j] is non-zero for 0 <= j <= n - 1.
 */

fun main() {
    val price = listOf(2, 5)
    val special = listOf(
        listOf(3, 0, 5),
        listOf(1, 2, 10)
    )
    val needs = listOf(3, 2)

    val result = Solution().shoppingOffers(price, special, needs)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(11^N * special.length * N)
    // 공간 복잡도 : O(11^N)
    fun shoppingOffers(price: List<Int>, special: List<List<Int>>, needs: List<Int>): Int {
        val memo = HashMap<List<Int>, Int>()

        fun dfs(currNeeds: List<Int>): Int {
            memo[currNeeds]?.let { return it }

            var minCost = 0

            for (i in currNeeds.indices) {
                minCost += currNeeds[i] * price[i]
            }

            for (offer in special) {
                val nextNeeds = mutableListOf<Int>()
                var valid = true

                for (i in currNeeds.indices) {
                    if (currNeeds[i] < offer[i]) {
                        valid = false
                        break
                    }

                    nextNeeds.add(currNeeds[i] - offer[i])
                }

                if (valid) {
                    val offerPrice = offer.last()
                    val cost = dfs(nextNeeds) + offerPrice

                    minCost = minOf(minCost, cost)
                }
            }

            memo[currNeeds] = minCost

            return minCost
        }

        return dfs(needs)
    }
}