import kotlin.math.abs

/**
 * Given an integer array nums of length n and an integer target, find three integers in nums such that the sum is closest to target.
 *
 * Return the sum of the three integers.
 *
 * You may assume that each input would have exactly one solution.
 *
 * 3 <= nums.length <= 500
 * -1000 <= nums[i] <= 1000
 * -10^4 <= target <= 10^4
 */

fun main() {
    val nums = intArrayOf(-1, 2, 1, -4)
    val target = 1

    val solution = Solution().threeSumClosest(nums, target)

    println(solution)
}

class Solution {
    // O(N^2) 풀이
    fun threeSumClosest(nums: IntArray, target: Int): Int {
        nums.sort()

        var closestSum = nums[0] + nums[1] + nums[2]

        for (i in 0 until nums.size - 2) {
            var left = i + 1
            var right = nums.size - 1

            while (left < right) {
                val sum = nums[i] + nums[left] + nums[right]

                if (abs(sum - target) < abs(closestSum - target)) {
                    closestSum = sum
                }

                if (sum < target) {
                    left++
                } else if (sum > target) {
                    right--
                } else {
                    return sum
                }
            }
        }

        return closestSum
    }
}
