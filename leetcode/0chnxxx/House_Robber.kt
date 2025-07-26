/**
 * You are a professional robber planning to rob houses along a street.
 * Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
 *
 * Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.
 *
 * Constraints:
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 400
 */

fun main() {
    val nums = intArrayOf(1, 2, 3, 1)

    val result = Solution().rob(nums)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun rob(nums: IntArray): Int {
        if (nums.size == 1) return nums[0]

        var prev2 = nums[0]
        var prev1 = maxOf(nums[0], nums[1])

        for (i in 2 until nums.size) {
            val current = maxOf(prev1, prev2 + nums[i])

            prev2 = prev1
            prev1 = current
        }

        return prev1
    }

//    // 시간 복잡도 : O(N)
//    // 공간 복잡도 : O(N)
//    fun rob(nums: IntArray): Int {
//        if (nums.size == 1) return nums[0]
//        if (nums.size == 2) return maxOf(nums[0], nums[1])
//
//        val dp = IntArray(nums.size)
//
//        dp[0] = nums[0]
//        dp[1] = maxOf(nums[0], nums[1])
//
//        for (i in 2 until nums.size) {
//            dp[i] = maxOf(dp[i - 1], dp[i - 2] + nums[i])
//        }
//
//        return dp.last()
//    }
}
