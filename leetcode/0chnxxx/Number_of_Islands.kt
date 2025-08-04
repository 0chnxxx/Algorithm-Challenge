/**
 * Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 *
 * Constraints:
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * grid[i][j] is '0' or '1'.
 */

fun main() {
    val grid = arrayOf(
        charArrayOf('1', '1', '1', '1', '0'),
        charArrayOf('1', '1', '0', '1', '0'),
        charArrayOf('1', '1', '0', '0', '0'),
        charArrayOf('0', '0', '0', '0', '0')
    )

    val result = Solution().numIslands(grid)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(M * N)
    // 공간 복잡도 : O(M * N)
    fun numIslands(grid: Array<CharArray>): Int {
        val m = grid.size
        val n = grid[0].size
        var count = 0

        val directions = listOf(
            Pair(0, 1),
            Pair(1, 0),
            Pair(0, -1),
            Pair(-1, 0)
        )

        fun bfs(x: Int, y: Int) {
            val queue = ArrayDeque<Pair<Int, Int>>()

            queue.add(Pair(x, y))

            grid[x][y] = '0'

            while (queue.isNotEmpty()) {
                val (currentX, currentY) = queue.removeFirst()

                for ((dx, dy) in directions) {
                    val nx = currentX + dx
                    val ny = currentY + dy

                    if (nx in 0 until m && ny in 0 until n && grid[nx][ny] == '1') {
                        grid[nx][ny] = '0'
                        queue.add(Pair(nx, ny))
                    }
                }
            }
        }

        for (i in 0 until m) {
            for (j in 0 until n) {
                if (grid[i][j] == '1') {
                    bfs(i, j)
                    count++
                }
            }
        }

        return count
    }
}