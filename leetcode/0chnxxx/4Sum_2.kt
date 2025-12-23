/**
 * Given four integer arrays nums1, nums2, nums3, and nums4 all of length n, return the number of tuples (i, j, k, l) such that:
 * 0 <= i, j, k, l < n
 * nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0
 *
 * Constraints:
 * n == nums1.length
 * n == nums2.length
 * n == nums3.length
 * n == nums4.length
 * 1 <= n <= 200
 * -228 <= nums1[i], nums2[i], nums3[i], nums4[i] <= 228
 */

fun main() {
    val nums1 = intArrayOf(1, 2)
    val nums2 = intArrayOf(-2, -1)
    val nums3 = intArrayOf(-1, 2)
    val nums4 = intArrayOf(0, 2)

    val result = Solution().fourSumCount(nums1, nums2, nums3, nums4)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N^2)
    // 공간 복잡도 : O(N^2)
    fun fourSumCount(nums1: IntArray, nums2: IntArray, nums3: IntArray, nums4: IntArray): Int {
        // nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0 은 결합법칙이 가능
        // (nums1[i] + nums2[j]) + (nums3[k] + nums4[l]) == 0

        val map = HashMap<Int, Int>()

        // nums1[i] + nums2[j] 의 조합으로 먼저 빈도를 찾음
        for (a in nums1) {
            for (b in nums2) {
                val sum = a + b
                map[sum] = map.getOrDefault(sum, 0) + 1
            }
        }

        var count = 0

        // nums3[k] + nums4[l] 의 조합으로 합이 0이 되는지 확인함
        for (c in nums3) {
            for (d in nums4) {
                val target = -(c + d)

                count += map.getOrDefault(target, 0)
            }
        }

        return count
    }
}