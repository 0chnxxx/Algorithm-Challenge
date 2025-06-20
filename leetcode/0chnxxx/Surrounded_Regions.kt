import java.util.LinkedList

/**
 * You are given an m x n matrix board containing letters 'X' and 'O', capture regions that are surrounded:
 *
 * Connect: A cell is connected to adjacent cells horizontally or vertically.
 * Region: To form a region connect every 'O' cell.
 * Surround: The region is surrounded with 'X' cells if you can connect the region with 'X' cells and none of the region cells are on the edge of the board.

 * To capture a surrounded region, replace all 'O's with 'X's in-place within the original board.
 * You do not need to return anything.
 *
 * Constraints:
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 200
 * board[i][j] is 'X' or 'O'.
 */

fun main() {
//    val board = arrayOf(
//        charArrayOf('X', 'X', 'X', 'X'),
//        charArrayOf('X', 'O', 'O', 'X'),
//        charArrayOf('X', 'X', 'O', 'X'),
//        charArrayOf('X', 'O', 'X', 'X'),
//    )
    val board = arrayOf(
        charArrayOf('O')
    )

    Solution().solve(board)

    println(board.joinToString(", ") { it.joinToString("") })
}

class Solution {
    // 시간 복잡도 : O(N * M)
    // 공간 복잡도 : O(N * M)
    fun solve(board: Array<CharArray>): Unit {
        val edges = LinkedList<Pair<Int, Int>>()
        val row = board.size
        val column = board[0].size

        // 테두리의 O를 먼저 구함
        for (i in 0 until row) {
            if (board[i][0] == 'O') edges.add(i to 0)
            if (board[i][column - 1] == 'O') edges.add(i to column - 1)
        }

        for (j in 0 until column) {
            if (board[0][j] == 'O') edges.add(0 to j)
            if (board[row - 1][j] == 'O') edges.add(row - 1 to j)
        }

        // 테두리의 O들로부터 BFS를 돌면서 연결된 O들을 전부 S로 마킹
        val dx = intArrayOf(1, -1, 0, 0)
        val dy = intArrayOf(0, 0, 1, -1)

        while (edges.isNotEmpty()) {
            val (y, x) = edges.poll()

            board[y][x] = 'S'

            for (i in 0..3) {
                val newX = x + dx[i]
                val newY = y + dy[i]

                if (newX !in 0 until column || newY !in 0 until row) continue

                if (board[newY][newX] == 'O') edges.add(newY to newX)
            }
        }

        // 전체 보드를 순회하면서 S는 다시 O로 변경하고 O는 X로 변경
        for (i in board.indices) {
            for (j in board[i].indices) {
                if (board[i][j] == 'O') board[i][j] = 'X'
                if (board[i][j] == 'S') board[i][j] = 'O'
            }
        }
    }
}