/**
 * Given a binary array nums, return the maximum length of a contiguous subarray with an equal number of 0 and 1.
 *
 * Constraints:
 * 1 <= nums.length <= 10^5
 * nums[i] is either 0 or 1.
 */

fun main() {
    val nums = intArrayOf(0, 1)

    val result = Solution().findMaxLength(nums)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(N)
    fun findMaxLength(nums: IntArray): Int {
        val map = HashMap<Int, Int>()

        map[0] = -1

        var sum = 0
        var maxLength = 0

        for (i in nums.indices) {
            sum += if (nums[i] == 1) 1 else -1

            if (map.containsKey(sum)) {
                maxLength = maxOf(maxLength, i - map[sum]!!)
            } else {
                map[sum] = i
            }
        }

        return maxLength
    }
}