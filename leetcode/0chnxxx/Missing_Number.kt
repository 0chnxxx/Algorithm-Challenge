/**
 * Given an array nums containing n distinct numbers in the range [0, n], return the only number in the range that is missing from the array.
 *
 * Constraints:
 * n == nums.length
 * 1 <= n <= 10^4
 * 0 <= nums[i] <= n
 * All the numbers of nums are unique.
 *
 * Follow up:
 * Could you implement a solution using only O(1) extra space complexity and O(n) runtime complexity?
 */

fun main() {
    val nums = intArrayOf(0, 1)

    val result = Solution().missingNumber(nums)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun missingNumber(nums: IntArray): Int {
        var xor = 0
        val n = nums.size

        // 0부터 n까지 xor을 통해 완전한 집합을 구함
        for (i in 0..n) {
            xor = xor xor i
        }

        // 실제 배열과의 xor을 통해 빠진 숫자를 구함
        for (num in nums) {
            xor = xor xor num
        }

        return xor
    }
}
