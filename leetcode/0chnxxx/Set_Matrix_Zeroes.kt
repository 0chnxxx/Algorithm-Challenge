/**
 * Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's.
 *
 * You must do it in place.
 *
 * Constraints:
 * m == matrix.length
 * n == matrix[0].length
 * 1 <= m, n <= 200
 * -2^31 <= matrix[i][j] <= 2^31 - 1
 */

fun main() {
    val matrix = arrayOf(
        intArrayOf(1, 1, 1),
        intArrayOf(1, 0, 1),
        intArrayOf(1, 1, 1)
    )

    Solution().setZeroes(matrix)

    matrix.forEach { println(it.joinToString(", ")) }
}

class Solution {
    // 시간 복잡도 : O(N * M)
    // 공간 복잡도 : O(1)
    fun setZeroes(matrix: Array<IntArray>): Unit {
        val n = matrix.size
        val m = matrix[0].size

        var hasZeroInRow = false
        var hasZeroInColumn = false

        // 첫번째 행에 0이 있는지 확인
        for (i in 0 until n) {
            if (matrix[i][0] == 0) hasZeroInColumn = true
        }

        // 첫번째 열에 0이 있는지 확인
        for (j in 0 until m) {
            if (matrix[0][j] == 0) hasZeroInRow = true
        }

        // 첫번째 행, 열을 제외한 나머지 셀에 0이 있는지 확인
        // 0이 있다면 해당 셀의 첫번재 행과 열에 0을 마킹
        for (i in 1 until n) {
            for (j in 1 until m) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0
                    matrix[0][j] = 0
                }
            }
        }

        // 첫번째 행과 열에 0 마킹을 확인하여 해당 행과 열에 속하는 모든 셀을 0으로 변경
        for (i in 1 until n) {
            for (j in 1 until m) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0
                }
            }
        }

        // 첫번째 행에 0이 존재한다면 해당 행의 모든 셀을 0으로 변경
        if (hasZeroInColumn) {
            for (i in 0 until n) {
                matrix[i][0] = 0
            }
        }

        // 첫번째 열에 0이 존재한다면 해당 열의 모든 셀을 0으로 변경
        if (hasZeroInRow) {
            for (j in 0 until m) {
                matrix[0][j] = 0
            }
        }
    }

//    // 시간 복잡도 : O(N * M)
//    // 공간 복잡도 : O(K)
//    fun setZeroes(matrix: Array<IntArray>): Unit {
//        val zero = mutableListOf<Pair<Int, Int>>()
//
//        for (i in matrix.indices) {
//            for (j in matrix[i].indices) {
//                if (matrix[i][j] == 0) {
//                    zero.add(Pair(i, j))
//                }
//            }
//        }
//
//        val x = zero.map { it.first }
//        val y = zero.map { it.second }
//
//        for (i in matrix.indices) {
//            for (j in matrix[i].indices) {
//                if (x.contains(i) || y.contains(j)) {
//                    matrix[i][j] = 0
//                }
//            }
//        }
//    }
}
