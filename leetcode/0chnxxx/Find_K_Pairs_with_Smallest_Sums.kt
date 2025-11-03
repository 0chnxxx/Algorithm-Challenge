import java.util.PriorityQueue

/**
 * You are given two integer arrays nums1 and nums2 sorted in non-decreasing order and an integer k.
 * Define a pair (u, v) which consists of one element from the first array and one element from the second array.
 * Return the k pairs (u1, v1), (u2, v2), ..., (uk, vk) with the smallest sums.
 *
 * Constraints:
 * 1 <= nums1.length, nums2.length <= 10^5
 * -10^9 <= nums1[i], nums2[i] <= 10^9
 * nums1 and nums2 both are sorted in non-decreasing order.
 * 1 <= k <= 10^4
 * k <= nums1.length * nums2.length
 */

fun main() {
    val nums1 = intArrayOf(1, 7, 11)
    val nums2 = intArrayOf(2, 4, 6)
    val k = 3

    val result = Solution().kSmallestPairs(nums1, nums2, k)

    println(result.joinToString(", "))
}

class Solution {
    // 시간 복잡도 : O(N log N)
    // 공간 복잡도 : O(N)
    fun kSmallestPairs(nums1: IntArray, nums2: IntArray, k: Int): List<List<Int>> {
        val result = mutableListOf<List<Int>>()

        if (nums1.isEmpty() || nums2.isEmpty()) return result

        val queue = PriorityQueue(compareBy<Triple<Int, Int, Int>> { it.first + it.second })

        for (i in 0 until minOf(nums1.size, k)) {
            queue.offer(Triple(nums1[i], nums2[0], 0))
        }

        var count = 0

        while (queue.isNotEmpty() && count < k) {
            val (u, v, j) = queue.poll()

            result.add(listOf(u, v))

            count++

            if (j + 1 < nums2.size) {
                queue.offer(Triple(u, nums2[j + 1], j + 1))
            }
        }

        return result
    }
}