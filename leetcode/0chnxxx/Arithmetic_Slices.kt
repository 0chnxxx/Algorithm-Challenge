/**
 * An integer array is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.
 * For example, [1,3,5,7,9], [7,7,7,7], and [3,-1,-5,-9] are arithmetic sequences.
 * Given an integer array nums, return the number of arithmetic subarrays of nums.
 * A subarray is a contiguous subsequence of the array.
 *
 * Constraints:
 * 1 <= nums.length <= 5000
 * -1000 <= nums[i] <= 1000
 */

fun main() {
    val nums = intArrayOf(1, 2, 3, 4)

    val result = Solution().numberOfArithmeticSlices(nums)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun numberOfArithmeticSlices(nums: IntArray): Int {
        if (nums.size < 3) return 0

        var dp = 0
        var result = 0

        for (i in 2 until nums.size) {
            if (nums[i] - nums[i - 1] == nums[i - 1] - nums[i - 2]) {
                dp += 1
                result += dp
            } else {
                dp = 0
            }
        }

        return result
    }
}