/**
 * Given an integer array nums of length n where all the integers of nums are in the range [1, n] and each integer appears at most twice, return an array of all the integers that appears twice.
 * You must write an algorithm that runs in O(n) time and uses only constant auxiliary space, excluding the space needed to store the output
 *
 * Constraints:
 * n == nums.length
 * 1 <= n <= 10^5
 * 1 <= nums[i] <= n
 * Each element in nums appears once or twice.
 */

fun main() {
    val nums = intArrayOf(4, 3, 2, 7, 8, 2, 3, 1)

    val result = Solution().findDuplicates(nums)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun findDuplicates(nums: IntArray): List<Int> {
        val result = mutableListOf<Int>()

        for (i in nums.indices) {
            val index = Math.abs(nums[i]) - 1

            if (nums[index] < 0) {
                result.add(index + 1)
            } else {
                nums[index] = -nums[index]
            }
        }

        return result
    }
}
