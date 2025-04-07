/**
 * Determine if a 9 x 9 Sudoku board is valid.
 * Only the filled cells need to be validated according to the following rules:
 *
 * Each row must contain the digits 1-9 without repetition.
 * Each column must contain the digits 1-9 without repetition.
 * Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
 *
 * Note:
 * A Sudoku board (partially filled) could be valid but is not necessarily solvable.
 * Only the filled cells need to be validated according to the mentioned rules.
 *
 * board.length == 9
 * board[i].length == 9
 * board[i][j] is a digit 1-9 or '.'.
 */

fun main() {
    val board = arrayOf(
        charArrayOf('.','.','4','.','.','.','6','3','.'),
        charArrayOf('.','.','.','.','.','.','.','.','.'),
        charArrayOf('5','.','.','.','.','.','.','9','.'),
        charArrayOf('.','.','.','5','6','.','.','.','.'),
        charArrayOf('4','.','3','.','.','.','.','.','1'),
        charArrayOf('.','.','.','7','.','.','.','.','.'),
        charArrayOf('.','.','.','5','.','.','.','.','.'),
        charArrayOf('.','.','.','.','.','.','.','.','.'),
        charArrayOf('.','.','.','.','.','.','.','.','.'),
    )
    
    val solution = Solution().isValidSudoku(board)
    
    println(solution)
}

class Solution {
    // 시간 복잡도 : O(81) -> O(1)
    // 공간 복잡도 : O(1)
    fun isValidSudoku(board: Array<CharArray>): Boolean {
        for (i in 0 until 9) {
            val rowSet = mutableSetOf<Char>()
            val columnSet = mutableSetOf<Char>()
            val squareSet = mutableSetOf<Char>()

            for (j in 0 until 9) {
                // row 검사
                val row = board[i][j]

                if (row != '.' && !rowSet.add(row)) return false

                // column 검사
                val column = board[j][i]

                if (column != '.' && !columnSet.add(column)) return false

                // square 검사
                val rowIndex = 3 * (i / 3) + j / 3
                val columnIndex = 3 * (i % 3) + j % 3
                val square = board[rowIndex][columnIndex]

                if (square != '.' && !squareSet.add(square)) return false
            }
        }

        return true
    }

//    // 시간 복잡도 : O(81 + 81 + 81) -> O(243) -> O(1)
//    // 공간 복잡도 : O(1)
//    fun isValidSudoku(board: Array<CharArray>): Boolean {
//        for (i in 0..8) {
//            val row = board[i].toMutableList()
//
//            if (!isValidLine(row)) return false
//        }
//
//        for (i in 0..8) {
//            val column = mutableListOf<Char>()
//
//            for (j in 0..8) {
//                column.add(board[j][i])
//            }
//
//            if (!isValidLine(column)) return false
//        }
//
//        for (i in 0..8 step 3) {
//            for (j in 0..8 step 3) {
//                val slicedBoard = board
//                    .slice(j..j + 2)
//                    .map { it.slice(i..i + 2) }
//                    .toList()
//
//                if (!isValidSquare(slicedBoard)) return false
//            }
//        }
//
//        return true
//    }
//
//    private fun isValidLine(line: MutableList<Char>): Boolean {
//        val filteredLine = line.filter { it != '.' }.toList()
//
//        val totalCount = filteredLine.count()
//        val distinctCount = filteredLine.distinct().count()
//
//        return totalCount == distinctCount
//    }
//
//    private fun isValidSquare(board: List<List<Char>>): Boolean {
//        val totalCount = board.flatten().count { it != '.' }
//        val distinctCount = board.flatten().distinct().count { it != '.' }
//
//        return totalCount == distinctCount
//    }
}
