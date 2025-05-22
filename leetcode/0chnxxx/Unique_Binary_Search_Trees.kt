/**
 * Given an integer n, return the number of structurally unique BST's (binary search trees) which has exactly n nodes of unique values from 1 to n.
 *
 * Constraints:
 * 1 <= n <= 19
 */

fun main() {
    val n = 3

    val solution = Solution().numTrees(n)

    println(solution)
}

class Solution {
    // 시간 복잡도 : O(N^2)
    // 공간 복잡도 : O(N)
    fun numTrees(n: Int): Int {
        val dp = IntArray(n + 1)

        dp[0] = 1
        dp[1] = 1

        for (i in 2..n) {
            for (j in 0 until i) {
                dp[i] += dp[j] * dp[i - j - 1]
            }
        }

        return dp[n]
    }
}
