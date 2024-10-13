import java.awt.Point
import java.util.*

/**
 * 2차원 배열에서 불이 1초에 사방으로 1칸씩 번진다.
 * 현재 내 위치와 인접한 칸까지 불이 번졌을 때 탈출한다.
 * 남은 시간이 몇초인지 구하여라.
 *
 * R, C -> 연구실의 크기
 * . -> 불이 번질 수 있는 빈 칸
 * # -> 불이 번질 수 없는 벽
 * & -> 내 위치
 * @ -> 불
 *
 * 1 <= R, C <= 1000
 */

fun main(args: Array<String>) {
    val (r, c) = readLine()!!.split(" ").map { it.toInt() }
    val map = Array(r) { Array(c) { "" } }

    var me = Point(0, 0)
    val fires = LinkedList<Point>()
    val directions = arrayOf(Point(-1, 0), Point(1, 0), Point(0, -1), Point(0, 1))

    for (i in 0 until r) {
        val data = readLine()!!.split("").subList(1, c + 1)

        for (j in 0 until c) {
            map[i][j] = data[j]

            when (map[i][j]) {
                "&" -> me = Point(i, j)
                "@" -> fires.add(Point(i, j))
            }
        }
    }

    val visited = Array(r) { IntArray(c) { 0 } }

    while (fires.isNotEmpty()) {
        val fire = fires.poll()

        for (direction in directions) {
            val newX = fire.x + direction.x
            val newY = fire.y + direction.y

            if (newX in 0 until r && newY in 0 until c && visited[newX][newY] == 0) {
                when(map[newX][newY]) {
                    "." -> {
                        visited[newX][newY] = visited[fire.x][fire.y] + 1
                        fires.add(Point(newX, newY))
                    }
                    "&" -> {
                        visited[newX][newY] = visited[fire.x][fire.y] + 1
                    }
                }
            }
        }
    }

    val fireArrivalTime = visited[me.x][me.y]

    if (fireArrivalTime == 0) {
        println(-1)
    } else {
        println(fireArrivalTime - 1)
    }
}
