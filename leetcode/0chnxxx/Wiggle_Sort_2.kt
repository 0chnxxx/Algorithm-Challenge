/**
 * Given an integer array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
 * You may assume the input array always has a valid answer.
 *
 * Constraints:
 * 1 <= nums.length <= 5 * 10^4
 * 0 <= nums[i] <= 5000
 * It is guaranteed that there will be an answer for the given input nums.
 *
 * Follow Up:
 * Can you do it in O(n) time and/or in-place with O(1) extra space?
 */

fun main() {
    val nums = intArrayOf(1, 5, 1, 1, 6, 4)

    val result = Solution().wiggleSort(nums)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N log N)
    // 공간 복잡도 : O(N)
    fun wiggleSort(nums: IntArray): Unit {
        val sorted = nums.sorted()
        val n = nums.size

        var left = (n - 1) / 2
        var right = n - 1

        for (i in nums.indices) {
            if (i % 2 == 0) {
                nums[i] = sorted[left--]
            } else {
                nums[i] = sorted[right--]
            }
        }
    }
}