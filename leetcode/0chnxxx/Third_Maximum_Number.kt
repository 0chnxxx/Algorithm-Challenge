/**
 * Given an integer array nums, return the third distinct maximum number in this array.
 * If the third maximum does not exist, return the maximum number.
 *
 * Constraints:
 * 1 <= nums.length <= 10^4
 * -2^31 <= nums[i] <= 2^31 - 1
 *
 * Follow up:
 * Can you find an O(n) solution?
 */

fun main() {
    val nums = intArrayOf(3, 2, 1)

    val result = Solution().thirdMax(nums)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun thirdMax(nums: IntArray): Int {
        var max1: Long? = null
        var max2: Long? = null
        var max3: Long? = null

        for (num in nums) {
            val n = num.toLong()

            if (n == max1 || n == max2 || n == max3) continue

            when {
                max1 == null || n > max1 -> {
                    max3 = max2
                    max2 = max1
                    max1 = n
                }
                max2 == null || n > max2 -> {
                    max3 = max2
                    max2 = n
                }
                max3 == null || n > max3 -> {
                    max3 = n
                }
            }
        }

        return (max3 ?: max1)!!.toInt()
    }

//    // 시간 복잡도 : O(N)
//    // 공간 복잡도 : O(N)
//    fun thirdMax(nums: IntArray): Int {
//        val sortedNums = nums.toSet().sortedDescending()
//
//        if (sortedNums.size >= 3) {
//            return sortedNums[2]
//        } else {
//            return sortedNums[0]
//        }
//    }
}