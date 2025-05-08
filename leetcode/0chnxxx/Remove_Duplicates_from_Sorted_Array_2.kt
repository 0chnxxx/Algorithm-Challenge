/**
 * Given an integer array nums sorted in non-decreasing order, remove some duplicates in-place such that each unique element appears at most twice. The relative order of the elements should be kept the same.
 *
 * Since it is impossible to change the length of the array in some languages, you must instead have the result be placed in the first part of the array nums.
 * More formally, if there are k elements after removing the duplicates, then the first k elements of nums should hold the final result.
 * It does not matter what you leave beyond the first k elements.
 *
 * Return k after placing the final result in the first k slots of nums.
 *
 * Do not allocate extra space for another array.
 * You must do this by modifying the input array in-place with O(1) extra memory.
 *
 * Custom Judge:
 * The judge will test your solution with the following code:
 *
 * Constraints:
 * 1 <= nums.length <= 3 * 10^4
 * -10^4 <= nums[i] <= 10^4
 * nums is sorted in non-decreasing order.
 */

fun main() {
    val nums = intArrayOf(1, 1, 1, 2, 2, 3)

    val solution = Solution().removeDuplicates(nums)

    println(solution)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun removeDuplicates(nums: IntArray): Int {
        if (nums.size <= 2) return nums.size

        var index = 2

        for (i in 2 until nums.size) {
            if (nums[i] != nums[index - 2]) {
                nums[index] = nums[i]
                index++
            }
        }

        return index
    }

//    // 시간 복잡도 : O(N^2)
//    // 공간 복잡도 : O(1)
//    fun removeDuplicates(nums: IntArray): Int {
//        for (i in 0 until nums.size) {
//            var count = 1
//
//            for (j in i + 1 until nums.size) {
//                if (count >= 2 && nums[i] == nums[j]) {
//                    nums[j] = Int.MAX_VALUE
//                    count++
//                } else if (nums[i] == nums[j]) {
//                    count++
//                } else {
//                    break
//                }
//            }
//        }
//
//        nums.sort()
//
//        return nums.count { it != Int.MAX_VALUE }
//    }
}
