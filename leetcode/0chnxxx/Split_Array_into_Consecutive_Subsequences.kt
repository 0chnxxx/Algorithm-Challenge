/**
 * You are given an integer array nums that is sorted in non-decreasing order.
 * Determine if it is possible to split nums into one or more subsequences such that both of the following conditions are true:
 * Each subsequence is a consecutive increasing sequence (i.e. each integer is exactly one more than the previous integer).
 * All subsequences have a length of 3 or more.
 * Return true if you can split nums according to the above conditions, or false otherwise.
 * A subsequence of an array is a new array that is formed from the original array by deleting some (can be none) of the elements without disturbing the relative positions of the remaining elements.
 * (i.e., [1,3,5] is a subsequence of [1,2,3,4,5] while [1,3,2] is not).
 *
 * Constraints:
 * 1 <= nums.length <= 10^4
 * -1000 <= nums[i] <= 1000
 * nums is sorted in non-decreasing order.
 */

fun main() {
    val nums = intArrayOf(1, 2, 3, 3, 4, 5)

    val result = Solution().isPossible(nums)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(N)
    fun isPossible(nums: IntArray): Boolean {
        val frequency = HashMap<Int, Int>()
        val need = HashMap<Int, Int>()

        for (num in nums) {
            frequency[num] = frequency.getOrDefault(num, 0) + 1
        }

        for (num in nums) {
            if (frequency[num] == 0) continue

            if (need.getOrDefault(num, 0) > 0) {
                need[num] = need[num]!! - 1
                need[num + 1] = need.getOrDefault(num + 1, 0) + 1
            } else if (frequency.getOrDefault(num + 1, 0) > 0 &&
                frequency.getOrDefault(num + 2, 0) > 0) {

                frequency[num + 1] = frequency[num + 1]!! - 1
                frequency[num + 2] = frequency[num + 2]!! - 1
                need[num + 3] = need.getOrDefault(num + 3, 0) + 1
            } else {
                return false
            }

            frequency[num] = frequency[num]!! - 1
        }

        return true
    }
}
