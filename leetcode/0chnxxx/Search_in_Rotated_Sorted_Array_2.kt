/**
 * There is an integer array nums sorted in non-decreasing order (not necessarily with distinct values).
 *
 * Before being passed to your function, nums is rotated at an unknown pivot index k (0 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed).
 * For example, [0,1,2,4,4,4,5,6,6,7] might be rotated at pivot index 5 and become [4,5,6,6,7,0,1,2,4,4].
 *
 * Given the array nums after the rotation and an integer target, return true if target is in nums, or false if it is not in nums.
 *
 * You must decrease the overall operation steps as much as possible.
 *
 * Constraints:
 * 1 <= nums.length <= 5000
 * -10^4 <= nums[i] <= 10^4
 * nums is guaranteed to be rotated at some pivot.
 * -10^4 <= target <= 10^4
 */

fun main() {
    val nums = intArrayOf(2, 5, 6, 0, 0, 1, 2)
    val target = 0

    val solution = Solution().search(nums, target)

    println(solution)
}

class Solution {
    // 시간 복잡도 : O(log N)
    // 공간 복잡도 : O(1)
    fun search(nums: IntArray, target: Int): Boolean {
        var left = 0
        var right = nums.lastIndex

        while (left <= right) {
            val mid = (left + right) / 2

            when {
                // 중간 값이 타겟 값인 경우 리턴
                nums[mid] == target -> return true
                // 중복 값인 경우
                nums[left] == nums[mid] && nums[mid] == nums[right] -> {
                    left++
                    right--
                }
                // 왼쪽이 정렬된 경우
                nums[left] <= nums[mid] -> {
                    if (target in nums[left]..nums[mid]) {
                        right = mid - 1
                    } else {
                        left = mid + 1
                    }
                }
                // 오른쪽이 정렬된 경우
                else -> {
                    if (target in nums[mid]..nums[right]) {
                        left = mid + 1
                    } else {
                        right = mid - 1
                    }
                }
            }
        }

        return false
    }

//    // 시간 복잡도 : O(N)
//    // 공간 복잡도 : O(1)
//    fun search(nums: IntArray, target: Int): Boolean {
//        return nums.contains(target)
//    }
}
