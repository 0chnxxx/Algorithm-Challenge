/**
 * Given an integer array nums, return true if there exists a triple of indices (i, j, k) such that i < j < k and nums[i] < nums[j] < nums[k].
 * If no such indices exists, return false.
 *
 * Constraints:
 * 1 <= nums.length <= 5 * 10^5
 * -2^31 <= nums[i] <= 2^31 - 1
 *
 * Follow up:
 * Could you implement a solution that runs in O(n) time complexity and O(1) space complexity?
 */

fun main() {
    val nums = intArrayOf(1, 2, 3, 4, 5)

    val result = Solution().increasingTriplet(nums)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun increasingTriplet(nums: IntArray): Boolean {
        var first = Int.MAX_VALUE
        var second = Int.MAX_VALUE

        for (n in nums) {
            when {
                n <= first -> first = n
                n <= second -> second = n
                else -> return true
            }
        }

        return false
    }
}
