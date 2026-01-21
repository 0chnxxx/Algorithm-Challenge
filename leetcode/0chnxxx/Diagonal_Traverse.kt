/**
 * Given an m x n matrix mat, return an array of all the elements of the array in a diagonal order.
 *
 * Constraints:
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 10^4
 * 1 <= m * n <= 10^4
 * -10^5 <= mat[i][j] <= 10^5
 */

fun main() {
    val mat = arrayOf(
        intArrayOf(1, 2, 3),
        intArrayOf(4, 5, 6),
        intArrayOf(7, 8, 9)
    )

    val result = Solution().findDiagonalOrder(mat)

    println(result.joinToString(", "))
}

class Solution {
    // 시간 복잡도 : O(N * M)
    // 공간 복잡도 : O(1)
    fun findDiagonalOrder(mat: Array<IntArray>): IntArray {
        val m = mat.size
        val n = mat[0].size
        val result = IntArray(m * n)

        var r = 0
        var c = 0
        var dir = 1
        var idx = 0

        while (idx < m * n) {
            result[idx++] = mat[r][c]

            if (dir == 1) {
                if (c == n - 1) {
                    r++
                    dir = -1
                } else if (r == 0) {
                    c++
                    dir = -1
                } else {
                    r--
                    c++
                }
            } else {
                if (r == m - 1) {
                    c++
                    dir = 1
                } else if (c == 0) {
                    r++
                    dir = 1
                } else {
                    r++
                    c--
                }
            }
        }

        return result
    }
}