/**
 * You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
 *
 * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly.
 * DO NOT allocate another 2D matrix and do the rotation.
 *
 * n == matrix.length == matrix[i].length
 * 1 <= n <= 20
 * -1000 <= matrix[i][j] <= 1000
 */

fun main() {
    val matrix = arrayOf(
        intArrayOf(1, 2, 3),
        intArrayOf(4, 5, 6),
        intArrayOf(7, 8, 9)
    )

    Solution().rotate(matrix)

    println(matrix)
}

class Solution {
    // 시간 복잡도 : O(N^2)
    // 공간 복잡도 : O(1)
    // swap과 reverse를 통한 in-place 방식으로 공간 복잡도 최적화
    // reverse 때문에 시간은 더 느림
    fun rotate(matrix: Array<IntArray>): Unit {
        val n = matrix.size

        for (i in 0 until n) {
            for (j in i + 1 until n) {
                val temp = matrix[i][j]
                matrix[i][j] = matrix[j][i]
                matrix[j][i] = temp
            }
        }

        for (i in 0 until n) {
            matrix[i].reverse()
        }
    }

//    // 시간 복잡도 : O(N^2)
//    // 공간 복잡도 : O(N^2)
//    fun rotate(matrix: Array<IntArray>): Unit {
//        val n = matrix.size - 1
//        val copy = Array(matrix.size) { i -> matrix[i].copyOf() }
//
//        for (i in matrix.indices) {
//            for (j in matrix[i].indices) {
//                matrix[i][j] = copy[n - j][i]
//            }
//        }
//    }
}
