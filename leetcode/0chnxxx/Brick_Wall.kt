/**
 * There is a rectangular brick wall in front of you with n rows of bricks.
 * The ith row has some number of bricks each of the same height (i.e., one unit) but they can be of different widths.
 * The total width of each row is the same.
 * Draw a vertical line from the top to the bottom and cross the least bricks.
 * If your line goes through the edge of a brick, then the brick is not considered as crossed.
 * You cannot draw a line just along one of the two vertical edges of the wall, in which case the line will obviously cross no bricks.
 * Given the 2D array wall that contains the information about the wall, return the minimum number of crossed bricks after drawing such a vertical line.
 *
 * Constraints:
 * n == wall.length
 * 1 <= n <= 10^4
 * 1 <= wall[i].length <= 10^4
 * 1 <= sum(wall[i].length) <= 2 * 10^4
 * sum(wall[i]) is the same for each row i.
 * 1 <= wall[i][j] <= 2^31 - 1
 */

fun main() {
    val wall = listOf(
        listOf(1, 2, 2, 1),
        listOf(3, 1, 2),
        listOf(1, 3, 2),
        listOf(2, 4),
        listOf(3, 1, 2),
        listOf(1, 3, 1, 1)
    )

    val result = Solution().leastBricks(wall)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(N)
    fun leastBricks(wall: List<List<Int>>): Int {
        val map = HashMap<Int, Int>()

        for (row in wall) {
            var sum = 0

            for (i in 0 until row.size - 1) {
                sum += row[i]
                map[sum] = (map[sum] ?: 0) + 1
            }
        }

        val maxEdge = map.values.maxOrNull() ?: 0

        return wall.size - maxEdge
    }
}