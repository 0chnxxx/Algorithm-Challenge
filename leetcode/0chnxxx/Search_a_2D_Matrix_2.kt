/**
 * Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix.
 *
 * This matrix has the following properties:
 * Integers in each row are sorted in ascending from left to right.
 * Integers in each column are sorted in ascending from top to bottom.
 *
 * Constraints:
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= n, m <= 300
 * -10^9 <= matrix[i][j] <= 10^9
 * All the integers in each row are sorted in ascending order.
 * All the integers in each column are sorted in ascending order.
 * -10^9 <= target <= 10^9
 */

fun main() {
    val matrix = arrayOf(
        intArrayOf(1, 4, 7, 11, 15),
        intArrayOf(2, 5, 8, 12, 19),
        intArrayOf(3, 6, 9, 16, 22),
        intArrayOf(10, 13, 14, 17, 24),
        intArrayOf(18, 21, 23, 26, 30)
    )
    val target = 5

    val result = Solution().searchMatrix(matrix, target)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(M + N)
    // 공간 복잡도 : O(1)
    fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
        val m = matrix.size
        val n = matrix[0].size

        // 우측 상단에서 시작
        var row = 0
        var column = n - 1


        while (row < m && column >= 0) {
            val value = matrix[row][column]

            when {
                value == target -> return true
                value > target -> column-- // 왼쪽으로 이동
                else -> row++ // 아래로 이동
            }
        }

        return false
    }
}
