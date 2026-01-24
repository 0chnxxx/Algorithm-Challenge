/**
 * Given a circular integer array nums (i.e., the next element of nums[nums.length - 1] is nums[0]), return the next greater number for every element in nums.
 * The next greater number of a number x is the first greater number to its traversing-order next in the array, which means you could search circularly to find its next greater number.
 * If it doesn't exist, return -1 for this number.
 *
 * Constraints:
 * 1 <= nums.length <= 10^4
 * -10^9 <= nums[i] <= 10^9
 */

fun main() {
    val nums = intArrayOf(1, 2, 1)

    val result = Solution().nextGreaterElements(nums)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(N)
    fun nextGreaterElements(nums: IntArray): IntArray {
        val n = nums.size
        val result = IntArray(n) { -1 }
        val stack = ArrayDeque<Int>()

        for (i in 0 until 2 * n) {
            val cur = nums[i % n]

            while (stack.isNotEmpty() && nums[stack.last()] < cur) {
                val idx = stack.removeLast()
                result[idx] = cur
            }

            if (i < n) {
                stack.addLast(i)
            }
        }

        return result
    }
}
