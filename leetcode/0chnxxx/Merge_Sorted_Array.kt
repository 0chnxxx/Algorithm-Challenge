/**
 * You are given two integer arrays nums1 and nums2, sorted in non-decreasing order, and two integers m and n, representing the number of elements in nums1 and nums2 respectively.
 *
 * Merge nums1 and nums2 into a single array sorted in non-decreasing order.
 *
 * The final sorted array should not be returned by the function, but instead be stored inside the array nums1.
 * To accommodate this, nums1 has a length of m + n, where the first m elements denote the elements that should be merged, and the last n elements are set to 0 and should be ignored.
 * nums2 has a length of n.
 *
 * Constraints:
 * nums1.length == m + n
 * nums2.length == n
 * 0 <= m, n <= 200
 * 1 <= m + n <= 200
 * -10^9 <= nums1[i], nums2[j] <= 10^9
 */

fun main() {
    val nums1 = intArrayOf(1, 2, 3, 0, 0, 0)
    val m = 3
    val nums2 = intArrayOf(2, 5, 6)
    val n = 3

    Solution().merge(nums1, m, nums2, n)

    println(nums1.joinToString(", "))
}

class Solution {
    // 시간 복잡도 : O(N + M)
    // 공간 복잡도 : O(1)
    fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int): Unit {
        // nums1 의 유효한 마지막
        var i1 = m - 1
        // nums2 의 마지막 인덱스
        var i2 = n - 1
        // nums1 의 마지막 인덱스
        var i = m + n - 1

        // nums1, nums2 가 아직 남아있다면 반복
        while (i1 >= 0 && i2 >= 0) {
            // nums1 과 nums2 중 더 큰 값을 nums1의 맨 끝부터 채운다.
            if (nums1[i1] > nums2[i2]) {
                nums1[i] = nums1[i1]
                i1--
            } else {
                nums1[i] = nums2[i2]
                i2--
            }

            // 다 채웠으니 인덱스 감소
            i--
        }

        // nums2 에 값이 남았다면 처리
        while (i2 >= 0) {
            nums1[i] = nums2[i2]
            i2--
            i--
        }
    }
}
