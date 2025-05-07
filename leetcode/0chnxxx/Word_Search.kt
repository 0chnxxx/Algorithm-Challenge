import java.util.LinkedList

/**
 * Given an m x n grid of characters board and a string word, return true if word exists in the grid.
 *
 * The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring.
 * The same letter cell may not be used more than once.
 *
 * Constraints:
 * m == board.length
 * n = board[i].length
 * 1 <= m, n <= 6
 * 1 <= word.length <= 15
 * board and word consists of only lowercase and uppercase English letters.
 */

fun main() {
    val board = arrayOf(
        charArrayOf('A', 'B', 'C', 'E'),
        charArrayOf('S', 'F', 'E', 'S'),
        charArrayOf('A', 'D', 'E', 'E')
    )
    val word = "ABCESEEEFS"

    val solution = Solution().exist(board, word)

    println(solution)
}

class Solution {
    // 시간 복잡도 : O(N * M * 3^L)
    // 공간 복잡도 : O(L)
    fun exist(board: Array<CharArray>, word: String): Boolean {
        val n = board.size
        val m = board[0].size

        fun dfs(x: Int, y: Int, i: Int): Boolean {
            if (i == word.length) return true

            if (x !in 0 until n || y !in 0 until m || board[x][y] != word[i]) return false

            val temp = board[x][y]

            board[x][y] = '.'

            val isFound = dfs(x + 1, y, i + 1) ||
                          dfs(x - 1, y, i + 1) ||
                          dfs(x, y + 1, i + 1) ||
                          dfs(x, y - 1, i + 1)

            board[x][y] = temp

            return isFound
        }

        for (i in 0 until n) {
            for (j in 0 until m) {
                if (dfs(i, j, 0)) return true
            }
        }

        return false
    }
}
