/**
 * The next greater element of some element x in an array is the first greater element that is to the right of x in the same array.
 * You are given two distinct 0-indexed integer arrays nums1 and nums2, where nums1 is a subset of nums2.
 * For each 0 <= i < nums1.length, find the index j such that nums1[i] == nums2[j] and determine the next greater element of nums2[j] in nums2. If there is no next greater element, then the answer for this query is -1.
 * Return an array ans of length nums1.length such that ans[i] is the next greater element as described above.
 *
 * Constraints:
 * 1 <= nums1.length <= nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 10^4
 * All integers in nums1 and nums2 are unique.
 * All the integers of nums1 also appear in nums2.
 *
 * Follow up:
 * Could you find an O(nums1.length + nums2.length) solution?
 */

fun main() {
    val nums1 = intArrayOf(4, 1, 2)
    val nums2 = intArrayOf(1, 3, 4, 2)

    val result = Solution().nextGreaterElement(nums1, nums2)

    println(result.joinToString(", "))
}

class Solution {
    // 시간 복잡도 : O(nums1.length + nums2.length)
    // 공간 복잡도 : O(nums1.length + nums2.length)
    fun nextGreaterElement(nums1: IntArray, nums2: IntArray): IntArray {
        val nextGreater = HashMap<Int, Int>()
        val stack = ArrayDeque<Int>()

        for (num in nums2) {
            while (stack.isNotEmpty() && num > stack.first()) {
                val smaller = stack.removeFirst()
                nextGreater[smaller] = num
            }

            stack.addFirst(num)
        }

        while (stack.isNotEmpty()) {
            nextGreater[stack.removeFirst()] = -1
        }

        val result = IntArray(nums1.size)

        for (i in nums1.indices) {
            result[i] = nextGreater[nums1[i]]!!
        }

        return result
    }
}