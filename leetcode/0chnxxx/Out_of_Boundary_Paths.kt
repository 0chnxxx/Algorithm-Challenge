/**
 * There is an m x n grid with a ball.
 * The ball is initially at the position [startRow, startColumn].
 * You are allowed to move the ball to one of the four adjacent cells in the grid (possibly out of the grid crossing the grid boundary).
 * You can apply at most maxMove moves to the ball.
 * Given the five integers m, n, maxMove, startRow, startColumn, return the number of paths to move the ball out of the grid boundary.
 * Since the answer can be very large, return it modulo 109 + 7.
 *
 * Constraints:
 * 1 <= m, n <= 50
 * 0 <= maxMove <= 50
 * 0 <= startRow < m
 * 0 <= startColumn < n
 */

fun main() {
    val m = 2
    val n = 2
    val maxMove = 2
    val startRow = 0
    val startColumn = 0

    val result = Solution().findPaths(m, n, maxMove, startRow, startColumn)

    println(result)
}

class Solution {
    val MOD = 1_000_000_007

    lateinit var memo: Array<Array<IntArray>>
    val directions = arrayOf(
        intArrayOf(1, 0),
        intArrayOf(-1, 0),
        intArrayOf(0, 1),
        intArrayOf(0, -1),
    )

    // 시간 복잡도 : O(M * N * maxMove)
    // 공간 복잡도 : O(M * N * maxMove)
    fun findPaths(m: Int, n: Int, maxMove: Int, startRow: Int, startColumn: Int): Int {
        memo = Array(m) { Array(n) { IntArray(maxMove + 1) { -1 } } }

        return dfs(m, n, startRow, startColumn, maxMove)
    }

    private fun dfs(m: Int, n: Int, r: Int, c: Int, move: Int): Int {
        if (r < 0 || c < 0 || r >= m || c >= n) {
            return 1
        }

        if (move == 0) {
            return 0
        }

        if (memo[r][c][move] != -1) {
            return memo[r][c][move]
        }

        var count = 0L

        for (d in directions) {
            val nr = r + d[0]
            val nc = c + d[1]

            count += dfs(m, n, nr, nc, move - 1)
            count %= MOD
        }

        memo[r][c][move] = count.toInt()

        return memo[r][c][move]
    }
}