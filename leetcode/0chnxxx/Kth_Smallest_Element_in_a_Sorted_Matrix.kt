/**
 * Given an n x n matrix where each of the rows and columns is sorted in ascending order, return the kth smallest element in the matrix.
 * Note that it is the kth smallest element in the sorted order, not the kth distinct element.
 * You must find a solution with a memory complexity better than O(n2).
 *
 * Constraints:
 * n == matrix.length == matrix[i].length
 * 1 <= n <= 300
 * -109 <= matrix[i][j] <= 109
 * All the rows and columns of matrix are guaranteed to be sorted in non-decreasing order.
 * 1 <= k <= n2
 *
 * Follow up:
 * Could you solve the problem with a constant memory (i.e., O(1) memory complexity)?
 * Could you solve the problem in O(n) time complexity? The solution may be too advanced for an interview but you may find reading this paper fun.
 */

fun main() {
    val matrix = arrayOf(
        intArrayOf(1, 5, 9),
        intArrayOf(10, 11, 13),
        intArrayOf(12, 13, 15)
    )

    val k = 8

    val result = Solution().kthSmallest(matrix, k)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N * logN * log(maxVal - minVal))
    // 공간 복잡도 : O(1)
    fun kthSmallest(matrix: Array<IntArray>, k: Int): Int {
        val n = matrix.size
        var left = matrix[0][0]
        var right = matrix[n - 1][n - 1]

        fun countLessEqual(row: IntArray, target: Int): Int {
            var l = 0
            var r = row.size

            while (l < r) {
                val m = (l + r) / 2

                if (row[m] <= target) {
                    l = m + 1
                } else {
                    r = m
                }
            }

            return l
        }

        while (left < right) {
            val mid = left + (right - left) / 2
            var count = 0

            for (i in 0 until n) {
                count += countLessEqual(matrix[i], mid)
            }

            if (count < k) {
                left = mid + 1
            } else {
                right = mid
            }
        }

        return left
    }
}