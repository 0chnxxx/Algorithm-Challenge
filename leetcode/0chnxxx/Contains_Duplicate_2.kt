import kotlin.math.abs

/**
 * Given an integer array nums and an integer k, return true if there are two distinct indices i and j in the array such that nums[i] == nums[j] and abs(i - j) <= k.
 *
 * Constraints:
 * 1 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 * 0 <= k <= 10^5
 */

fun main() {
    val nums = intArrayOf(1, 2, 3, 1)
    val k = 3

    val result = Solution().containsNearbyDuplicate(nums, k)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(N)
    fun containsNearbyDuplicate(nums: IntArray, k: Int): Boolean {
        val map = mutableMapOf<Int, Int>()

        for (i in nums.indices) {
            val num = nums[i]

            if (map.containsKey(num) && i - map[num]!! <= k) {
                return true
            }

            map[num] = i
        }

        return false
    }

//    // 시간 복잡도 : O(N^2)
//    // 공간 복잡도 : O(1)
//    fun containsNearbyDuplicate(nums: IntArray, k: Int): Boolean {
//        for (i in nums.indices) {
//            for (j in i + 1 until nums.size) {
//                if (nums[i] == nums[j] && abs(i - j) <= k) {
//                    return true
//                }
//            }
//        }
//
//        return false
//    }
}