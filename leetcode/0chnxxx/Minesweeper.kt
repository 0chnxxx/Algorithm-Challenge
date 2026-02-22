/**
 * Let's play the minesweeper game (Wikipedia, online game)!
 *
 * You are given an m x n char matrix board representing the game board where:
 * - 'M' represents an unrevealed mine,
 * - 'E' represents an unrevealed empty square,
 * - 'B' represents a revealed blank square that has no adjacent mines (i.e., above, below, left, right, and all 4 diagonals),
 * - digit ('1' to '8') represents how many mines are adjacent to this revealed square, and
 * - 'X' represents a revealed mine.
 *
 * You are also given an integer array click where click = [clickr, clickc] represents the next click position among all the unrevealed squares ('M' or 'E').
 *
 * Return the board after revealing this position according to the following rules:
 * 1. If a mine 'M' is revealed, then the game is over. You should change it to 'X'.
 * 2. If an empty square 'E' with no adjacent mines is revealed, then change it to a revealed blank 'B' and all of its adjacent unrevealed squares should be revealed recursively.
 * 3. If an empty square 'E' with at least one adjacent mine is revealed, then change it to a digit ('1' to '8') representing the number of adjacent mines.
 * 4. Return the board when no more squares will be revealed.
 *
 * Constraints:
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 50
 * board[i][j] is either 'M', 'E', 'B', or a digit from '1' to '8'.
 * click.length == 2
 * 0 <= clickr < m
 * 0 <= clickc < n
 * board[clickr][clickc] is either 'M' or 'E'.
 */

fun main() {
    val board = arrayOf(
        charArrayOf('E', 'E', 'E', 'E', 'E'),
        charArrayOf('E', 'E', 'M', 'E', 'E'),
        charArrayOf('E', 'E', 'E', 'E', 'E'),
        charArrayOf('E', 'E', 'E', 'E', 'E')
    )
    val click = intArrayOf(3, 0)

    val result = Solution().updateBoard(board, click)

    println(result.joinToString(", "))
}

class Solution {
    private val directions = arrayOf(
        intArrayOf(-1, -1),
        intArrayOf(-1, 0),
        intArrayOf(-1, 1),
        intArrayOf(0, -1),
        intArrayOf(0, 1),
        intArrayOf(1, -1),
        intArrayOf(1, 0),
        intArrayOf(1, 1)
    )

    // 시간 복잡도 : O(N * M)
    // 공간 복잡도 : O(N * M)
    fun updateBoard(board: Array<CharArray>, click: IntArray): Array<CharArray> {
        val r = click[0]
        val c = click[1]

        if (board[r][c] == 'M') {
            board[r][c] = 'X'
            return board
        }

        dfs(board, r, c)

        return board
    }

    private fun dfs(board: Array<CharArray>, r: Int, c: Int) {
        if (r !in board.indices || c !in board[0].indices) return

        if (board[r][c] != 'E') return

        val mineCount = countAdjacentMines(board, r, c)

        if (mineCount > 0) {
            board[r][c] = ('0' + mineCount)
            return
        }

        board[r][c] = 'B'

        for (dir in directions) {
            val nr = r + dir[0]
            val nc = c + dir[1]
            dfs(board, nr, nc)
        }
    }

    private fun countAdjacentMines(board: Array<CharArray>, r: Int, c: Int): Int {
        var count = 0

        for (dir in directions) {
            val nr = r + dir[0]
            val nc = c + dir[1]

            if (nr in board.indices && nc in board[0].indices) {
                if (board[nr][nc] == 'M') {
                    count++
                }
            }
        }

        return count
    }
}