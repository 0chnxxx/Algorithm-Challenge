/**
 * You are a professional robber planning to rob houses along a street.
 * Each house has a certain amount of money stashed.
 * All houses at this place are arranged in a circle.
 * That means the first house is the neighbor of the last one.
 * Meanwhile, adjacent houses have a security system connected, and it will automatically contact the police if two adjacent houses were broken into on the same night.
 *
 * Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.
 *
 * Constraints:
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 1000
 */

fun main() {
    val nums = intArrayOf(2, 3, 2)

    val result = Solution().rob(nums)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun rob(nums: IntArray): Int {
        if (nums.size == 1) return nums[0]
        if (nums.size == 2) return maxOf(nums[0], nums[1])

        fun dp(nums: IntArray, start: Int, end: Int): Int {
            var prev1 = 0
            var prev2 = 0

            for (i in start..end) {
                val temp = prev1

                prev1 = maxOf(prev1, prev2 + nums[i])
                prev2 = temp
            }

            return prev1
        }

        return maxOf(
            dp(nums, 0, nums.size - 2), // 첫 집 포함, 마지막 집 제외
            dp(nums, 1, nums.size - 1), // 첫 집 제외, 마지막 집 포함
        )
    }
}
