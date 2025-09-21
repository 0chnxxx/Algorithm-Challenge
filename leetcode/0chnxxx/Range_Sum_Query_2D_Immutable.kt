/**
 * Given a 2D matrix matrix, handle multiple queries of the following type:
 * Calculate the sum of the elements of matrix inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
 *
 * Implement the NumMatrix class:
 * NumMatrix(int[][] matrix) Initializes the object with the integer matrix matrix.
 * int sumRegion(int row1, int col1, int row2, int col2) Returns the sum of the elements of matrix inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
 * You must design an algorithm where sumRegion works on O(1) time complexity.
 *
 * Constraints:
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 200
 * -10^4 <= matrix[i][j] <= 10^4
 * 0 <= row1 <= row2 < m
 * 0 <= col1 <= col2 < n
 * At most 10^4 calls will be made to sumRegion.
 */

fun main() {
    val array = arrayOf(
        intArrayOf(3, 0, 1, 4, 2),
        intArrayOf(5, 6, 3, 2, 1),
        intArrayOf(1, 2, 0, 1, 5),
        intArrayOf(4, 1, 0, 1, 7),
        intArrayOf(1, 0, 3, 0, 5)
    )

    val numMatrix = NumMatrix(array)

    println(numMatrix.sumRegion(2, 1, 4, 3)) // return 8 (i.e sum of the red rectangle)
    println(numMatrix.sumRegion(1, 1, 2, 2)) // return 11 (i.e sum of the green rectangle)
    println(numMatrix.sumRegion(1, 2, 2, 4)) // return 12 (i.e sum of the blue rectangle)
}

class NumMatrix(matrix: Array<IntArray>) {
    private val prefixSum: Array<IntArray>

    init {
        val m = matrix.size
        val n = matrix[0].size

        prefixSum = Array(m + 1) { IntArray(n + 1) }

        for (r in 1..m) {
            for (c in 1..n) {
                // matrix[r - 1][c - 1] : 현재 칸 값
                // prefixSum[r - 1][c] : 위쪽 칸
                // prefixSum[r][c - 1] : 왼쪽 칸
                // prefixSum[r - 1][c - 1] : 중복되는 칸 (빼줌)
                prefixSum[r][c] =
                    matrix[r - 1][c - 1] + prefixSum[r - 1][c] + prefixSum[r][c - 1] - prefixSum[r - 1][c - 1]
            }
        }
    }

    // 시간 복잡도 : O(1)
    // 공간 복잡도 : O(N * M)
    fun sumRegion(row1: Int, col1: Int, row2: Int, col2: Int): Int {
        return prefixSum[row2 + 1][col2 + 1] - prefixSum[row1][col2 + 1] - prefixSum[row2 + 1][col1] + prefixSum[row1][col1]
    }
}
