/**
 * You are given an integer array nums of length n.
 *
 * Assume arrk to be an array obtained by rotating nums by k positions clock-wise. We define the rotation function F on nums as follow:
 * F(k) = 0 * arrk[0] + 1 * arrk[1] + ... + (n - 1) * arrk[n - 1].
 *
 * Return the maximum value of F(0), F(1), ..., F(n-1).
 * The test cases are generated so that the answer fits in a 32-bit integer.
 *
 * Constraints:
 * n == nums.length
 * 1 <= n <= 10^5
 * -100 <= nums[i] <= 100
 */

fun main() {
    val nums = intArrayOf(4, 3, 2, 6)

    val result = Solution().maxRotateFunction(nums)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(N)
    fun maxRotateFunction(nums: IntArray): Int {
        val n = nums.size
        val sum = nums.sum()
        var f = nums.mapIndexed { index, i -> index * i }.sum()
        var max = f

        for (k in 1 until n) {
            f = f + sum - n * nums[n - k]

            max = maxOf(max, f)
        }

        return max
    }
}