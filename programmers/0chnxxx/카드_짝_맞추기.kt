import java.util.*

/**
 * 게임 개발자인 베로니는 개발 연습을 위해 다음과 같은 간단한 카드 짝맞추기 보드 게임을 개발해 보려고 합니다.
 * 게임이 시작되면 화면에는 카드 16장이 뒷면을 위로하여 4 * 4 크기의 격자 형태로 표시되어 있습니다.
 * 각 카드의 앞면에는 카카오프렌즈 캐릭터 그림이 그려져 있으며, 8가지의 캐릭터 그림이 그려진 카드가 각기 2장씩 화면에 무작위로 배치되어 있습니다.
 * 유저가 카드를 2장 선택하여 앞면으로 뒤집었을 때 같은 그림이 그려진 카드면 해당 카드는 게임 화면에서 사라지며, 같은 그림이 아니라면 원래 상태로 뒷면이 보이도록 뒤집힙니다.
 * 이와 같은 방법으로 모든 카드를 화면에서 사라지게 하면 게임이 종료됩니다.
 *
 * 게임에서 카드를 선택하는 방법은 다음과 같습니다.
 * - 카드는 커서를 이용해서 선택할 수 있습니다.
 *      - 커서는 4 * 4 화면에서 유저가 선택한 현재 위치를 표시하는 굵고 빨간 테두리 상자를 의미합니다.
 * - 커서는 컨트롤 키와 방향키에 의해 이동되며 키 조작법은 다음과 같습니다.
 *      - 방향키 상하좌우 중 하나를 누르면, 커서가 누른 키 방향으로 1칸 이동합니다.
 *      - 컨트롤 키를 누른 상태에서 방향키 상하좌우 중 하나를 누르면, 누른 키 방향에 있는 가장 가까운 카드로 한번에 이동합니다.
 *          - 만약 해당 방향에 카드가 하나도 없다면 그 방향의 가장 마지막 칸으로 이동합니다.
 *      - 만약 누른 키 방향으로 이동 가능한 카드 또는 빈 공간이 없어 이동할 수 없다면 커서는 움직이지 않습니다.
 * - 커서가 위치한 카드를 뒤집기 위해서는 엔터 키를 입력합니다.
 *      - 엔터 키를 입력해서 카드를 뒤집었을 때
 *          - 앞면이 보이는 카드가 1장 뿐이라면 그림을 맞출 수 없으므로 두번째 카드를 뒤집을 때까지 앞면을 유지합니다.
 *          - 앞면이 보이는 카드가 2장이 된 경우, 두개의 카드에 그려진 그림이 같으면 해당 카드들이 화면에서 사라지며, 그림이 다르다면 두 카드 모두 뒷면이 보이도록 다시 뒤집힙니다.
 *
 * 베로니는 게임 진행 중 카드의 짝을 맞춰 몇 장 제거된 상태에서 카드 앞면의 그림을 알고 있다면, 남은 카드를 모두 제거하는데 필요한 키 조작 횟수의 최솟값을 구해 보려고 합니다.
 * 키 조작 횟수는 방향키와 엔터 키를 누르는 동작을 각각 조작 횟수 1로 계산하며, 컨트롤 키와 방향키를 함께 누르는 동작 또한 조작 횟수 1로 계산합니다.
 *
 * 현재 카드가 놓인 상태를 나타내는 2차원 배열 board
 * 커서의 처음 위치 r, c
 *
 * 모든 카드를 제거하기 위한 키 조작 횟수의 최솟값을 return 하도록 solution 함수를 완성해 주세요.
 *
 * board는 4 * 4 크기의 2차원 배열입니다.
 * 0 <= board[i] <= 6
 * 0은 카드가 제거된 빈 칸을 나타냅니다.
 * 0 <= r, c <= 3
 */

fun main() {
    val board = arrayOf(
        intArrayOf(3, 0, 0, 2),
        intArrayOf(0, 0, 1, 0),
        intArrayOf(0, 1, 0, 0),
        intArrayOf(2, 0, 0, 3)
    )
    val r = 0
    val c = 1

    val solution = Solution().solution(board, r, c)

    println(solution)
}

class Solution {
    private var totalCardTypes = 0
    private var minMoves = Int.MAX_VALUE

    fun solution(board: Array<IntArray>, r: Int, c: Int): Int {
        val cardMap = mutableMapOf<Int, MutableList<Pair<Int, Int>>>()
        val visited = BooleanArray(7) { false }

        for (i in board.indices) {
            for (j in board[i].indices) {
                if (board[i][j] > 0) {
                    cardMap.computeIfAbsent(board[i][j]) { mutableListOf() }.add(i to j)
                    visited[board[i][j]] = true
                }
            }
        }

        totalCardTypes = cardMap.keys.size

        dfs(board, cardMap, visited, 0, 0, r, c)

        return minMoves
    }

    // 카드 제거 순서 순열
    private fun dfs(board: Array<IntArray>, cardMap: MutableMap<Int, MutableList<Pair<Int, Int>>>, visited: BooleanArray, types: Int, moves: Int, r: Int, c: Int) {
        // 결과 비교
        if (types == totalCardTypes) {
            minMoves = minOf(minMoves, moves)
            return
        }

        // 진행
        for ((number, cards) in cardMap) {
            if (!visited[number]) continue

            // 최소 이동 거리 계산 아래 두 케이스 비교
            // 1. 현재 위치 -> 첫 번째 위치 (+엔터) -> 두 번째 위치 (+엔터)
            // 2. 현재 위치 -> 두 번째 위치 (+엔터) -> 첫 번째 위치 (+엔터)
            val moveCount1 = bfs(board, r, c, cards[0].first, cards[0].second) + bfs(board, cards[0].first, cards[0].second, cards[1].first, cards[1].second) + 2
            val moveCount2 = bfs(board, r, c, cards[1].first, cards[1].second) + bfs(board, cards[1].first, cards[1].second, cards[0].first, cards[0].second) + 2

            // 다음 dfs 진행
            visited[number] = false
            board[cards[0].first][cards[0].second] = 0
            board[cards[1].first][cards[1].second] = 0

            if (moveCount1 < moveCount2) {
                dfs(board, cardMap, visited, types + 1, moves + moveCount1, cards[1].first, cards[1].second)
            } else {
                dfs(board, cardMap, visited, types + 1, moves + moveCount2, cards[0].first, cards[0].second)
            }

            board[cards[0].first][cards[0].second] = number
            board[cards[1].first][cards[1].second] = number
            visited[number] = true
        }
    }

    // 최소 이동 거리 계산
    private fun bfs(board: Array<IntArray>, startX: Int, startY: Int, targetX: Int, targetY: Int): Int {
        val dx = intArrayOf(0, 0, 1, -1)
        val dy = intArrayOf(1, -1, 0, 0)

        val queue: Queue<Triple<Int, Int, Int>> = ArrayDeque()
        val visited = Array(board.size) { BooleanArray(board[0].size) }

        queue.add(Triple(startX, startY, 0))
        visited[startX][startY] = true

        while (queue.isNotEmpty()) {
            val (currentX, currentY, move) = queue.poll()

            // 타겟 카드를 찾은 경우 이동 횟수 리턴
            if (currentX == targetX && currentY == targetY) return move

            // 4방향 1칸씩 이동
            for (d in 0 until 4) {
                val newX = currentX + dx[d]
                val newY = currentY+ dy[d]

                if (newX !in board.indices || newY !in board[0].indices || visited[newX][newY]) continue

                queue.add(Triple(newX, newY, move + 1))
                visited[newX][newY] = true
            }

            // 4방향 ctrl 이동
            for (d in 0 until 4) {
                var newX = currentX
                var newY = currentY

                // 벽에 닿거나 다른 카드를 만날 때까지 이동
                while (true) {
                    newX += dx[d]
                    newY += dy[d]

                    // 벽에 닿은 경우
                    if (newX !in board.indices || newY !in board[0].indices) {
                        newX -= dx[d]
                        newY -= dy[d]
                        break
                    }

                    // 다른 카드를 만난 경우
                    if (board[newX][newY] != 0) break
                }

                // 이미 방문한 곳이면 스킵
                if (visited[newX][newY]) continue

                queue.add(Triple(newX, newY, move + 1))
                visited[newX][newY] = true
            }
        }

        return -1
    }
}
