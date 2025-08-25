/**
 * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
 *
 * Constraints:
 * 1 <= nums.length <= 5 * 10^4
 * -10^9 <= nums[i] <= 10^9
 *
 * Follow up:
 * Could you solve the problem in linear time and in O(1) space?
 */

fun main() {
    val nums = intArrayOf(3, 2, 3)

    val result = Solution().majorityElement(nums)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun majorityElement(nums: IntArray): List<Int> {
        // n / 3 번 이상 등장하려면 후보군은 2개면 충분함
        var candidate1: Int? = null
        var candidate2: Int? = null
        var count1 = 0
        var count2 = 0

        for (num in nums) {
            if (candidate1 == num) {
                count1++
            } else if (candidate2 == num) {
                count2++
            } else if (count1 == 0) {
                candidate1 = num
                count1 = 1
            } else if (count2 == 0) {
                candidate2 = num
                count2 = 1
            } else {
                count1--
                count2--
            }
        }

        val result = mutableListOf<Int>()
        val size = nums.size / 3

        if (nums.count { it == candidate1 } > size) result.add(candidate1!!)
        if (nums.count { it == candidate2 } > size) result.add(candidate2!!)

        return result
    }
}