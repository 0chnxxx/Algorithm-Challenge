/**
 * You are given an integer array nums. Two players are playing a game with this array: player 1 and player 2.
 * Player 1 and player 2 take turns, with player 1 starting first.
 * Both players start the game with a score of 0.
 * At each turn, the player takes one of the numbers from either end of the array (i.e., nums[0] or nums[nums.length - 1]) which reduces the size of the array by 1.
 * The player adds the chosen number to their score. The game ends when there are no more elements in the array.
 * Return true if Player 1 can win the game.
 * If the scores of both players are equal, then player 1 is still the winner, and you should also return true.
 * You may assume that both players are playing optimally.
 *
 * Constraints:
 * 1 <= nums.length <= 20
 * 0 <= nums[i] <= 10^7
 */

fun main() {
    val nums = intArrayOf(1, 5, 2)

    val result = Solution().predictTheWinner(nums)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N^2)
    // 공간 복잡도 : O(N^2)
    fun predictTheWinner(nums: IntArray): Boolean {
        val n = nums.size
        val memo = Array(n) { IntArray(n) { Int.MIN_VALUE } }

        fun dfs(l: Int, r: Int): Int {
            if (l == r) return nums[l]
            if (memo[l][r] != Int.MIN_VALUE) return memo[l][r]

            val pickLeft = nums[l] - dfs(l + 1, r)
            val pickRight = nums[r] - dfs(l, r - 1)

            val best = maxOf(pickLeft, pickRight)

            memo[l][r] = best

            return best
        }

        return dfs(0, n - 1) >= 0
    }
}