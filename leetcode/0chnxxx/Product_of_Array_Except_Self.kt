/**
 * Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 * You must write an algorithm that runs in O(n) time and without using the division operation.
 *
 * Constraints:
 * 2 <= nums.length <= 10^5
 * -30 <= nums[i] <= 30
 * The input is generated such that answer[i] is guaranteed to fit in a 32-bit integer.
 *
 * Follow up:
 * Can you solve the problem in O(1) extra space complexity? (The output array does not count as extra space for space complexity analysis.)
 */

fun main() {
    val nums = intArrayOf(1, 2, 3, 4)

    val result = Solution().productExceptSelf(nums)

    println(result.joinToString(", "))
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun productExceptSelf(nums: IntArray): IntArray {
        val length = nums.size
        val result = IntArray(length) { 1 }

        var prefix = 1

        for (i in 0 until length) {
            result[i] = prefix
            prefix *= nums[i]
        }

        var suffix = 1

        for (i in length - 1 downTo 0) {
            result[i] *= suffix
            suffix *= nums[i]
        }

        return result
    }
}