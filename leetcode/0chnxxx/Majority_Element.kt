/**
 * Given an array nums of size n, return the majority element.
 * The majority element is the element that appears more than ⌊n / 2⌋ times.
 * You may assume that the majority element always exists in the array.
 *
 * Constraints:
 * n == nums.length
 * 1 <= n <= 5 * 10^4
 * -10^9 <= nums[i] <= 10^9
 *
 * Follow-up:
 * Could you solve the problem in linear time and in O(1) space?
 */

fun main() {
    val nums = intArrayOf(2, 2)

    val result = Solution().majorityElement(nums)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun majorityElement(nums: IntArray): Int {
        var count = 0
        var candidate = 0

        for (num in nums) {
            if (count == 0) {
                candidate = num
            }

            count += if (num == candidate) 1 else -1
        }

        return candidate
    }

//    // 시간 복잡도 : O(N)
//    // 공간 복잡도 : O(N)
//    fun majorityElement(nums: IntArray): Int {
//        val judge = Math.ceil(nums.size / 2.0).toInt()
//        val group = nums.groupBy { it }
//
//        for (entry in group) {
//            if (entry.value.size >= judge) {
//                return entry.key
//            }
//        }
//
//        return 0
//    }
}
