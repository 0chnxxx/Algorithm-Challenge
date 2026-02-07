/**
 * Given an integer array nums and an integer k, return true if nums has a good subarray or false otherwise.
 *
 * A good subarray is a subarray where:
 * its length is at least two, and
 * the sum of the elements of the subarray is a multiple of k.
 *
 * Note that:
 * A subarray is a contiguous part of the array.
 * An integer x is a multiple of k if there exists an integer n such that x = n * k. 0 is always a multiple of k.
 *
 * Constraints:
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i] <= 10^9
 * 0 <= sum(nums[i]) <= 2^31 - 1
 * 1 <= k <= 2^31 - 1
 */

fun main() {
    val nums = intArrayOf(23, 2, 4, 6, 7)
    val k = 6

    val result = Solution().checkSubarraySum(nums, k)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(min(n, k))
    fun checkSubarraySum(nums: IntArray, k: Int): Boolean {
        val map = mutableMapOf<Int, Int>()

        map[0] = -1

        var sum = 0

        for (i in nums.indices) {
            sum += nums[i]

            val mod = sum % k

            if (map.containsKey(mod)) {
                if (i - map[mod]!! >= 2) {
                    return true
                }
            } else {
                map[mod] = i
            }
        }

        return false
    }
}
