/**
 * Given an integer array nums of 2n integers, group these integers into n pairs (a1, b1), (a2, b2), ..., (an, bn) such that the sum of min(ai, bi) for all i is maximized. Return the maximized sum.
 *
 * Constraints:
 * 1 <= n <= 10^4
 * nums.length == 2 * n
 * -104 <= nums[i] <= 10^4
 */

fun main() {
    val nums = intArrayOf(1, 4, 3, 2)

    val result = Solution().arrayPairSum(nums)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N log N)
    // 공간 복잡도 : O(log N)
    fun arrayPairSum(nums: IntArray): Int {
        nums.sort()

        var sum = 0

        for (i in nums.indices step 2) {
            sum += nums[i]
        }

        return sum
    }
}