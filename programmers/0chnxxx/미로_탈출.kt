import kotlin.collections.ArrayDeque

/**
 * 1 * 1 크기의 칸들로 이루어진 직사각형 격자 형태의 미로에서 탈출하려고 합니다.
 * 각 칸은 통로 또는 벽으로 구성되어 있으며, 벽으로 된 칸은 지나갈 수 없고 통로로 된 칸으로만 이동할 수 있습니다.
 * 통로들 중 한 칸에는 미로를 빠져나가는 문이 있는데, 이 문은 레버를 당겨서만 열 수 있습니다.
 * 레버 또는 통로들 중 한 칸에 있습니다.
 * 따라서, 출발 지점에서 먼저 레버가 있는 칸으로 이동하여 레버를 당긴 후 미로를 빠져나가는 문이 있는 칸으로 이동하면 됩니다.
 * 이때 아직 레버를 당기지 않았더라도 출구가 있는 칸을 지나갈 수 있습니다.
 * 미로에서 한 칸을 이동하는데 1초가 걸린다고 할 때, 최대한 빠르게 미로를 빠져나가는데 걸리는 시간을 구하려 합니다.
 *
 * 미로를 나타낸 문자열 배열 maps가 매개변수로 주어질 때, 미로를 탈출하는데 필요한 최소 시간을 return 하는 solution 함수를 완성해주세요.
 * 만약, 탈출할 수 없다면 -1을 return 해주세요.
 *
 * 5 <= maps의 길이 <= 100
 * 5 <= maps의 원소의 길이 <= 100
 *
 * S : 시작 지점
 * E : 출구
 * L : 레버
 * O : 통로
 * X : 벽
 */

fun main() {
    val maps = arrayOf("SOOOL", "XXXXO", "OOOOO", "OXXXXX", "OOOOE")

    val result = Solution().solution(maps)

    println(result)
}

class Solution {
    fun solution(maps: Array<String>): Int {
        val miro = maps.map { it.split("").filter { it.isNotEmpty() } }

        var start = 0 to 0
        var lever = 0 to 0

        for (i in 0 until miro.size) {
            for (j in 0 until miro[i].size) {
                when (miro[i][j]) {
                    "S" -> start = i to j
                    "L" -> lever = i to j
                }
            }
        }

        val directions = arrayOf(
            Pair(-1, 0),
            Pair(1, 0),
            Pair(0, -1),
            Pair(0, 1)
        )

        fun bfs(start: Pair<Int, Int>, target: String): Int {
            val queue = ArrayDeque<Pair<Int, Int>>()
            val visited = Array(miro.size) { BooleanArray(miro[0].size) { false } }

            queue.add(start)
            visited[start.first][start.second] = true

            var time = 0

            while (queue.isNotEmpty()) {
                repeat(queue.size) {
                    val (x, y) = queue.removeFirst()

                    if (miro[x][y] == target) {
                        return time
                    }

                    for (direction in directions) {
                        val newX = x + direction.first
                        val newY = y + direction.second

                        if (newX !in 0 until miro.size || newY !in 0 until miro[0].size) {
                            continue
                        }

                        if (visited[newX][newY] || miro[newX][newY] == "X") {
                            continue
                        }

                        queue.add(newX to newY)
                        visited[newX][newY] = true
                    }
                }

                time++
            }

            return -1
        }

        val toLever = bfs(start, "L")

        if (toLever == -1) {
            return -1
        }

        val toExit = bfs(lever, "E")

        if (toExit == -1) {
            return -1
        }

        return toLever + toExit
    }
}
