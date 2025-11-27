/**
 * Given an integer array nums, return true if you can partition the array into two subsets such that the sum of the elements in both subsets is equal or false otherwise.
 *
 * Constraints:
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 100
 */

fun main() {
    val nums = intArrayOf(1, 5, 11, 5)

    val reuslt = Solution().canPartition(nums)

    println(reuslt)
}

class Solution {
    // 시간 복잡도 : O(n * target)
    // 공간 복잡도 : O(target)
    fun canPartition(nums: IntArray): Boolean {
        val sum = nums.sum()

        // 전체 합이 홀수면 불가능함
        if (sum % 2 != 0) return false

        val target = sum / 2
        val dp = BooleanArray(target + 1)

        dp[0] = true

        for (num in nums) {
            for (i in target downTo num) {
                if (dp[i - num]) {
                    dp[i] = true
                }
            }
        }

        return dp[target]
    }
}