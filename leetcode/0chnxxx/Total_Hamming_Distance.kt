/**
 * The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
 * Given an integer array nums, return the sum of Hamming distances between all the pairs of the integers in nums.
 *
 * Constraints:
 * 1 <= nums.length <= 10^4
 * 0 <= nums[i] <= 10^9
 * The answer for the given input will fit in a 32-bit integer.
 */

fun main() {
    val nums = intArrayOf(4, 14, 2)

    val result = Solution().totalHammingDistance(nums)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun totalHammingDistance(nums: IntArray): Int {
        val n = nums.size
        var result = 0

        for (bit in 0..30) {
            var count1 = 0

            for (num in nums) {
                if ((num shr bit) and 1 == 1) {
                    count1++
                }
            }

            val count0 = n - count1
            result += count1 * count0
        }

        return result
    }
}