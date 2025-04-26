import java.util.*

/**
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right, which minimizes the sum of all numbers along its path.
 *
 * Note: You can only move either down or right at any point in time.
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 200
 * 0 <= grid[i][j] <= 200
 */

fun main() {
    val grid = arrayOf(
        intArrayOf(1, 3, 1),
        intArrayOf(1, 5, 1),
        intArrayOf(4, 2, 1)
    )

    val solution = Solution().minPathSum(grid)

    println(solution)
}

class Solution {
    // 시간 복잡도 : O(M * N)
    // 공간 복잡도 : O(1)
    // in-place로 최적화
    fun minPathSum(grid: Array<IntArray>): Int {
        val m = grid.size
        val n = grid[0].size

        for (i in 0 until m) {
            for (j in 0 until n) {
                when {
                    // 시작점
                    i == 0 && j == 0 -> {}
                    // 이전 열의 값에 누적
                    i == 0 -> grid[i][j] += grid[i][j - 1]
                    // 이전 행의 값에 누적
                    j == 0 -> grid[i][j] += grid[i - 1][j]
                    // 위, 왼쪽의 값 중 작은 값에 누적
                    else -> grid[i][j] += minOf(grid[i - 1][j], grid[i][j - 1])
                }
            }
        }

        // 우측 아래의 최종 값을 반환
        return grid[m - 1][n - 1]
    }

//    // 시간 복잡도 : O(M * N)
//    // 공간 복잡도 : O(M * N)
//    fun minPathSum(grid: Array<IntArray>): Int {
//        val m = grid.size
//        val n = grid[0].size
//
//        val dp = Array(m) { IntArray(n) }
//
//        for (i in 0 until m) {
//            for (j in 0 until n) {
//                when {
//                    // 시작점
//                    i == 0 && j == 0 -> dp[i][j] = grid[i][j]
//                    // 이전 열의 값에 누적
//                    i == 0 -> dp[i][j] = dp[i][j - 1] + grid[i][j]
//                    // 이전 행의 값에 누적
//                    j == 0 -> dp[i][j] = dp[i - 1][j] + grid[i][j]
//                    // 위, 왼쪽의 값 중 작은 값에 누적
//                    else -> dp[i][j] = minOf(dp[i - 1][j], dp[i][j - 1]) + grid[i][j]
//                }
//            }
//        }
//
//        // 우측 아래의 최종 값을 반환
//        return dp[m - 1][n - 1]
//    }

//    // 시간 복잡도 : O(M * N)
//    // 공간 복잡도 : O(M * N)
//    fun minPathSum(grid: Array<IntArray>): Int {
//        val m = grid.size
//        val n = grid[0].size
//        val queue = LinkedList<Triple<Int, Int, Int>>()
//        val directions = arrayOf(
//            1 to 0,
//            0 to 1
//        )
//        val sums = Array(m) { IntArray(n) { Int.MAX_VALUE } }
//
//        queue.offer(Triple(0, 0, grid[0][0]))
//        sums[0][0] = grid[0][0]
//
//        while (queue.isNotEmpty()) {
//            val (x, y, sum) = queue.poll()
//
//            for ((dx, dy) in directions) {
//                val newX = x + dx
//                val newY = y + dy
//
//                if (newX !in 0 until m || newY !in 0 until n) continue
//
//                val newSum = sum + grid[newX][newY]
//
//                if (newSum < sums[newX][newY]) {
//                    sums[newX][newY] = newSum
//                    queue.offer(Triple(newX, newY, newSum))
//                }
//            }
//        }
//
//        return sums[m - 1][n - 1]
//    }
}
