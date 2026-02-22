/**
 * You are given a sorted array consisting of only integers where every element appears exactly twice, except for one element which appears exactly once.
 * Return the single element that appears only once.
 * Your solution must run in O(log n) time and O(1) space.
 *
 * Constraints:
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i] <= 10^5
 */

fun main() {
    val nums = intArrayOf(1, 1, 2, 3, 3, 4, 4, 8, 8)

    val result = Solution().singleNonDuplicate(nums)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(log N)
    // 공간 복잡도 : O(1)
    fun singleNonDuplicate(nums: IntArray): Int {
        var left = 0
        var right = nums.lastIndex

        while (left < right) {
            var mid = (left + right) / 2

            if (mid % 2 == 1) mid--

            if (nums[mid] == nums[mid + 1]) {
                left = mid + 2
            } else {
                right = mid
            }
        }

        return nums[left]
    }
}