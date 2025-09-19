/**
 * Given an integer array nums, return the length of the longest strictly increasing subsequence.
 *
 * Constraints:
 * 1 <= nums.length <= 2500
 * -10^4 <= nums[i] <= 10^4
 *
 * Follow up:
 * Can you come up with an algorithm that runs in O(n log(n)) time complexity?
 */

fun main() {
    val nums = intArrayOf(10, 9, 2, 5, 3, 7, 101, 18)

    val result = Solution().lengthOfLIS(nums)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N log N)
    // 공간 복잡도 : O(N)
    fun lengthOfLIS(nums: IntArray): Int {
        val sub = mutableListOf<Int>()

        for (num in nums) {
            var left = 0
            var right = sub.size

            while (left < right) {
                val mid = (left + right) / 2

                if (sub[mid] < num) {
                    left = mid + 1
                } else {
                    right = mid
                }
            }

            if (left < sub.size) {
                sub[left] = num
            } else {
                sub.add(num)
            }
        }

        return sub.size
    }
}