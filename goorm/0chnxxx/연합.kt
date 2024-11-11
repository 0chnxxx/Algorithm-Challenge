/**
 * 바다 위에 N개의 섬이 있다.
 * 섬은 1번부터 N번까지 차례대로 번호가 붙어 있다.
 * 서로 다른 두 섬을 연결하는 M개의 다리도 있다.
 * 모든 다리는 단방향으로만 이동 가능하고 정방향과 역방향 최대 두 개이다.
 * 양방향으로 다리가 있는 두 섬은 연합을 결성한다.
 * a와 b가 연합을 결성하고 b와 c가 연합을 결성했다면 a와 c 역시 위 조건을 만족하지 않더라도 같은 연합에 속해있는 것으로 본다.
 * N개의 섬 사이에 존재하는 연합의 개수를 구해보자.
 *
 * 첫번째 줄에 섬의 개수 N, 다리의 개수 M이 공백을 두고 입력
 * 다음 M개 줄에는 다리의 출발과 도착지인 Si, Ei 가 공백을 두고 입력
 *
 * 2 <= N <= 2000
 * 1 <= M <= 200000
 * 1 <= Si, Ei <= N
 * Si != Ei
 */

import java.util.*

fun main(args: Array<String>) {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val parent = IntArray(n + 1) { it }
    val graph = Array(n + 1) { BooleanArray(n + 1) }

    fun find(x: Int): Int {
        if (parent[x] == x) {
            return x
        }

        parent[x] = find(parent[x])

        return parent[x]
    }

    fun union(x: Int, y: Int) {
        val rootX = find(x)
        val rootY = find(y)

        if (rootX != rootY) {
            parent[rootY] = rootX
        }
    }

    repeat(m) {
        val (s, e) = readLine()!!.split(" ").map { it.toInt() }

        graph[s][e] = true

        if (graph[e][s]) {
            union(s, e)
        }
    }

    var count = 0

    for (i in 1..n) {
        if (i == find(i)) {
            count++
        }
    }

    println(count)
}
