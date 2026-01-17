/**
 * You are given an integer array nums and an integer target.
 * You want to build an expression out of nums by adding one of the symbols '+' and '-' before each integer in nums and then concatenate all the integers.
 * For example, if nums = [2, 1], you can add a '+' before 2 and a '-' before 1 and concatenate them to build the expression "+2-1".
 * Return the number of different expressions that you can build, which evaluates to target.
 *
 * Constraints:
 * 1 <= nums.length <= 20
 * 0 <= nums[i] <= 1000
 * 0 <= sum(nums[i]) <= 1000
 * -1000 <= target <= 1000
 */

fun main() {
    val nums = intArrayOf(1, 1, 1, 1, 1)
    val target = 3

    val result = Solution().findTargetSumWays(nums, target)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(2^N)
    // 공간 복잡도 : O(N)
    fun findTargetSumWays(nums: IntArray, target: Int): Int {
        fun dfs(nums: IntArray, index: Int, sum: Int, target: Int): Int {
            if (index == nums.size) {
                return if (sum == target) 1 else 0
            }

            val plus = dfs(nums, index + 1, sum + nums[index], target)
            val minus = dfs(nums, index + 1, sum - nums[index], target)

            return plus + minus
        }

        return dfs(nums, 0, 0, target)
    }
}
