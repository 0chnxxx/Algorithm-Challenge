/**
 * Suppose an array of length n sorted in ascending order is rotated between 1 and n times.
 * For example, the array nums = [0,1,2,4,5,6,7] might become:
 *
 * [4,5,6,7,0,1,2] if it was rotated 4 times.
 * [0,1,2,4,5,6,7] if it was rotated 7 times.
 *
 * Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].
 *
 * Given the sorted rotated array nums of unique elements, return the minimum element of this array.
 * You must write an algorithm that runs in O(log n) time.
 *
 * Constraints:
 * n == nums.length
 * 1 <= n <= 5000
 * -5000 <= nums[i] <= 5000
 * All the integers of nums are unique.
 * nums is sorted and rotated between 1 and n times.
 */

fun main() {
    val nums = intArrayOf(11, 13, 15, 17)

    val result = Solution().findMin(nums)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(log N)
    // 공간 복잡도 : O(1)
    fun findMin(nums: IntArray): Int {
        var left = 0
        var right = nums.lastIndex

        // 이미 정렬된 경우 얼리 리턴
        if (nums[left] < nums[right]) return nums[left]

        // 이진 탐색
        while (left < right) {
            val mid = (left + right) ushr 1

            if (nums[mid] > nums[right]) {
                left = mid + 1
            } else {
                right = mid
            }
        }

        return nums[left]
    }

//    // 시간 복잡도 : O(N)
//    // 공간 복잡도 : O(1)
//    fun findMin(nums: IntArray): Int {
//        return nums.min()
//    }
}