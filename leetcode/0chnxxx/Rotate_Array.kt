/**
 * Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.
 *
 * Constraints:
 * 1 <= nums.length <= 10^5
 * -2^31 <= nums[i] <= 2^31 - 1
 * 0 <= k <= 10^5
 *
 * Follow up:
 * Try to come up with as many solutions as you can.
 * There are at least three different ways to solve this problem.
 * Could you do it in-place with O(1) extra space?
 */

fun main() {
    val nums = intArrayOf(1, 2, 3, 4, 5, 6, 7)
    val k = 3

    Solution().rotate(nums, k)

    println(nums.joinToString(", "))
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun rotate(nums: IntArray, k: Int): Unit {
        val n = nums.size
        val steps = k % n

        // 전체 뒤집기
        reverse(nums, 0, n - 1)
        // steps를 기준으로 앞부분 뒤집기
        reverse(nums, 0, steps - 1)
        // steps를 기준으로 뒷부분 뒤집기
        reverse(nums, steps, n - 1)
    }

    private fun reverse(nums: IntArray, start: Int, end: Int) {
        var i = start
        var j = end

        while (i < j) {
            val tmp = nums[i]

            nums[i] = nums[j]
            nums[j] = tmp

            i++
            j--
        }
    }
}