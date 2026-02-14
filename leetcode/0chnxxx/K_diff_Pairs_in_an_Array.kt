/**
 * Given an array of integers nums and an integer k, return the number of unique k-diff pairs in the array.
 * A k-diff pair is an integer pair (nums[i], nums[j]), where the following are true:
 * 0 <= i, j < nums.length
 * i != j
 * |nums[i] - nums[j]| == k
 * Notice that |val| denotes the absolute value of val.
 *
 * Constraints:
 * 1 <= nums.length <= 10^4
 * -10^7 <= nums[i] <= 10^7
 * 0 <= k <= 10^7
 */

fun main() {
    val nums = intArrayOf(3, 1, 4, 1, 5)
    val k = 2

    val result = Solution().findPairs(nums, k)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(N)
    fun findPairs(nums: IntArray, k: Int): Int {
        if (k < 0) return 0

        val countMap = mutableMapOf<Int, Int>()

        // 숫자 등장 횟수 저장
        for (num in nums) {
            countMap[num] = countMap.getOrDefault(num, 0) + 1
        }

        var result = 0

        if (k == 0) {
            // 같은 숫자가 2번 이상 등장한 경우만 카운트
            for ((_, count) in countMap) {
                if (count >= 2) result++
            }
        } else {
            // num + k 가 존재하는지 확인
            for (num in countMap.keys) {
                if (countMap.containsKey(num + k)) {
                    result++
                }
            }
        }

        return result
    }
}
