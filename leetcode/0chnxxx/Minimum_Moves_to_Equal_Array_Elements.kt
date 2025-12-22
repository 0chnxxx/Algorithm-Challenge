/**
 * Given an integer array nums of size n, return the minimum number of moves required to make all array elements equal.
 * In one move, you can increment n - 1 elements of the array by 1.
 *
 * Constraints:
 * n == nums.length
 * 1 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 * The answer is guaranteed to fit in a 32-bit integer.
 */

fun main() {
    val nums = intArrayOf(1, 2, 3)

    val result = Solution().minMoves(nums)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun minMoves(nums: IntArray): Int {
        val min = nums.minOrNull()!!
        var moves = 0

        // 각 숫자를 최솟값으로 만드는데까지 필요한 움직임 수를 더하면 됨
        for (num in nums) {
            moves += num - min
        }

        return moves
    }
}