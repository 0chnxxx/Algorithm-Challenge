/**
 * Given a set of distinct positive integers nums, return the largest subset answer such that every pair (answer[i], answer[j]) of elements in this subset satisfies:
 * answer[i] % answer[j] == 0, or
 * answer[j] % answer[i] == 0
 * If there are multiple solutions, return any of them.
 *
 * Constraints:
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= 2 * 10^9
 * All the integers in nums are unique.
 */

fun main() {
    val nums = intArrayOf(1, 2, 3)

    val result = Solution().largestDivisibleSubset(nums)

    println(result.joinToString(", "))
}

class Solution {
    // 시간 복잡도 : O(N^2)
    // 공간 복잡도 : O(N)
    fun largestDivisibleSubset(nums: IntArray): List<Int> {
        if (nums.isEmpty()) return emptyList()

        nums.sort()

        val n = nums.size
        val dp = IntArray(n) { 1 }
        val prev = IntArray(n) { -1 }

        var maxLen = 1
        var maxIdx = 0

        for (i in 1 until n) {
            for (j in 0 until i) {
                if (nums[i] % nums[j] == 0 && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1
                    prev[i] = j
                }
            }

            if (dp[i] > maxLen) {
                maxLen = dp[i]
                maxIdx = i
            }
        }

        val result = mutableListOf<Int>()
        var k = maxIdx

        while (k >= 0) {
            result.add(nums[k])
            k = prev[k]
        }

        return result.reversed()
    }
}
