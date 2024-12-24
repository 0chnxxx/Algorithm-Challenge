/**
 * 리코쳇 로봇이라는 보드게임이 있습니다.
 * 이 보드게임은 격자모양 게임판 위에서 말을 움직이는 게임으로, 시작 위치에서 출발한 뒤 목표 위치에 정확하게 멈추기 위해 최소 몇 번의 이동이 필요한지 말하는 게임입니다.
 * 이 게임에서 말의 이동은 현재 위치에서 상하좌우 중 한 방향으로 게임판 위의 장애물이나 게임판 가장자리까지 부딪힐 때까지 미끄러져 움직이는 것을 한 번의 이동으로 정의합니다.
 *
 * "." 은 빈 공간을
 * "R" 은 로봇의 처음 위치를
 * "D" 는 장애물의 위치를
 * "G" 는 목표지점을 나타냅니다.
 *
 * 게임판의 상태를 나타내는 문자열 배열 board가 주어졌을 때, 말이 목표 위치에 도달하는데 최소 몇 번 이동해야 하는지 return하는 solution 함수를 완성해주세요.
 * 만약 목표 위치에 도달할 수 없다면 -1을 return 해주세요.
 *
 * 3 <= board의 길이 <= 100
 * 3 <= board의 원소의 길이 <= 100
 */

fun main() {
    val board = arrayOf("...D..R", ".D.G...", "....D.D", "D....D.", "..D....")

    val result = Solution().solution(board)

    println(result)
}

class Solution {
    fun solution(board: Array<String>): Int {
        var start = 0 to 0
        val directions = arrayOf(-1 to 0, 1 to 0, 0 to -1, 0 to 1)

        for (i in 0 until board.size) {
            for (j in 0 until board[0].length) {
                if (board[i][j] == 'R') {
                    start = i to j
                }
            }
        }

        val queue = ArrayDeque<Triple<Int, Int, Int>>()
        val visited = Array(board.size) { BooleanArray(board[0].length) }

        queue.add(Triple(start.first, start.second, 0))
        visited[start.first][start.second] = true

        while (queue.isNotEmpty()) {
            val (x, y, move) = queue.removeFirst()

            if (board[x][y] == 'G') {
                return move
            }

            for ((dx, dy) in directions) {
                var nx = x
                var ny = y

                while (nx + dx in 0 until board.size && ny + dy in 0 until board[0].length && board[nx + dx][ny + dy] != 'D') {
                    nx += dx
                    ny += dy
                }

                if (!visited[nx][ny]) {
                    queue.add(Triple(nx, ny, move + 1))
                    visited[nx][ny] = true
                }
            }
        }

        return -1
    }
}
