/**
 * Given two integer arrays nums1 and nums2, return an array of their intersection.
 * Each element in the result must appear as many times as it shows in both arrays and you may return the result in any order.
 *
 * Constraints:
 * 1 <= nums1.length, nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 1000
 *
 *
 * Follow up:
 * What if the given array is already sorted? How would you optimize your algorithm?
 * What if nums1's size is small compared to nums2's size? Which algorithm is better?
 * What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
 */

fun main() {
    val nums1 = intArrayOf(1, 2, 2, 1)
    val nums2 = intArrayOf(2, 2)

    val result = Solution().intersect(nums1, nums2)

    println(result.joinToString(", "))
}

class Solution {
    // 시간 복잡도 : O(N log N + M log M)
    // 공간 복잡도 : O(1)
    fun intersect(nums1: IntArray, nums2: IntArray): IntArray {
        nums1.sort()
        nums2.sort()

        val result = mutableListOf<Int>()

        var i = 0
        var j = 0

        while (i < nums1.size && j < nums2.size) {
            when {
                nums1[i] == nums2[j] -> {
                    result.add(nums1[i])

                    i++
                    j++
                }
                nums1[i] < nums2[j] -> i++
                else -> j++
            }
        }

        return result.toIntArray()
    }
}