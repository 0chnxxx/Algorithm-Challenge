/**
 * Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.
 *
 * We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
 *
 * You must solve this problem without using the library's sort function.
 *
 * Constraints:
 * n == nums.length
 * 1 <= n <= 300
 * nums[i] is either 0, 1, or 2.
 */

fun main() {
    val nums = intArrayOf(2, 0, 2, 1, 1, 0)

    Solution().sortColors(nums)

    println(nums.joinToString(", "))
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun sortColors(nums: IntArray): Unit {
        var count0 = 0
        var count1 = 0
        var count2 = 0

        // 0, 1, 2 숫자의 갯수를 카운팅
        for (num in nums) {
            when (num) {
                0 -> count0++
                1 -> count1++
                2 -> count2++
            }
        }

        // 0번 인덱스부터 재배치
        var index = 0

        // 0 -> 1 -> 2 순서로 카운팅한 갯수만큼 배치
        repeat(count0) { nums[index++] = 0 }
        repeat(count1) { nums[index++] = 1 }
        repeat(count2) { nums[index++] = 2 }
    }

//    // 시간 복잡도 : O(N^2)
//    // 공간 복잡도 : O(1)
//    fun sortColors(nums: IntArray): Unit {
//        fun swap(from: Int, to: Int) {
//            val temp = nums[from]
//            nums[from] = nums[to]
//            nums[to] = temp
//        }
//
//        for (i in nums.indices) {
//            for (j in i downTo 0) {
//                if (j - 1 < 0) continue
//
//                if (nums[j] < nums[j - 1]) {
//                    swap(j, j - 1)
//                }
//            }
//        }
//    }
}
