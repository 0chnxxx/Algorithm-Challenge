/**
 * You are given an m x n integer array grid.
 * There is a robot initially located at the top-left corner (i.e., grid[0][0]).
 * The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]).
 * The robot can only move either down or right at any point in time.
 *
 * An obstacle and space are marked as 1 or 0 respectively in grid.
 * A path that the robot takes cannot include any square that is an obstacle.
 *
 * Return the number of possible unique paths that the robot can take to reach the bottom-right corner.
 *
 * The testcases are generated so that the answer will be less than or equal to 2 * 10^9.
 *
 * m == obstacleGrid.length
 * n == obstacleGrid[i].length
 * 1 <= m, n <= 100
 * obstacleGrid[i][j] is 0 or 1.
 */

fun main() {
    val obstacleGrid = arrayOf(
        intArrayOf(0, 1, 0, 0)
    )

    val solution = Solution().uniquePathsWithObstacles(obstacleGrid)

    println(solution)
}

class Solution {
    // 시간 복잡도 : O(M * N)
    // 공간 복잡도 : O(M * N)
    fun uniquePathsWithObstacles(obstacleGrid: Array<IntArray>): Int {
        if (obstacleGrid[0][0] == 1) return 0

        val m = obstacleGrid.size
        val n = obstacleGrid[0].size
        val dp = Array(m) { IntArray(n) { 0 } }

        // 첫번째 열은 1가지 방법뿐
        for (i in 0 until m) {
            if (obstacleGrid[i][0] == 1) break

            dp[i][0] = 1
        }

        // 첫번째 열은 1가지 방법뿐
        for (j in 0 until n) {
            if (obstacleGrid[0][j] == 1) break

            dp[0][j] = 1
        }

        // 첫번째 열과 행을 통해 다른 칸들의 경로를 구함
        for (i in 1 until m) {
            for (j in 1 until n) {
                if (obstacleGrid[i][j] != 1) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
                }
            }
        }

        // (m - 1, n - 1) 의 결과를 반환함
        return dp[m - 1][n - 1]
    }
}
