/**
 * 플레이어는 1번부터 N번까지의 번호가 붙은 N개의 도시와 M개의 도로가 있는 나라에 살고 있다.
 * 각 도로는 서로 다른 두 도시를 양방향으로 연결하고 있고, 주어진 도로만을 이용해 임의의 두 도시 사이를 이동하는 것이 가능하다.
 * 플레이어는 차를 타고 S번 도시에서 E번 도시로 이동하려고 한다.
 * 플레이어가 두 도시 사이를 이동할 때는 항상 가장 작은 수의 도시를 거치는 경로를 따라 이동한다.
 * 플레이어가 사는 나라에서는 자주 공사를 한다.
 * i일 뒤에는 i번 도시에서 하루 동안 공사를 할 예정이다.
 * 어떤 도시에서 공사를 하고 있으면, 그 도시에 연결된 모든 도로를 일시적으로 사용할 수 없다.
 * 어떤 도시를 공사를 하느냐에 따라 플레이어가 이동해야 하는 경로가 달라질 수 있다.
 * 앞으로 N일 동안 매일 플레이어는 S번 도시에서 E번 도시로 이동한다고 할 때, 각 날마다 플레이어가 이동하는 경로에 포함되는 도시의 수를 구해서 출력하라.
 * 단, 출발 도시와 도착 도시에서 공사를 하거나, 두 도시 사이를 이동할 수 없는 경우에는 -1 을 대신 출력한다.
 * 
 * 첫번째 줄에 도시의 수 N, 도로의 수 M, 출발 도시 S, 도착 도시 E가 공백을 두고 입력
 * 다음 M개 줄에는 각 도로가 연결하는 두 도시의 번호 u, v가 공백을 두고 입력
 * 
 * 3 <= N <= 1000
 * N - 1 <= M <= 2000
 * 1 <= S, E <= N (S != E)
 * 1 <= u, v <= N (u != v)
 */

import java.util.*

fun main(args: Array<String>) {
    val (n, m, s, e) = readLine()!!.split(" ").map { it.toInt() }
    val bridges = Array(n + 1) { mutableListOf<Int>() }
    
    repeat(m) {
        val (u, v) = readLine()!!.split(" ").map { it.toInt() }
        
        bridges[u].add(v)
        bridges[v].add(u)
    }

    bridges.forEach { it.sort() }

    fun bfs(start: Int, end: Int, construction: Int): Int {
        val queue = LinkedList<Pair<Int, Int>>()
        val visited = BooleanArray(n + 1)

        queue.offer(start to 0)
        visited[start] = true

        while (queue.isNotEmpty()) {
            val (current, distance) = queue.poll()

            if (current == e) {
                return distance + 1
            }

            for (target in bridges[current]) {
                if (!visited[target] && target != construction) {
                    visited[target] = true
                    queue.offer(target to distance + 1)
                }
            }
        }

        return -1
    }
    
    for (day in 1..n) {
        if (day == s || day == e) {
            println(-1)
        } else {
            println(bfs(s, e, day))
        }
    }
}
