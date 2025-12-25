/**
 * Given an array of n integers nums, a 132 pattern is a subsequence of three integers nums[i], nums[j] and nums[k] such that i < j < k and nums[i] < nums[k] < nums[j].
 * Return true if there is a 132 pattern in nums, otherwise, return false.
 *
 * Constraints:
 * n == nums.length
 * 1 <= n <= 2 * 10^5
 * -10^9 <= nums[i] <= 10^9
 */

fun main() {
    val nums = intArrayOf(1, 2, 3, 4)

    val result = Solution().find132pattern(nums)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(N)
    fun find132pattern(nums: IntArray): Boolean {
        if (nums.size < 3) return false

        val stack = ArrayDeque<Int>()
        var third = Int.MIN_VALUE

        for (i in nums.lastIndex downTo 0) {
            val num = nums[i]

            if (num < third) return true

            while (stack.isNotEmpty() && num > stack.last()) {
                third = stack.removeLast()
            }

            stack.addLast(num)
        }

        return false
    }
}
