/**
 * We are playing the Guessing Game. The game will work as follows:
 * I pick a number between 1 and n.
 * You guess a number.
 * If you guess the right number, you win the game.
 * If you guess the wrong number, then I will tell you whether the number I picked is higher or lower, and you will continue guessing.
 * Every time you guess a wrong number x, you will pay x dollars. If you run out of money, you lose the game.
 * Given a particular n, return the minimum amount of money you need to guarantee a win regardless of what number I pick.
 *
 * Constraints:
 * 1 <= n <= 200
 */

fun main() {
    val n = 10

    val result = Solution().getMoneyAmount(n)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N^3)
    // 공간 복잡도 : O(N^2)
    fun getMoneyAmount(n: Int): Int {
        val dp = Array(n + 1) { IntArray(n + 1) }

        for (length in 2..n) {
            for (l in 1..n - length + 1) {
                val r = l + length - 1

                dp[l][r] = Int.MAX_VALUE

                for (x in l until r) {
                    val cost = x + maxOf(dp[l][x - 1], dp[x + 1][r])

                    dp[l][r] = minOf(dp[l][r], cost)
                }
            }
        }

        return dp[1][n]
    }
}
