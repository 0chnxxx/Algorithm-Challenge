/**
 * N개의 노드와 M개의 양방향 간선으로 이루어진 그래프가 있다.
 * 이 그래프의 노드는 1번부터 N번까지 번호가 붙어 있다.
 * 양끝 노드가 동일한 간선은 주어지지 않는다.
 * 플레이어는 아래 규칙에 따라 그래프에서 이동하려고 한다.
 * 1. 한 번 방문한 노드는 다시 방문할 수 없다. (시작 노드도 방문한 것으로 간주한다)
 * 2. 현재 노드와 간선으로 직접 연결된 다른 노드 중 방문할 수 있으면서 번호가 가장 작은 노드로 이동한다.
 * 플레이어는 처음에 K번 노드에 있다.
 * 플레이어가 더 이상 이동할 수 있는 노드가 없을 때, 방문한 서로 다른 노드의 개수와 마지막 노드 번호를 구해보자.
 *
 * 첫번째 줄에 노드의 개수 N, 간선의 개수 M, 시작 노드의 번호 K가 공백을 두고 입력
 * 다음 M개 줄에는 간선이 있는 양끝 정점의 번호 Si, Ei가 공백을 두고 입력
 *
 * 1 <= N <= 2000
 * 1 <= M <= 200000
 * 1 <= K <= N
 * 1 <= Si, Ei <= N (Si != Ei)
 */

import java.util.*

fun main(args: Array<String>) {
    val (n, m, k) = readLine()!!.split(" ").map { it.toInt() }
    val graph = Array(n + 1) { mutableListOf<Int>() }

    repeat(m) {
        val (from, to) = readLine()!!.split(" ").map { it.toInt() }

        graph[from].add(to)
        graph[to].add(from)
    }

    graph.forEach { it.sort() }

    val visited = BooleanArray(n + 1)
    val queue = LinkedList<Int>()
    var count = 0
    var last = k

    queue.offer(k)
    visited[k] = true

    while (queue.isNotEmpty()) {
        val current = queue.poll()

        last = current
        count++

        for (node in graph[current]) {
            if (!visited[node]) {
                visited[node] = true
                queue.offer(node)
                break
            }
        }
    }

    println("$count $last")
}
