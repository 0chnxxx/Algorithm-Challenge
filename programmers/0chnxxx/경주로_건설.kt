import java.util.LinkedList

/**
 * 건설회사의 설계사인 죠르디는 고객사로부터 자동차 경주로 건설에 필요한 견적을 의뢰받았습니다.
 * 제공된 경주로 설계 도면에 따르면 경주로 부지는 N * N 크기의 정사각형 격자 형태이며 각 격자는 1 * 1 크기입니다.
 * 설계 도면에는 각 격자의 칸은 0 또는 1로 채워져 있으며, 0은 칸이 비어 있음을 1은 해당 칸이 벽으로 채워져 있음을 나타냅니다.
 * 경주로의 출발점은 (0, 0) 칸이며, 도착점은 (N - 1, N - 1) 칸입니다.
 * 죠르디는 출발점인 (0, 0) 칸에서 출발한 자동차가 도착점인 (N - 1, N - 1) 칸까지 무사히 도달할 수 있게 중간에 끊기지 않도록 경주로를 건설해야 합니다.
 * 경주로는 상하좌우로 인접한 두 빈 칸을 연결하여 건설할 수 있으며, 벽이 있는 칸에는 경주로를 건설할 수 없습니다.
 * 이때, 인접한 두 빈 칸을 상하 또는 좌우로 연결한 경주로를 직선 도로라고 합니다.
 * 또한 두 직선 도로가 서로 직각으로 만나는 지점을 코너라고 부릅니다.
 * 건설 비용을 계산해 보니 직선 도로 하나를 만들 때는 100원이 소요되며, 코너를 하나 만들 때는 500원이 추가로 듭니다.
 * 죠르디는 견적서 작성을 위해 경주로를 건설하는데 필요한 최소 비용을 계산해야 합니다.
 *
 * 도면의 상태를 나타내는 2차원 배열 board가 매개변수로 주어질 때, 경주로를 건설하는데 필요한 최소 비용을 return 하도록 solution 함수를 완성해주세요.
 *
 * 3 <= board의 길이 <= 25
 */

fun main() {
    val board = arrayOf(
        intArrayOf(0,0,0,0,0,0,0,1),
        intArrayOf(0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,1,0,0),
        intArrayOf(0,0,0,0,1,0,0,0),
        intArrayOf(0,0,0,1,0,0,0,1),
        intArrayOf(0,0,1,0,0,0,1,0),
        intArrayOf(0,1,0,0,0,1,0,0),
        intArrayOf(1,0,0,0,0,0,0,0)
    )

    val solution = Solution().solution(board)

    println(solution)
}

class Solution {
    fun solution(board: Array<IntArray>): Int {
        val n = board.size
        val directions = arrayOf(0 to -1, 0 to 1, -1 to 0, 1 to 0)
        val cost = Array(n) { Array(n) { IntArray(4) { Int.MAX_VALUE } } } // x, y 위치에서 4방향에 대한 비용을 저장하기 위한 3차원 배열
        val queue = LinkedList<Triple<Int, Int, Int>>()

        queue.offer(Triple(0, 0, -1))
        directions.forEachIndexed { i, _ -> cost[0][0][i] = 0 }

        while (queue.isNotEmpty()) {
            val (x, y, direction) = queue.poll()

            for ((newDirection, delta) in directions.withIndex()) {
                val newX = x + delta.first
                val newY = y + delta.second

                // 새로운 x, y가 범위 안에 있으며 벽이 아닌지 확인
                if (newX in 0 until n && newY in 0 until n && board[newX][newY] == 0) {
                    // 같은 방향이면 직선 도로를 깔기 위해 100원, 다른 방향이면 코너와 직선 도로를 깔기 위해 600원
                    val newCost = when(direction) {
                        -1 -> 100 // 출발점에선 직선 도로만 깔기 위해 100원
                        newDirection -> cost[x][y][direction] + 100 // 같은 방향이면 기존 비용에서 100원 추가
                        else -> cost[x][y][direction] + 600 // 다른 방향이면 기존 비용에서 600원 추가
                    }

                    // 더 적은 비용이라면
                    if (newCost < cost[newX][newY][newDirection]) {
                        cost[newX][newY][newDirection] = newCost
                        queue.offer(Triple(newX, newY, newDirection))
                    }
                }
            }
        }

        // 모든 경우를 다 탐색한 후 도착점의 4방향 중 최소 비용을 반환
        return cost[n - 1][n - 1].minOrNull() ?: Int.MAX_VALUE
    }
}
