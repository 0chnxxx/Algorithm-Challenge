/**
 * Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 * Note that you must do this in-place without making a copy of the array.
 *
 * Constraints:
 * 1 <= nums.length <= 10^4
 * -2^31 <= nums[i] <= 2^31 - 1
 *
 * Follow up:
 * Could you minimize the total number of operations done?
 */

fun main() {
    val nums = intArrayOf(0, 1, 0, 3, 12)

    Solution().moveZeroes(nums)

    println(nums.joinToString(", "))
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun moveZeroes(nums: IntArray): Unit {
        var position = 0

        for (i in nums.indices) {
            if (nums[i] != 0) {
                nums[position] = nums[i]
                position++
            }
        }

        while (position < nums.size) {
            nums[position] = 0
            position++
        }
    }
}