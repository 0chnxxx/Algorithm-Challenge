/**
 * Given an integer array nums of size n, return the minimum number of moves required to make all array elements equal.
 * In one move, you can increment or decrement an element of the array by 1.
 * Test cases are designed so that the answer will fit in a 32-bit integer.
 *
 * Constraints:
 * n == nums.length
 * 1 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 */

fun main() {
    val nums = intArrayOf(1, 2, 3)

    val result = Solution().minMoves2(nums)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N log N)
    // 공간 복잡도 : O(1)
    fun minMoves2(nums: IntArray): Int {
        nums.sort()

        val median = nums[nums.size / 2]
        var move = 0L

        for (num in nums) {
            move += Math.abs(num - median)
        }

        return move.toInt()
    }
}