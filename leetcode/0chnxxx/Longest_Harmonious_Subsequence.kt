import kotlin.math.max

/**
 * We define a harmonious array as an array where the difference between its maximum value and its minimum value is exactly 1.
 * Given an integer array nums, return the length of its longest harmonious subsequence among all its possible subsequences.
 *
 * Constraints:
 * 1 <= nums.length <= 2 * 10^4
 * -10^9 <= nums[i] <= 10^9
 */

fun main() {
    val nums = intArrayOf(1, 3, 2, 2, 5, 2, 3, 7)

    val result = Solution().findLHS(nums)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(N)
    fun findLHS(nums: IntArray): Int {
        val map = mutableMapOf<Int, Int>()

        for (num in nums) {
            map[num] = map.getOrDefault(num, 0) + 1
        }

        var maxLength = 0

        for ((key, value) in map) {
            if (map.containsKey(key + 1)) {
                maxLength = maxOf(maxLength, value + map[key + 1]!!)
            }
        }

        return maxLength
    }
}