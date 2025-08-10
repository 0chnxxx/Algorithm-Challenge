/**
 * Given an integer array nums and an integer k, return the kth largest element in the array.
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * Can you solve it without sorting?
 *
 * Constraints:
 * 1 <= k <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 */

fun main() {
    val nums = intArrayOf(3, 2, 1, 5, 6, 4)
    val k = 2

    val result = Solution().findKthLargest(nums, k)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun findKthLargest(nums: IntArray, k: Int): Int {
        val offset = 10000 // 음수 보정
        val array = IntArray(20001) // -10^4 ~ 10^4 범위

        // 갯수 저장
        for (num in nums) {
            array[num + offset]++
        }

        var target = k
        var point = array.lastIndex // 가장 큰 값부터 시작

        while (target > 0 && point >= 0) {
            val count = array[point]

            // 값이 없는 경우 다음 작은 값으로 이동
            if (count == 0) {
                point--
                continue
            }

            // 값이 있는 경우 해당 값을 소비
            target -= count

            // 전부 소비된 경우 결과값 반환
            if (target <= 0) {
                return point - offset // offset을 제거해서 실제 값으로 변환
            }

            point--
        }

        return -1
    }
}
