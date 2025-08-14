import java.util.Queue
import kotlin.math.max
import kotlin.math.sin

/**
 * Given an m x n binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
 *
 * Constraints:
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 300
 * matrix[i][j] is '0' or '1'.
 */

fun main() {
    val matrix = arrayOf(
        charArrayOf('1', '0', '1', '0', '0'),
        charArrayOf('1', '0', '1', '1', '1'),
        charArrayOf('1', '1', '1', '1', '1'),
        charArrayOf('1', '0', '0', '1', '0'),
    )

    val result = Solution().maximalSquare(matrix)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(M * N)
    // 공간 복잡도 : O(M * N)
    fun maximalSquare(matrix: Array<CharArray>): Int {
        if (matrix.isEmpty()) return 0

        val row = matrix.size
        val column = matrix[0].size

        var max = 0
        val dp = Array(row) { IntArray(column) }

        for (i in 0 until row) {
            for (j in 0 until column) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1
                    } else {
                        dp[i][j] = minOf(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1]) + 1
                    }

                    max = maxOf(max, dp[i][j])
                }
            }
        }

        return max * max
    }
}