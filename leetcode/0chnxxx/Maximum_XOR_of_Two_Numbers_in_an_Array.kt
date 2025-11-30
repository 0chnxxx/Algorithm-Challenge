/**
 * Given an integer array nums, return the maximum result of nums[i] XOR nums[j], where 0 <= i <= j < n.
 *
 * Constraints:
 * 1 <= nums.length <= 2 * 10^5
 * 0 <= nums[i] <= 2^31 - 1
 */

fun main() {
    val nums = intArrayOf(3, 10, 5, 25, 2, 8)

    val result = Solution().findMaximumXOR(nums)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(N)
    fun findMaximumXOR(nums: IntArray): Int {
        var maxXor = 0
        var mask = 0

        for (i in 31 downTo 0) {
            mask = mask or (1 shl i)

            val set = HashSet<Int>()

            for (num in nums) {
                set.add(num and mask)
            }

            val candidate = maxXor or (1 shl i)

            for (prefix in set) {
                if (set.contains(prefix xor candidate)) {
                    maxXor = candidate
                    break
                }
            }
        }

        return maxXor
    }
}
