/**
 * Given an integer array nums, you need to find one continuous subarray such that if you only sort this subarray in non-decreasing order, then the whole array will be sorted in non-decreasing order.
 * Return the shortest such subarray and output its length.
 *
 * Constraints:
 * 1 <= nums.length <= 10^4
 * -10^5 <= nums[i] <= 10^5
 *
 * Follow up:
 * Can you solve it in O(n) time complexity?
 */

fun main() {
    val nums = intArrayOf(2, 6, 4, 8, 10, 9, 15)

    val result = Solution().findUnsortedSubarray(nums)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun findUnsortedSubarray(nums: IntArray): Int {
        var left = -1
        var right = -1

        var max = Int.MIN_VALUE

        for (i in nums.indices) {
            if (nums[i] < max) {
                right = i
            } else {
                max = nums[i]
            }
        }

        var min = Int.MAX_VALUE

        for (i in nums.size - 1 downTo 0) {
            if (nums[i] > min) {
                left = i
            } else {
                min = nums[i]
            }
        }

        return if (right == -1) 0 else right - left + 1
    }
}