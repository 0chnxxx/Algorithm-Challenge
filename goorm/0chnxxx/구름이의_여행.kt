/**
 * 구름 나라는 N개의 섬으로 이루어져 있다.
 * 각 섬에는 1부터 N까지의 번호가 붙어 있고, 섬과 섬 사이를 편하게 이동할 수 있도록 다리를 M개 설치했다.
 * 모든 다리는 양방향으로 이동할 수 있다.
 * 서로 다른 두 섬을 잇는 다리는 최대 하나이다.
 * 다리가 잇는 두 섬은 항상 다른 섬이다.
 * 1번 섬에서 출발해서 N번 섬으로 가려고 하는데 통과하는 다리의 개수가 K개 이하가 되길 원한다.
 * 1번 섬에서 N번 섬까지 K개 이하의 다리를 이용해 도착할 수 있는지 판별해보자.
 *
 * 도달할 수 있다면 "YES"를 출력, 도달할 수 없다면 "NO"를 출력
 *
 * 2 <= N <= 1000
 * 1 <= M <= 5000
 * 1 <= K <= M
 * 1 <= Ui, Vi <= N
 * Ui != Vi
 * i != j
 */

import java.util.*

fun main(args: Array<String>) {
    val (n, m, k) = readLine()!!.split(" ").map { it.toInt() }
    val bridges = Array(n + 1) { mutableListOf<Int>() }

    repeat(m) {
        val (from, to) = readLine()!!.split(" ").map { it.toInt() }

        bridges[from].add(to)
        bridges[to].add(from)
    }

    val visited = Array(n + 1) { IntArray(k + 1) { -1 } }

    fun dfs(current: Int, destination: Int, count: Int): Boolean {
        if (count > k) {
            return false
        }

        if (visited[current][count] != -1) {
            return false
        }

        if (current == destination) {
            return true
        }

        visited[current][count] = 1

        for (next in bridges[current]) {
            if (dfs(next, destination, count + 1)) {
                return true
            }
        }

        return false
    }

    if (dfs(1, n, 0)) {
        println("YES")
    } else {
        println("NO")
    }
}
