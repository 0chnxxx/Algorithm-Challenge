/**
 * Given an array nums of n integers where nums[i] is in the range [1, n], return an array of all the integers in the range [1, n] that do not appear in nums.
 *
 * Constraints:
 * n == nums.length
 * 1 <= n <= 10^5
 * 1 <= nums[i] <= n
 *
 * Follow up:
 * Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.
 */

fun main() {
    val nums = intArrayOf(4, 3, 2, 7, 8, 2, 3, 1)

    val result = Solution().findDisappearedNumbers(nums)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun findDisappearedNumbers(nums: IntArray): List<Int> {
        val result = mutableListOf<Int>()

        for (i in nums.indices) {
            val idx = Math.abs(nums[i]) - 1

            if (nums[idx] > 0) {
                nums[idx] = -nums[idx]
            }
        }

        for (i in nums.indices) {
            if (nums[i] > 0) {
                result.add(i + 1)
            }
        }

        return result
    }
}