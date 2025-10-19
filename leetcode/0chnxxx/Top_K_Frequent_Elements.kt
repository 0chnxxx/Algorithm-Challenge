/**
 * Given an integer array nums and an integer k, return the k most frequent elements.
 * You may return the answer in any order.
 *
 * Constraints:
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * k is in the range [1, the number of unique elements in the array].
 * It is guaranteed that the answer is unique.
 *
 * Follow up:
 * Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 */

fun main() {
    val nums = intArrayOf(1, 1, 1, 2, 2, 3)
    val k = 2

    val result = Solution().topKFrequent(nums, k)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(N)
    fun topKFrequent(nums: IntArray, k: Int): IntArray {
        val frequency = mutableMapOf<Int, Int>()

        for (num in nums) {
            frequency[num] = frequency.getOrDefault(num, 0) + 1
        }

        val bucket = Array<MutableList<Int>>(nums.size + 1) { mutableListOf() }

        for ((num, frequency) in frequency) {
            bucket[frequency].add(num)
        }

        val result = mutableListOf<Int>()

        for (i in bucket.indices.reversed()) {
            for (num in bucket[i]) {
                result.add(num)

                if (result.size == k) return result.toIntArray()
            }
        }

        return result.toIntArray()
    }
}
