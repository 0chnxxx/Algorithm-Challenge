/**
 * 플레이어는 거대한 통신망 중 한 구역을 조사하고자 한다.
 * 플레이어가 조사할 구역에는 N개의 컴퓨터가 있고, M개의 통신 회선이 있다.
 * 각 컴퓨터는 1번부터 N번까지 번호가 붙어 있고, 통신 회선은 서로 다른 두 컴퓨터를 양방향으로 연결하고 있다.
 * 컴퓨터들은 연결 여부에 따라 여러 개의 컴포넌트로 나뉜다.
 * 어떤 두 컴퓨터가 통신 회선만을 이용해서 연결되어 있다면 두 컴퓨터는 같은 컴포넌트에 속한다.
 * 플레이어는 여러 개의 컴포넌트 중, 가장 밀도가 높은 컴포넌트를 조사하려고 한다.
 * 컴포넌트의 밀도는 그 컴포넌트에 포함된 통신 회선의 개수를 컴퓨터의 수로 나눈 값이다.
 * 주어진 통신 구역을 분석해서, 아래 조건을 만족하는 컴포넌트에 포함된 컴퓨터의 번호를 오름차순으로 공백을 두고 출력하라.
 * 1. 가장 밀도가 높은 컴포넌트를 출력한다.
 * 2. 1을 만족하는 컴포넌트가 여러 개라면 컴포넌트를 구성하는 컴퓨터의 수가 가장 작은 컴포넌트를 출력한다.
 * 3. 2를 만족하는 컴포넌트가 여러 개라면 더 작은 번호를 가진 컴퓨터가 있는 컴포넌트를 출력한다.
 *
 * 첫번째 줄에는 컴퓨터의 개수 N, 통신 회선의 개수 M이 공백을 두고 입력
 * 다음 M개 줄에는 통신 회선이 있는 두 정점 Ai, Bi가 공백을 두고 입력
 *
 * 2 <= N <= 100000
 * 1 <= M <= 200000
 * 1 <= Ai, Bi <= N (Ai != Bi)
 * 같은 두 컴퓨터 쌍을 연결하는 통신 회선은 최대 하나이다.
 */

fun main(args: Array<String>) {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val graph = Array(n + 1) { mutableListOf<Int>() }

    repeat(m) {
        val (from, to) = readLine()!!.split(" ").map { it.toInt() }

        graph[from].add(to)
        graph[to].add(from)
    }

    val visited = BooleanArray(n + 1)
    val components = mutableListOf<Triple<Double, Int, List<Int>>>()

    fun bfs(current: Int): Pair<Int, List<Int>> {
        val queue = ArrayDeque<Int>()
        val component = mutableListOf<Int>()
        var count = 0

        queue.add(current)
        visited[current] = true

        while (queue.isNotEmpty()) {
            val node = queue.removeFirst()

            component.add(node)

            for (next in graph[node]) {
                count++

                if (!visited[next]) {
                    visited[next] = true
                    queue.add(next)
                }
            }
        }

        return (count / 2) to component
    }

    for (i in 1..n) {
        if (!visited[i]) {
            val (count, component) = bfs(i)
            val size = component.size

            if (size > 0) {
                val density = count.toDouble() / (2 * size)

                components.add(Triple(density, size, component.sorted()))
            }
        }
    }

    val result = components
        .sortedWith(compareByDescending<Triple<Double, Int, List<Int>>> { it.first }
            .thenBy { it.second }
            .thenBy { it.third.first() })
        .first()
        .third

    println(result.joinToString(" "))
}
