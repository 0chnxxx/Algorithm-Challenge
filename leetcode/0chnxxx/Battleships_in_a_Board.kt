/**
 * Given an m x n matrix board where each cell is a battleship 'X' or empty '.', return the number of the battleships on board.
 * Battleships can only be placed horizontally or vertically on board.
 * In other words, they can only be made of the shape 1 x k (1 row, k columns) or k x 1 (k rows, 1 column), where k can be of any size.
 * At least one horizontal or vertical cell separates between two battleships (i.e., there are no adjacent battleships).
 *
 * Constraints:
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 200
 * board[i][j] is either '.' or 'X'.
 *
 * Follow up:
 * Could you do it in one-pass, using only O(1) extra memory and without modifying the values board?
 */

fun main() {
    val board = arrayOf(
        charArrayOf('X', ',', '.', ',', '.', ',', 'X'),
        charArrayOf('.', ',', '.', ',', '.', ',', 'X'),
        charArrayOf('.', ',', '.', ',', '.', ',', 'X')
    )

    val result = Solution().countBattleships(board)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N * M)
    // 공간 복잡도 : O(1)
    fun countBattleships(board: Array<CharArray>): Int {
        var count = 0

        for (i in board.indices) {
            for (j in board[i].indices) {
                if (board[i][j] != 'X') continue

                if (i > 0 && board[i - 1][j] == 'X') continue
                if (j > 0 && board[i][j - 1] == 'X') continue

                count++
            }
        }

        return count
    }
}
