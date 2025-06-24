/**
 * Given a non-empty array of integers nums, every element appears twice except for one.
 * Find that single one.
 * You must implement a solution with a linear runtime complexity and use only constant extra space.
 *
 * Constraints:
 * 1 <= nums.length <= 3 * 10^4
 * -3 * 10^4 <= nums[i] <= 3 * 10^4
 * Each element in the array appears twice except for one element which appears only once.
 */

fun main() {
    val nums = intArrayOf(2, 2, 1)

    val result = Solution().singleNumber(nums)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun singleNumber(nums: IntArray): Int {
        var result = 0

        for (num in nums) {
            // A XOR A = 0
            // 0 XOR A = A
            // 즉, 중복된 숫자를 걸러내고 한 번 나타난 숫자만 남김
            result = result xor num
        }

        return result
    }
}
