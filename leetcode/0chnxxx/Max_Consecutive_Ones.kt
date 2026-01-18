/**
 * Given a binary array nums, return the maximum number of consecutive 1's in the array.
 *
 * Constraints:
 * 1 <= nums.length <= 10^5
 * nums[i] is either 0 or 1.
 */

fun main() {
    val nums = intArrayOf(1, 1, 0, 1, 1, 1)

    val result = Solution().findMaxConsecutiveOnes(nums)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun findMaxConsecutiveOnes(nums: IntArray): Int {
        var max = 0
        var count = 0

        for (num in nums) {
            if (num == 1) {
                count++
            } else {
                max = maxOf(max, count)
                count = 0
            }
        }

        max = maxOf(max, count)

        return max
    }
}