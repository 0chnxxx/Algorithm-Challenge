/**
 * 한 변의 길이가 N인 정사각형 모양의 마을 M이 있다.
 * r번째 행, c번째 열에 해당하는 칸에는 숫자 M(r, c)가 적혀 있다.
 * M(r, c)는 0 또는 1 중 하나이다.
 * 0이면 아무것도 없는 칸이다.
 * 1이면 집이 있는 칸이다.
 * 마을에 있는 집에 전력을 공급하기 위해선 그 집에 발전기를 설치하거나 상하좌우로 인접한 집 중 하나가 전력을 공급받고 있으면 된다.
 * 모든 집에 전력을 공급하기 위해서 설치해야 할 발전기의 최소 개수를 구해보자.
 *
 * 첫번재 줄에 마을의 크기 N 입력
 * 다음 N개 줄에 마을의 상태를 나타내는 N개의 숫자가 공백을 두고 입력
 *
 * 1 <= N <= 1000
 */

import java.util.*

fun main(args: Array<String>) {
    val n = readLine()!!.toInt()
    val map = Array(n) { IntArray(n) }

    for (i in 0 until n) {
        val status = readLine()!!.split(" ").map { it.toInt() }

        for (j in 0 until n) {
            map[i][j] = status[j]
        }
    }

    val directions = arrayOf(
        Pair(-1, 0),
        Pair(1, 0),
        Pair(0, -1),
        Pair(0, 1)
    )

    val visited = Array(n) { BooleanArray(n) }
    var count = 0

    fun bfs(start: Pair<Int, Int>) {
        val queue = LinkedList<Pair<Int, Int>>()

        queue.offer(start)
        visited[start.first][start.second] = true

        while (queue.isNotEmpty()) {
            val (x, y) = queue.poll()

            for (direction in directions) {
                val newX = x + direction.first
                val newY = y + direction.second

                if (newX in 0 until n && newY in 0 until n && map[newX][newY] == 1 && !visited[newX][newY]) {
                    queue.offer(Pair(newX, newY))
                    visited[newX][newY] = true
                }
            }
        }
    }

    for (i in 0 until n) {
        for (j in 0 until n) {
            if (map[i][j] == 1 && !visited[i][j]) {
                bfs(Pair(i, j))
                count++
            }
        }
    }

    println(count)
}
