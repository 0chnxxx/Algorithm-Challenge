/**
 * Given an integer array nums, find three numbers whose product is maximum and return the maximum product.
 *
 * Constraints:
 * 3 <= nums.length <= 10^4
 * -1000 <= nums[i] <= 1000
 */

fun main() {
    val nums = intArrayOf(1, 2, 3)

    val result = Solution().maximumProduct(nums)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N log N)
    // 공간 복잡도 : O(1)
    fun maximumProduct(nums: IntArray): Int {
        nums.sort()

        val n = nums.size

        val case1 = nums[n - 1] * nums[n - 2] * nums[n - 3]
        val case2 = nums[0] * nums[1] * nums[n - 1]

        return maxOf(case1, case2)
    }
}