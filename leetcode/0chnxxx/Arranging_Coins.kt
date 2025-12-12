/**
 * You have n coins and you want to build a staircase with these coins.
 * The staircase consists of k rows where the ith row has exactly i coins.
 * The last row of the staircase may be incomplete.
 * Given the integer n, return the number of complete rows of the staircase you will build.
 *
 * Constraints:
 * 1 <= n <= 2^31 - 1
 */

fun main() {
    val n = 5

    val result = Solution().arrangeCoins(n)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(root N)
    // 공간 복잡도 : O(1)
    fun arrangeCoins(n: Int): Int {
        var coins = n.toLong()
        var row = 1

        while (coins >= row) {
            coins -= row
            row++
        }

        return row - 1
    }
}
