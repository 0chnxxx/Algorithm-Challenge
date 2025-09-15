/**
 * Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.
 * There is only one repeated number in nums, return this repeated number.
 * You must solve the problem without modifying the array nums and using only constant extra space.
 *
 * Constraints:
 * 1 <= n <= 10^5
 * nums.length == n + 1
 * 1 <= nums[i] <= n
 * All the integers in nums appear only once except for precisely one integer which appears two or more times.
 *
 *
 * Follow up:
 * How can we prove that at least one duplicate number must exist in nums?
 * Can you solve the problem in linear runtime complexity?
 */

fun main() {
    val nums = intArrayOf(3, 1, 3, 4, 2)

    val result = Solution().findDuplicate(nums)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun findDuplicate(nums: IntArray): Int {
        var slow = nums[0]
        var fast = nums[0]

        do {
            slow = nums[slow]
            fast = nums[nums[fast]]
        } while (slow != fast)

        slow = nums[0]

        while (slow != fast) {
            slow = nums[slow]
            fast = nums[fast]
        }

        return slow
    }
}