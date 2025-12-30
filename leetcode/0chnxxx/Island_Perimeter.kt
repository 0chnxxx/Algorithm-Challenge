/**
 * You are given row x col grid representing a map where grid[i][j] = 1 represents land and grid[i][j] = 0 represents water.
 * Grid cells are connected horizontally/vertically (not diagonally).
 * The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells).
 * The island doesn't have "lakes", meaning the water inside isn't connected to the water around the island.
 * One cell is a square with side length 1.
 * The grid is rectangular, width and height don't exceed 100.
 * Determine the perimeter of the island.
 *
 * Constraints:
 * row == grid.length
 * col == grid[i].length
 * 1 <= row, col <= 100
 * grid[i][j] is 0 or 1.
 * There is exactly one island in grid.
 */

fun main() {
    val grid = arrayOf(
        intArrayOf(0, 1, 0, 0),
        intArrayOf(1, 1, 1, 0),
        intArrayOf(0, 1, 0, 0),
        intArrayOf(1, 1, 0, 0)
    )

    val result = Solution().islandPerimeter(grid)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N * M)
    // 공간 복잡도 : O(1)
    fun islandPerimeter(grid: Array<IntArray>): Int {
        var perimeter = 0

        for (i in grid.indices) {
            for (j in grid[0].indices) {
                if (grid[i][j] == 1) {
                    perimeter += 4
                    if (i > 0 && grid[i-1][j] == 1) perimeter--
                    if (i < grid.size - 1 && grid[i+1][j] == 1) perimeter--
                    if (j > 0 && grid[i][j-1] == 1) perimeter--
                    if (j < grid[0].size - 1 && grid[i][j+1] == 1) perimeter--
                }
            }
        }

        return perimeter
    }
}