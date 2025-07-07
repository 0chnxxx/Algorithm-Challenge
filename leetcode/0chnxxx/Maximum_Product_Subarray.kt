/**
 * Given an integer array nums, find a subarray that has the largest product, and return the product.
 * The test cases are generated so that the answer will fit in a 32-bit integer.
 *
 * Constraints:
 * 1 <= nums.length <= 2 * 10^4
 * -10 <= nums[i] <= 10
 * The product of any subarray of nums is guaranteed to fit in a 32-bit integer.
 */

fun main() {
    val nums = intArrayOf(-4, -3)

    val result = Solution().maxProduct(nums)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun maxProduct(nums: IntArray): Int {
        val n = nums.size
        var result = nums[0]
        var max = nums[0]
        var min = nums[0]

        for (i in 1 until n) {
            val num = nums[i]

            if (num < 0) {
                val temp = max
                max = min
                min = temp
            }

            max = maxOf(num, max * num)
            min = minOf(num, min * num)

            result = maxOf(result, max)
        }

        return result
    }
}