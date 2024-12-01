/**
 * N * N 크기의 2차원 배열 M이 있다.
 * M의 i번째 줄 j번째 칸을 M(i, j)라고 하며, 한 칸의 크기는 1이다.
 * 배열의 각 칸에는 0부터 9 사이의 숫자가 하나씩 적혀 있다.
 * 이 숫자를 해당 칸의 값이라고 한다.
 *
 * 배열 M에는 뭉친 그룹이 있다.
 * 뭉친 그룹이란 상하좌우로 인접한 칸으로 연결되어 있으면서, 모든 칸들이 같은 값을 가지는 칸들의 집합을 의미한다.
 * 뭉친 그룹의 크기는 그 그룹에 속한 칸의 개수와 같다.
 *
 * M(x, y)칸의 값이 K일 때, K인 칸으로 이루어진 뭉친 그룹 중 가장 큰 뭉친 그룹의 크기를 출력하라.
 *
 * 첫번째 줄에 M의 크기 N 입력
 * 두번째 줄에 x, y가 공백을 두고 입력
 * 다음 N개 줄에는 M의 상태가 입력
 *
 * 1 <= N <= 500
 * 1 <= x, y <= N
 * 0 <= M(i, j) <= 9
 */

import java.util.*
import kotlin.math.max

fun main(args: Array<String>) {
    val n = readLine()!!.toInt()
    val (x, y) = readLine()!!.split(" ").map { it.toInt() }
    val array = Array(n) { IntArray(n) }

    for (i in 0 until n) {
        val status = readLine()!!.split(" ").map { it.toInt() }

        for (j in 0 until n) {
            array[i][j] = status[j]
        }
    }

    val directions = arrayOf(-1 to 0, 1 to 0, 0 to -1, 0 to 1)
    val k = array[x - 1][y - 1]
    val visited = Array(n) { BooleanArray(n) }

    fun bfs(startX: Int, startY: Int): Int {
        val queue = LinkedList<Pair<Int, Int>>()
        var count = 1

        queue.offer(startX to startY)
        visited[startX][startY] = true

        while (queue.isNotEmpty()) {
            val (currentX, currentY) = queue.poll()

            for (direction in directions) {
                val newX = currentX + direction.first
                val newY = currentY + direction.second

                if (newX !in 0 until n || newY !in 0 until n || visited[newX][newY]) {
                    continue
                }

                if (array[newX][newY] == k) {
                    queue.offer(newX to newY)
                    visited[newX][newY] = true
                    count++
                }
            }
        }

        return count
    }

    var result = 0

    for (i in 0 until n) {
        for (j in 0 until n) {
            if (array[i][j] == k && !visited[i][j]) {
                result = max(result, bfs(i, j))
            }
        }
    }

    println(result)
}
