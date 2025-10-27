/**
 * Given an array of distinct integers nums and a target integer target, return the number of possible combinations that add up to target.
 * The test cases are generated so that the answer can fit in a 32-bit integer.
 *
 * Constraints:
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 1000
 * All the elements of nums are unique.
 * 1 <= target <= 1000
 *
 * Follow up:
 * What if negative numbers are allowed in the given array? How does it change the problem? What limitation we need to add to the question to allow negative numbers?
 */

fun main() {
    val nums = intArrayOf(1, 2, 3)
    val target = 4

    val result = Solution().combinationSum4(nums, target)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N * target)
    // 공간 복잡도 : O(target)
    fun combinationSum4(nums: IntArray, target: Int): Int {
        val dp = IntArray(target + 1) { -1 }

        dp[0] = 1

        fun dfs(remain: Int): Int {
            if (remain < 0) return 0
            if (dp[remain] != -1) return dp[remain]

            var count = 0

            for (num in nums) {
                count += dfs(remain - num)
            }

            dp[remain] = count

            return count
        }

        return dfs(target)
    }
}