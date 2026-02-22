/**
 * Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.
 * The distance between two cells sharing a common edge is 1.
 *
 * Constraints:
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 10^4
 * 1 <= m * n <= 10^4
 * mat[i][j] is either 0 or 1.
 * There is at least one 0 in mat.
 *
 * Note:
 * This question is the same as 1765: https://leetcode.com/problems/map-of-highest-peak/
 */

fun main() {
    val mat = arrayOf(
        intArrayOf(0, 0, 0),
        intArrayOf(0, 1, 0),
        intArrayOf(0, 0, 0),
    )

    val result = Solution().updateMatrix(mat)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(M * N)
    // 공간 복잡도 : O(M * N)
    fun updateMatrix(mat: Array<IntArray>): Array<IntArray> {
        val m = mat.size
        val n = mat[0].size

        val distance = Array(m) { IntArray(n) { Int.MAX_VALUE } }
        val queue = ArrayDeque<Pair<Int, Int>>()

        for (i in 0 until m) {
            for (j in 0 until n) {
                if (mat[i][j] == 0) {
                    distance[i][j] = 0
                    queue.add(i to j)
                }
            }
        }

        val directions = arrayOf(
            intArrayOf(1, 0),
            intArrayOf(-1, 0),
            intArrayOf(0, 1),
            intArrayOf(0, -1)
        )

        while (queue.isNotEmpty()) {
            val (x, y) = queue.removeFirst()

            for (d in directions) {
                val nx = x + d[0]
                val ny = y + d[1]

                if (nx !in 0 until m || ny !in 0 until n) continue

                if (distance[nx][ny] > distance[x][y] + 1) {
                    distance[nx][ny] = distance[x][y] + 1
                    queue.add(nx to ny)
                }
            }
        }

        return distance
    }
}
