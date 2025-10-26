/**
 * A wiggle sequence is a sequence where the differences between successive numbers strictly alternate between positive and negative.
 * The first difference (if one exists) may be either positive or negative.
 * A sequence with one element and a sequence with two non-equal elements are trivially wiggle sequences.
 *
 * For example, [1, 7, 4, 9, 2, 5] is a wiggle sequence because the differences (6, -3, 5, -7, 3) alternate between positive and negative.
 * In contrast, [1, 4, 7, 2, 5] and [1, 7, 4, 5, 5] are not wiggle sequences.
 * The first is not because its first two differences are positive, and the second is not because its last difference is zero.
 *
 * A subsequence is obtained by deleting some elements (possibly zero) from the original sequence, leaving the remaining elements in their original order.
 * Given an integer array nums, return the length of the longest wiggle subsequence of nums.
 *
 * Constraints:
 * 1 <= nums.length <= 1000
 * 0 <= nums[i] <= 1000
 *
 * Follow up:
 * Could you solve this in O(n) time?
 */

fun main() {
    val nums = intArrayOf(1, 7, 4, 9, 2, 5)

    val result = Solution().wiggleMaxLength(nums)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun wiggleMaxLength(nums: IntArray): Int {
        if (nums.size < 2) return nums.size

        var up = 1
        var down = 1

        // 모든 숫자를 순회
        for (i in 1 until nums.size) {
            // 현재 숫자가 이전 숫자보다 큰 경우
            if (nums[i] > nums[i - 1]) {
                // 증가분 계산
                up = down + 1
            // 현재 숫자가 이전 숫자보다 작은 경우
            } else if (nums[i] < nums[i - 1]) {
                // 감소분 계산
                down = up + 1
            }
        }

        // 증가분 감소분 중에 큰 값을 찾음
        return maxOf(up, down)
    }
}
