/**
 * There is an integer array nums sorted in ascending order (with distinct values).
 *
 * Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed).
 * For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
 *
 * Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.
 *
 * You must write an algorithm with O(log n) runtime complexity.
 *
 * 1 <= nums.length <= 5000
 * -10^4 <= nums[i] <= 10^4
 * All values of nums are unique.
 * nums is an ascending array that is possibly rotated.
 * -10^4 <= target <= 10^4
 */

fun main() {
    val nums = intArrayOf(4, 5, 6, 7, 0, 1, 2)
    val target = 0

    val solution = Solution().search(nums, target)

    println(solution)
}

class Solution {
    // 시간 복잡도 : O(log N) -> 이진 탐색으로 최적화
    // 공간 복잡도 : O(1)
    fun search(nums: IntArray, target: Int): Int {
        var left = 0
        var right = nums.size - 1

        while (left <= right) {
            val mid = left + (right - left) / 2

            when {
                nums[mid] == target -> return mid
                nums[left] <= nums[mid] -> {
                    if (target in nums[left]..nums[mid]) {
                        right = mid - 1
                    } else {
                        left = mid + 1
                    }
                }
                else -> {
                    if (target in nums[mid]..nums[right]) {
                        left = mid + 1
                    } else {
                        right = mid - 1
                    }
                }
            }
        }

        return -1
    }

//    // 시간 복잡도 : O(N) -> 투 포인터로 최적화
//    // 공간 복잡도 : O(1)
//    fun search(nums: IntArray, target: Int): Int {
//        var left = 0
//        var right = nums.size - 1
//
//        while (left <= right) {
//            if (nums[left] == target) {
//                return left
//            }
//
//            if (nums[right] == target) {
//                return right
//            }
//
//            left++
//            right--
//        }
//
//        return -1
//    }

//    // 시간 복잡도 : O(N)
//    // 공간 복잡도 : O(1)
//    fun search(nums: IntArray, target: Int): Int {
//        return nums.indexOf(target)
//    }
}
