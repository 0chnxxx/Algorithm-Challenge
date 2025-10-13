/**
 * Given two integer arrays nums1 and nums2, return an array of their intersection.
 * Each element in the result must be unique and you may return the result in any order.
 *
 * Constraints:
 * 1 <= nums1.length, nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 1000
 */

fun main() {
    val nums1 = intArrayOf(1, 2, 2, 1)
    val nums2 = intArrayOf(2, 2)

    val result = Solution().intersection(nums1, nums2)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N log N + M log M)
    // 공간 복잡도 : O(1)
    fun intersection(nums1: IntArray, nums2: IntArray): IntArray {
        nums1.sort()
        nums2.sort()

        var i = 0
        var j = 0

        val result = mutableListOf<Int>()

        while (i < nums1.size && j < nums2.size) {
            when {
                nums1[i] == nums2[j] -> {
                    if (result.isEmpty() || result.last() != nums1[i]) {
                        result.add(nums1[i])
                    }

                    i++
                    j++
                }
                nums1[i] < nums2[j] -> i++
                else -> j++
            }
        }

        return result.toIntArray()
    }

//    // 시간 복잡도 : O(N + M)
//    // 공간 복잡도 : O(N + M)
//    fun intersection(nums1: IntArray, nums2: IntArray): IntArray {
//        return nums1.intersect(nums2.toList()).toIntArray()
//    }
}
