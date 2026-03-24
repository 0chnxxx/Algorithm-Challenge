/**
 * Given an integer array nums, return the number of triplets chosen from the array that can make triangles if we take them as side lengths of a triangle.
 *
 * Constraints:
 * 1 <= nums.length <= 1000
 * 0 <= nums[i] <= 1000
 */

fun main() {
    val nums = intArrayOf(2, 2, 3, 4)

    val result = Solution().triangleNumber(nums)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N^2)
    // 공간 복잡도 : O(1)
    fun triangleNumber(nums: IntArray): Int {
        nums.sort()

        var count = 0

        for (i in nums.size - 1 downTo 2) {
            var left = 0
            var right = i - 1

            while (left < right) {
                if (nums[left] + nums[right] > nums[i]) {
                    count += (right - left)
                    right--
                } else {
                    left++
                }
            }
        }

        return count
    }
}