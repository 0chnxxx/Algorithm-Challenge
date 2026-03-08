/**
 * Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.
 * A subarray is a contiguous non-empty sequence of elements within an array.
 *
 * Constraints:
 * 1 <= nums.length <= 2 * 10^4
 * -1000 <= nums[i] <= 1000
 * -10^7 <= k <= 10^7
 */

fun main() {
    val nums = intArrayOf(1, 1, 1)
    val k = 2

    val result = Solution().subarraySum(nums, k)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(N)
    fun subarraySum(nums: IntArray, k: Int): Int {
        val map = HashMap<Int, Int>()

        map[0] = 1

        var sum = 0
        var count = 0

        for (num in nums) {
            sum += num

            count += map.getOrDefault(sum - k, 0)

            map[sum] = map.getOrDefault(sum, 0) + 1
        }

        return count
    }
}