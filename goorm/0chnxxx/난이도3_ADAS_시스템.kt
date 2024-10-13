import java.awt.Point
import java.util.*

/**
 * ADAS 시스템은 주행보조시스템
 * W * H 의 2차원 좌표 평면에서 가상 주행
 *
 * (a, b) 좌표의 종류는 일반 점, P-점, S점, E점이 있다.
 * 주행은 S점에서 E점까지 이동하며 E점에 도착하면 주행 종료된다.
 *
 * (a, b) 좌표에 도착하면 상하좌우 점을 후보에 등록한다. (좌표 바깥은 제외)
 *
 * 후보 결정 우선순위
 * 1. E점
 * 2. P-점
 * 3. 일반점
 *
 * 우선순위가 같은 점들이 여러개일때?
 * 1. (a, b) 에서 a 값이 작은 것을 우선, 그 다음 b 값이 작은 것을 우선
 *
 * 위험 점수는 처음 0점 시작하고 각 점을 방문할 때 규칙에 따라 증감
 * 1. S점과 E점은 증감하지 않음
 * 2. 일반점은 주변 3*3 크기 안에서 자신을 제외한 P-점의 개수만큼 위험 점수가 증가
 * 3. P-점은 주변 3*3 크기 안에 자신으 제외한 P-점의 개수에서 3을 뺀 위험 점수가 증가
 * 4. 주행을 마친 후 위험 점수가 0보다 작다면 0으로 측정
 */

fun main(args: Array<String>) {
    val (height, width) = readLine()!!.split(" ").map { it.toInt() }
    val map = Array(height) { Array(width) { "" } }
    var now = Point(0, 0)

    for (i in 0 until height) {
        val mark = readLine()!!.split("").subList(1, width + 1)

        for (j in 0 until width) {
            if (mark[j] == "S") {
                now = Point(i, j)
            }

            map[i][j] = mark[j]
        }
    }

    val result = bfs(map, now)

    println(result)
}

private fun bfs(map: Array<Array<String>>, now: Point): Int {
    val directions = listOf(
        Point(-1, 0), Point(1, 0), Point(0, -1), Point(0, 1)
    )
    val visited = Array(map.size) { BooleanArray(map[0].size) }
    val queue = PriorityQueue<Point>(compareBy({ when (map[it.x][it.y]) { "E" -> 0; "P" -> 1; else -> 2 } }, { it.x }, { it.y }))
    var totalScore = 0

    queue.add(now)
    visited[now.x][now.y] = true

    while (queue.isNotEmpty()) {
        val point = queue.poll()

        if (map[point.x][point.y] == "E") {
            return maxOf(totalScore, 0)
        }

        if (map[point.x][point.y] != "S") {
            totalScore += check(map, Point(point.x, point.y))
        }

        for (direction in directions) {
            val newX = point.x + direction.x
            val newY = point.y + direction.y
            val newPoint = Point(newX, newY)

            if (newX in 0 until map.size && newY in 0 until map[0].size && !visited[newX][newY]) {
                visited[newX][newY] = true
                queue.add(Point(newX, newY))
            }
        }
    }

    return maxOf(totalScore, 0)
}

private fun check(map: Array<Array<String>>, now: Point): Int {
    val directions = listOf(
        Point(-1, 0), Point(1, 0), Point(0, -1), Point(0, 1),
        Point(-1, -1), Point(1, -1), Point(-1, 1), Point(1, 1)
    )

    val pCount = directions.count {
        val newX = now.x + it.x
        val newY = now.y + it.y
        newX in 0 until map.size && newY in 0 until map[0].size && map[newX][newY] == "P"
    }

    return when (map[now.x][now.y]) {
        "P" -> pCount - 3
        else -> pCount
    }
}
