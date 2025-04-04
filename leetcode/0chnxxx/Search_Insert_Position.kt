/**
 * Given a sorted array of distinct integers and a target value, return the index if the target is found.
 * If not, return the index where it would be if it were inserted in order.
 *
 * You must write an algorithm with O(log n) runtime complexity.
 *
 * 1 <= nums.length <= 104
 * -104 <= nums[i] <= 104
 * nums contains distinct values sorted in ascending order.
 * -104 <= target <= 104
 */

fun main() {
    val nums = intArrayOf(1, 3, 5, 6)
    val target = 5

    val solution = Solution().searchInsert(nums, target)

    println(solution)
}

class Solution {
    // 시간 복잡도 : O(log N)
    // 공간 복잡도 : O(1)
    fun searchInsert(nums: IntArray, target: Int): Int {
        var left = 0
        var right = nums.size - 1

        while (left <= right) {
            val mid = left + (right - left) / 2

            when {
                nums[mid] < target -> left = mid + 1
                nums[mid] > target -> right = mid - 1
                else -> return mid
            }
        }

        return left
    }

//    // 시간 복잡도 : O(N)
//    // 공간 복잡도 : O(1)
//    fun searchInsert(nums: IntArray, target: Int): Int {
//        var index = 0
//
//        for (i in nums.indices) {
//            val num = nums[i]
//
//            if (num < target) index++
//        }
//
//        return index
//    }
}
