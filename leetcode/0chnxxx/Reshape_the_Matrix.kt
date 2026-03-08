/**
 * In MATLAB, there is a handy function called reshape which can reshape an m x n matrix into a new one with a different size r x c keeping its original data.
 * You are given an m x n matrix mat and two integers r and c representing the number of rows and the number of columns of the wanted reshaped matrix.
 * The reshaped matrix should be filled with all the elements of the original matrix in the same row-traversing order as they were.
 * If the reshape operation with given parameters is possible and legal, output the new reshaped matrix; Otherwise, output the original matrix.
 *
 * Constraints:
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 100
 * -1000 <= mat[i][j] <= 1000
 * 1 <= r, c <= 300
 */

fun main() {
    val mat = arrayOf(
        intArrayOf(1, 2),
        intArrayOf(3, 4)
    )
    val r = 1
    val c = 4

    val result = Solution().matrixReshape(mat, r, c)

    println(result)
}

class Solution {
//    // 시간 복잡도 : O(N * M)
//    // 공간 복잡도 : O(N * M)
//    fun matrixReshape(mat: Array<IntArray>, r: Int, c: Int): Array<IntArray> {
//        val m = mat.size
//        val n = mat[0].size
//
//        if (m * n != r * c) return mat
//
//        val result = Array(r) { IntArray(c) }
//
//        for (i in 0 until m) {
//            for (j in 0 until n) {
//                val index = i * n + j
//                val newRow = index / c
//                val newColumn = index % c
//
//                result[newRow][newColumn] = mat[i][j]
//            }
//        }
//
//        return result
//    }

    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(N * M)
    fun matrixReshape(mat: Array<IntArray>, r: Int, c: Int): Array<IntArray> {
        val m = mat.size
        val n = mat[0].size

        if (m * n != r * c) return mat

        val result = Array(r) { IntArray(c) }

        for (i in 0 until m * n) {
            result[i / c][i % c] = mat[i / n][i % n]
        }

        return result
    }
}
