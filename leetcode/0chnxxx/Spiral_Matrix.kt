/**
 * Given an m x n matrix, return all elements of the matrix in spiral order.
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 10
 * -100 <= matrix[i][j] <= 100
 */

fun main() {
    val matrix = arrayOf(
        intArrayOf(23,18,20,26,25),
        intArrayOf(24,22,3,4,4),
        intArrayOf(15,22,2,24,29),
        intArrayOf(18,15,23,28,28)
    )

    val solution = Solution().spiralOrder(matrix)

    println(solution)
}

class Solution {
    // 시간 복잡도 : O(N * M)
    // 공간 복잡도 : O(N * M)
    fun spiralOrder(matrix: Array<IntArray>): List<Int> {
        // 미리 공간을 선언해두면 resize 를 최적화 할 수 있음
        // val result = mutableListOf<Int>()
        val result = ArrayList<Int>(matrix.size * matrix[0].size)


        if (matrix.isEmpty()) return result

        var top = 0
        var bottom = matrix.size - 1
        var left = 0
        var right = matrix[0].size - 1

        while (top <= bottom && left <= right) {
            // 왼쪽 -> 오른쪽
            for (i in left..right) {
                result.add(matrix[top][i])
            }

            top++

            // 위 -> 아래
            for (i in top..bottom) {
                result.add(matrix[i][right])
            }

            right--

            // 오른쪽 -> 왼쪽
            if (top <= bottom) {
                for (i in right downTo left) {
                    result.add(matrix[bottom][i])
                }

                bottom--
            }

            // 아래 -> 위
            if (left <= right) {
                for (i in bottom downTo top) {
                    result.add(matrix[i][left])
                }

                left++
            }
        }

        return result
    }
}
