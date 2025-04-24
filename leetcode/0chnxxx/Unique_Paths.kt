import java.util.*

/**
 * There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]).
 * The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time.
 *
 * Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the bottom-right corner.
 *
 * The test cases are generated so that the answer will be less than or equal to 2 * 109.
 *
 * 1 <= m, n <= 100
 */

fun main() {
    val m = 3
    val n = 7

    val solution = Solution().uniquePaths(m, n)

    println(solution)
}

class Solution {
    // 시간 복잡도 : O(M * N)
    // 공간 복잡도 : O(M * N)
    fun uniquePaths(m: Int, n: Int): Int {
        val dp = Array(m) { IntArray(n) }

        // 첫번째 열은 1가지 방법뿐
        for (i in 0 until m) {
            dp[i][0] = 1
        }

        // 첫번째 열은 1가지 방법뿐
        for (j in 0 until n) {
            dp[0][j] = 1
        }

        // 첫번째 열과 행을 통해 다른 칸들의 경로를 구함
        for (i in 1 until m) {
            for (j in 1 until n) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
            }
        }

        // (m - 1, n - 1) 의 결과를 반환함
        return dp[m - 1][n - 1]
    }

//    // 시간 복잡도 : O(2^(M + N))
//    // 공간 복잡도 : O(M + N)
//    fun uniquePaths(m: Int, n: Int): Int {
//        var result = 0
//        val directions = arrayOf(
//            1 to 0,
//            0 to 1
//        )
//
//        fun dfs(x: Int, y: Int) {
//            if (x == m - 1 && y == n - 1) {
//                result++
//            }
//
//            for (direction in directions) {
//                val newX = x + direction.first
//                val newY = y + direction.second
//
//                if (newX !in 0 until m || newY !in 0 until n) continue
//
//                dfs(newX, newY)
//            }
//        }
//
//        dfs(0, 0)
//
//        return result
//    }
}
