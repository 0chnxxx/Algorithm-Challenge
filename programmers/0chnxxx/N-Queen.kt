/**
 * 가로, 세로 길이가 n인 정사각형으로된 체스판이 있습니다.
 * 체스판 위에 n개의 퀸이 서로 공격을 할 수 없도록 배치하고 싶습니다.
 * 체스판의 가로 세로의 길이 n이 매개변수로 주어질 때, n개의 퀸이 조건에 만족하도록 배치할 수 있는 방법의 수를 return하는 solution 함수를 완성해주세요.
 *
 * 퀸은 가로, 세로, 대각선으로 이동할 수 있습니다.
 * 1 <= n <= 12
 */

fun main() {
    val n = 4

    val solution = Solution().solution(n)

    println(solution)
}

class Solution {
    fun solution(n: Int): Int {
        var count = 0
        val columns = BooleanArray(n)
        val diagonals1 = BooleanArray(2 * n - 1)
        val diagonals2 = BooleanArray(2 * n - 1)

        fun backtrack(row: Int) {
            if (row == n) {
                count++
                return
            }

            for (column in 0 until n) {
                if (columns[column] || diagonals1[row - column + n - 1] || diagonals2[row + column]) {
                    continue
                }

                columns[column] = true
                diagonals1[row - column + n - 1] = true
                diagonals2[row + column] = true

                backtrack(row + 1)

                columns[column] = false
                diagonals1[row - column + n - 1] = false
                diagonals2[row + column] = false
            }
        }

        backtrack(0)

        return count
    }
}
