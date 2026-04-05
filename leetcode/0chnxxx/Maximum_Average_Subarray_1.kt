/**
 * You are given an integer array nums consisting of n elements, and an integer k.
 * Find a contiguous subarray whose length is equal to k that has the maximum average value and return this value.
 * Any answer with a calculation error less than 10-5 will be accepted.
 *
 * Constraints:
 * n == nums.length
 * 1 <= k <= n <= 10^5
 * -10^4 <= nums[i] <= 10^4
 */

fun main() {
    val nums = intArrayOf(1, 12, -5, -6, 50, 3)
    val k = 4

    val result = Solution().findMaxAverage(nums, k)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun findMaxAverage(nums: IntArray, k: Int): Double {
        var sum = 0L

        // 1. 초기 k개 합
        for (i in 0 until k) {
            sum += nums[i]
        }

        var maxSum = sum

        // 2. 슬라이딩 윈도우
        for (i in k until nums.size) {
            sum += nums[i]      // 새로 들어오는 값
            sum -= nums[i - k]  // 빠지는 값

            maxSum = maxOf(maxSum, sum)
        }

        return maxSum.toDouble() / k
    }
}