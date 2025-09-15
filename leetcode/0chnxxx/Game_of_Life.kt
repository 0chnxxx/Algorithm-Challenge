/**
 * According to Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."
 *
 * The board is made up of an m x n grid of cells, where each cell has an initial state: live (represented by a 1) or dead (represented by a 0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):
 * Any live cell with fewer than two live neighbors dies as if caused by under-population.
 * Any live cell with two or three live neighbors lives on to the next generation.
 * Any live cell with more than three live neighbors dies, as if by over-population.
 * Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 * The next state of the board is determined by applying the above rules simultaneously to every cell in the current state of the m x n grid board. In this process, births and deaths occur simultaneously.
 *
 * Given the current state of the board, update the board to reflect its next state.
 * Note that you do not need to return anything.
 *
 * Constraints:
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 25
 * board[i][j] is 0 or 1.
 *
 *
 * Follow up:
 * Could you solve it in-place?
 * Remember that the board needs to be updated simultaneously:
 * You cannot update some cells first and then use their updated values to update other cells.
 *
 * In this question, we represent the board using a 2D array.
 * In principle, the board is infinite, which would cause problems when the active area encroaches upon the border of the array (i.e., live cells reach the border).
 * How would you address these problems?
 */

fun main() {
    val board = arrayOf(
        intArrayOf(0, 1, 0),
        intArrayOf(0, 0, 1),
        intArrayOf(1, 1, 1),
        intArrayOf(0, 0, 0)
    )

    Solution().gameOfLife(board)

    println(board.map { it.contentToString() }.joinToString("\n"))
}

class Solution {
    // 시간 복잡도 : O(M * N)
    // 공간 복잡도 : O(1)
    fun gameOfLife(board: Array<IntArray>): Unit {
        val m = board.size
        val n = board[0].size

        // 8방향
        val directions = arrayOf(
            intArrayOf(-1, -1),
            intArrayOf(-1, 0),
            intArrayOf(-1, 1),
            intArrayOf(0, -1),
            intArrayOf(0, 1),
            intArrayOf(1, -1),
            intArrayOf(1, 0),
            intArrayOf(1, 1)
        )

        // 모든 셀 탐색
        for (i in 0 until m) {
            for (j in 0 until n) {
                // 살아있는 이웃 수
                var liveNeighbors = 0

                // 8방향 탐색
                for (direction in directions) {
                    val newX = i + direction[0]
                    val newY = j + direction[1]

                    // 범위 확인
                    if (newX in 0 until m && newY in 0 until n) {
                        // 이웃이 살아있으면 이웃 수 증가
                        if (Math.abs(board[newX][newY]) == 1) {
                            liveNeighbors++
                        }
                    }
                }

                // 해당 셀이 살아있고 살아있는 이웃 수가 2보다 작거나 3보다 큰 경우
                // 해당 셀을 죽임
                if (board[i][j] == 1 && (liveNeighbors < 2 || liveNeighbors > 3)) {
                    board[i][j] = -1
                }

                // 해당 셀이 죽어있고 살아있는 이웃 수가 3인 경우
                // 해당 셀을 살림
                if (board[i][j] == 0 && liveNeighbors == 3) {
                    board[i][j] = 2
                }
            }
        }

        // 모든 셀을 탐색
        for (i in 0 until m) {
            for (j in 0 until n) {
                // -1 과 2 임시 마킹을 0 과 1로 조정
                board[i][j] = if (board[i][j] > 0) 1 else 0
            }
        }
    }
}
