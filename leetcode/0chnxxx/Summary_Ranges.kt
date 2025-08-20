/**
 * You are given a sorted unique integer array nums.
 * A range [a,b] is the set of all integers from a to b (inclusive).
 * Return the smallest sorted list of ranges that cover all the numbers in the array exactly.
 * That is, each element of nums is covered by exactly one of the ranges, and there is no integer x such that x is in one of the ranges but not in nums.
 *
 * Each range [a,b] in the list should be output as:
 * "a->b" if a != b
 * "a" if a == b
 *
 * Constraints:
 * 0 <= nums.length <= 20
 * -2^31 <= nums[i] <= 2^31 - 1
 * All the values of nums are unique.
 * nums is sorted in ascending order.
 */

fun main() {
    val nums = intArrayOf(0, 1, 2, 4, 5, 7)

    val result = Solution().summaryRanges(nums)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(N)
    fun summaryRanges(nums: IntArray): List<String> {
        if (nums.isEmpty()) return emptyList()

        val result = mutableListOf<String>()
        var start = nums[0]

        for (i in 1..nums.size) {
            if (i == nums.size || nums[i] != nums[i - 1] + 1) {
                val end = nums[i - 1]

                if (start == end) {
                    result.add("$start")
                } else {
                    result.add("$start->$end")
                }

                if (i < nums.size) {
                    start = nums[i]
                }
            }
        }

        return result
    }
}