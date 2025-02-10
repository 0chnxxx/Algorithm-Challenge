import java.util.LinkedList

/**
 * 로봇개발자 무지는 한 달 앞으로 다가온 카카오배 로봇경진대회에 출품할 로봇을 준비하고 있습니다.
 * 준비 중인 로봇은 2 * 1 크기의 로봇으로 무지는 0과 1로 이루어진 N * N 크기의 지도에서 2 * 1 크기인 로봇을 움직여 (N, N) 위치까지 이동할 수 있도록 프로그래밍을 하려고 합니다.
 * 로봇이 이동하는 지도는 가장 왼쪽, 상단의 좌표를 (1, 1)로 하며 지도 내에 표시된 숫자 0은 빈칸을 1은 벽을 나타냅니다.
 * 로봇은 벽이 있는 칸 또는 지도 밖으로는 이동할 수 없습니다.
 * 로봇은 처음에 좌표 (1, 1) 위치에서 가로방향으로 놓여있는 상태로 시작하며, 앞뒤 구분없이 움직일 수 있습니다.
 *
 * 로봇이 움직일 때는 현재 놓여있는 상태를 유지하면서 이동합니다.
 * 로봇이 차지하는 두 칸 중 어느 한 칸이라도 (N, N) 위치에 도착하면 됩니다.
 *
 * 로봇은 조건에 따라 회전이 가능합니다.
 * 로봇은 90도씩 회전할 수 있습니다.
 * 단, 로봇이 차지하는 두 칸 중, 어느 칸이든 축이 될 수 있지만, 회전하는 방향(축이 되는 칸으로부터 대각선 방향에 있는 칸)에는 벽이 없어야 합니다.
 * 로봇이 한 칸 이동하거나 90도 회전하는데 걸리는 시간은 정확히 1초입니다.
 *
 * 0과 1로 이루어진 지도인 board가 주어질 때, 로봇이 (N, N) 위치까지 이동하는데 필요한 최소 시간을 return 하도록 solution 함수를 완성해주세요.
 *
 * 5 <= board의 한 변의 길이 <= 100
 * board의 원소는 0 또는 1
 * 로봇이 처음에 놓여 있는 칸 (1, 1), (1, 2)는 항상 0으로 주어집니다.
 * 로봇이 항상 목적지에 도착할 수 있는 경우만 입력으로 주어집니다.
 */

fun main() {
    val board = arrayOf(
        intArrayOf(0, 0, 0, 1, 1),
        intArrayOf(0, 0, 0, 1, 0),
        intArrayOf(0, 1, 0, 1, 1),
        intArrayOf(1, 1, 0, 0, 1),
        intArrayOf(0, 0, 0, 0, 0)
    )

    val solution = Solution().solution(board)

    println(solution)
}

class Solution {
    data class Robot(
        val y: Int,
        val x: Int,
        val direction: Int, // 가로 0, 세로 1
        val time: Int
    )

    fun solution(board: Array<IntArray>): Int {
        val n = board.size
        val directions = arrayOf(
            -1 to 0, // 위
            1 to 0, // 아래
            0 to -1, // 왼쪽
            0 to 1 // 오른쪽
        )

        val queue = LinkedList<Robot>()
        val visited = Array(n) { Array(n) { BooleanArray(2) } }

        queue.offer(Robot(0, 0, 0, 0))
        visited[0][0][0] = true

        while (queue.isNotEmpty()) {
            val (y1, x1, direction, time) = queue.poll()

            // 가로, 세로를 통해 x2, y2 계산
            val y2 = if (direction == 0) y1 else y1 + 1
            val x2 = if (direction == 0) x1 + 1 else x1

            // 종료 조건
            if ((y1 == n - 1 && x1 == n - 1) || (y2 == n - 1 && x2 == n - 1)) {
                return time
            }

            for ((dy, dx) in directions) {
                val newY1 = y1 + dy
                val newX1 = x1 + dx
                val newY2 = y2 + dy
                val newX2 = x2 + dx

                // 이동 가능 조건 (범위 안, 벽이 아님, 방문 안함)
                if (newY1 in 0 until n && newX1 in 0 until n &&
                    newY2 in 0 until n && newX2 in 0 until n &&
                    board[newY1][newX1] == 0 && board[newY2][newX2] == 0 &&
                    !visited[newY1][newX1][direction]
                ) {
                    queue.offer(Robot(newY1, newX1, direction, time + 1))
                    visited[newY1][newX1][direction] = true
                }
            }

            // 회전시켜서 또 한번 이동
            if (direction == 0) { // 가로인 경우
                // 위쪽으로 회전 가능
                if (y1 > 0 && board[y1 - 1][x1] == 0 && board[y1 - 1][x2] == 0) {
                    // 왼쪽 칸 기준으로 위로 회전
                    if (!visited[y1 - 1][x1][1]) {
                        queue.offer(Robot(y1 - 1, x1, 1, time + 1))
                        visited[y1 - 1][x1][1] = true
                    }
                    // 오른쪽 칸 기준으로 위로 회전
                    if (!visited[y1 - 1][x2][1]) {
                        queue.offer(Robot(y1 - 1, x2, 1, time + 1))
                        visited[y1 - 1][x2][1] = true
                    }
                }
                // 아래쪽으로 회전 가능
                if (y1 < n - 1 && board[y1 + 1][x1] == 0 && board[y1 + 1][x2] == 0) {
                    // 왼쪽 칸 기준으로 아래로 회전
                    if (!visited[y1][x1][1]) {
                        queue.offer(Robot(y1, x1, 1, time + 1))
                        visited[y1][x1][1] = true
                    }
                    // 오른쪽 칸 기준으로 아래로 회전
                    if (!visited[y1][x2][1]) {
                        queue.offer(Robot(y1, x2, 1, time + 1))
                        visited[y1][x2][1] = true
                    }
                }
            } else { // 세로인 경우
                // 왼쪽으로 회전 가능
                if (x1 > 0 && board[y1][x1 - 1] == 0 && board[y2][x1 - 1] == 0) {
                    // 위쪽 칸 기준으로 왼쪽으로 회전
                    if (!visited[y1][x1 - 1][0]) {
                        queue.offer(Robot(y1, x1 - 1, 0, time + 1))
                        visited[y1][x1 - 1][0] = true
                    }
                    // 아래쪽 칸 기준으로 왼쪽으로 회전
                    if (!visited[y2][x1 - 1][0]) {
                        queue.offer(Robot(y2, x1 - 1, 0, time + 1))
                        visited[y2][x1 - 1][0] = true
                    }
                }
                // 오른쪽으로 회전 가능
                if (x1 < n - 1 && board[y1][x1 + 1] == 0 && board[y2][x1 + 1] == 0) {
                    // 위쪽 칸 기준으로 오른쪽으로 회전
                    if (!visited[y1][x1][0]) {
                        queue.offer(Robot(y1, x1, 0, time + 1))
                        visited[y1][x1][0] = true
                    }
                    // 아래쪽 칸 기준으로 오른쪽으로 회전
                    if (!visited[y2][x1][0]) {
                        queue.offer(Robot(y2, x1, 0, time + 1))
                        visited[y2][x1][0] = true
                    }
                }
            }
        }

        return -1
    }
}
