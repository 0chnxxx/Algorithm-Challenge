/**
 * Given an integer array nums, return true if any value appears at least twice in the array, and return false if every element is distinct.
 *
 * Constraints:
 * 1 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 */

fun main() {
    val nums = intArrayOf(1, 2, 3, 1)

    val result = Solution().containsDuplicate(nums)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(N)
    fun containsDuplicate(nums: IntArray): Boolean {
        val seen = mutableSetOf<Int>()

        for (num in nums) {
            if (!seen.add(num)) {
                return true
            }
        }

        return false
    }

//    // 시간 복잡도 : O(N)
//    // 공간 복잡도 : O(N)
//    fun containsDuplicate(nums: IntArray): Boolean {
//        val distinct = nums.distinct()
//
//        return distinct.size != nums.size
//    }
}