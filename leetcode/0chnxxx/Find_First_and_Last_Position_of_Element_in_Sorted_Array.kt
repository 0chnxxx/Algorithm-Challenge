/**
 * Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.
 *
 * If target is not found in the array, return [-1, -1].
 *
 * You must write an algorithm with O(log n) runtime complexity.
 *
 * 0 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * nums is a non-decreasing array.
 * -109 <= target <= 109
 */

fun main() {
    val nums = intArrayOf(5, 7, 7, 8, 8, 10)
    val target = 8

    val solution = Solution().searchRange(nums, target)

    println(solution)
}

class Solution {
    // 시간 복잡도 : O(log N)
    // 공간 복잡도 : O(1)
    fun searchRange(nums: IntArray, target: Int): IntArray {
        // size 2짜리의 IntArray를 통한 최적화
        val result = IntArray(2) { -1 }

        fun binarySearch(): Int {
            var left = 0
            var right = nums.size - 1
            var index = -1

            while (left <= right) {
                val mid = (left + right) / 2

                when {
                    nums[mid] < target -> left = mid + 1
                    nums[mid] > target -> right = mid - 1
                    else -> {
                        index = mid

                        if (result[0] == -1) {
                            right = mid - 1
                        } else {
                            left = mid + 1
                        }
                    }
                }
            }

            return index
        }

        val first = binarySearch()

        // early return을 통한 최적화
        if (first == -1) {
            return intArrayOf(-1, -1)
        }

        result[0] = first

        val second = binarySearch()

        result[1] = second

        return result
    }

//    // 시간 복잡도 : O(N)
//    // 공간 복잡도 : O(1)
//    fun searchRange(nums: IntArray, target: Int): IntArray {
//        if (!nums.contains(target)) return intArrayOf(-1, -1)
//
//        return intArrayOf(nums.indexOfFirst { it == target }, nums.indexOfLast { it == target })
//    }
}
