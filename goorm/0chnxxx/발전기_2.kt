/**
 * 한 변의 길이가 N인 정사각형 모양의 마을 M이 있다.
 * 마을의 모든 칸에는 건물이 하나씩 있고, r번째 행, c번째 열에 해당하는 칸에는 정수 M(r, c)가 적혀있다.
 * M(r, c)는 해당 칸에 있는 건물의 유형의 번호를 의미한다.
 * 건물의 유형이 동일하면서, 서로 상하좌우 인접한 칸에 위치한 건물끼리는 서로 전력을 공유할 수 있다.
 * 전력을 공유할 수 있는 관계에 속한 건물의 개수가 K개 이상이면 이를 단지라고 한다.
 *
 * 가장 많은 단지가 있는 건물 유형을 구하여라.
 *
 * 첫번째 줄에 마을의 크기 N, 단지의 기준 K가 공백을 두고 입력
 * 다음 N개 줄에 마을의 상태를 나타내는 N개의 숫자가 공백을 두고 입력
 *
 * 1 <= N <= 1000
 * 1 <= K <= 50
 * 1 <= M(r, c) <= 30
 */

import java.util.*

fun main(args: Array<String>) {
    val (n, k) = readLine()!!.split(" ").map { it.toInt() }
    val map = Array(n) { IntArray(n) }
    val visited = Array(n) { BooleanArray(n) }
    val count = mutableMapOf<Int, Int>()

    for (i in 0 until n) {
        val types = readLine()!!.split(" ").map { it.toInt() }

        for (j in 0 until n) {
            map[i][j] = types[j]
        }
    }

    for (i in 0 until n) {
        for (j in 0 until n) {
            if (visited[i][j]) {
                continue
            }

            val value = count.getOrDefault(map[i][j], 0)
            count[map[i][j]] = value + bfs(i, j, map, visited, n, k)
        }
    }

    val result = count.entries
        .sortedWith(compareByDescending<MutableMap.MutableEntry<Int, Int>> { it.value }
            .thenByDescending { it.key })
        .first()
        .key

    println(result)
}

fun bfs(i: Int, j: Int, map: Array<IntArray>, visited: Array<BooleanArray>, n: Int, k: Int): Int {
    val queue = LinkedList<Pair<Int, Int>>()
    val directions = arrayOf(
        Pair(-1, 0),
        Pair(1, 0),
        Pair(0, -1),
        Pair(0, 1),
    )

    queue.offer(Pair(i, j))
    visited[i][j] = true

    var count = 1

    while (queue.isNotEmpty()) {
        val current = queue.poll()

        for (direction in directions) {
            val newI = current.first + direction.first
            val newJ = current.second + direction.second

            if (isInSpace(newI, newJ, n) && !visited[newI][newJ] && map[i][j] == map[newI][newJ]) {
                queue.offer(Pair(newI, newJ))
                visited[newI][newJ] = true
                count++
            }
        }
    }

    return if (count >= k) {
        1
    } else {
        0
    }
}

fun isInSpace(i: Int, j: Int, n: Int): Boolean {
    return i in 0 until n && j in 0 until n
}
