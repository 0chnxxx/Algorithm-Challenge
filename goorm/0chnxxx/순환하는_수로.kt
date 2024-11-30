/**
 * 구름이는 도시의 수도 시스템을 총괄하는 관리자이다.
 * 현재 도시에는 물을 보관하는 물탱크와 물탱크끼리 연결하는 수로가 각각 N개씩 존재한다.
 * 물탱크는 관리의 편의를 위해 1번부터 N번까지의 번호가 붙어 있고, 수로는 서로 다른 두 물탱크를 연결하고 있다.
 * 모든 물탱크는 수로로 연결되어 있고, 두 물탱크 사이에는 최대 하나의 수로만 연결되어 있다.
 * 도시의 물은 흐르지 않으면 녹조가 생기기 때문에, 항상 물이 수노한하도록 유지하는 것이 중요하다.
 * 그래서 구름이는 현재 수도 시스템에서 물이 순환할 수 있는 경로를 하나 찾고, 해당 수로를 이용해 물이 계속해서 순환할 수 있게끔 하려고 한다.
 * 모든 수로는 양방향으로 물이 흐르게 할 수 있으나, 한 번 물이 흐르기 시작한 뒤에는 두 방향 중 한 방향으로만 물을 보낼 수 있다.
 * 이때 어떤 수로들만을 이용해 물이 순환하는 경로를 만들 수 있다면, 이를 순환하는 수로라고 한다.
 * 도시에 있는 수로의 정보가 주어졌을 때, 순환하는 수로에 포함되는 물탱크들을 출력하라.
 * 이러한 물탱크의 집합은 유일함이 보장된다.
 *
 * 첫번째 줄에는 정수 N 입력
 * 다음 N개 줄에 수로가 연결하는 두 물탱크의 번호 Ui, Vi가 공백을 두고 입력
 *
 * 1 <= N <= 1000
 * 1 <= Ui, Vi <= N (Ui != Vi)
 *
 * 첫번째 줄에는 순환하는 수로에 포함된 물탱크의 수를 출력
 * 두번째 줄에는 순환하는 수로를 구성하는 물탱크의 번호를 오름차순으로 공백을 두고 출력
 */

fun main(args: Array<String>) {
    val n = readLine()!!.toInt()
    val tanks = Array(n + 1) { mutableListOf<Int>() }

    // 양방향 연결
    repeat(n) {
        val (u, v) = readLine()!!.split(" ").map { it.toInt() }

        tanks[u].add(v)
        tanks[v].add(u)
    }

    val visited = BooleanArray(n + 1) // 방문 여부 추적
    val parents = IntArray(n + 1) { -1 } // 부모 노드 추적
    val nodes = mutableSetOf<Int>() // 연결된 탱크 노드 저장

    fun dfs(start: Int): Boolean {
        visited[start] = true // 현재 탱크 방문 처리

        for (tank in tanks[start]) { // 인접 탱크 탐색
            if (!visited[tank]) {
                parents[tank] = start // 현재 탱크를 인접 탱크의 부모로 설정

                if (dfs(tank)) return true // 재귀
            } else if (tank != parents[start]) { // 인접 탱크가 부모가 아닌 경우
                var current = start

                nodes.add(tank)

                while (current != tank) { // 부모를 계속 거슬러 올라가며 연결된 탱크 저장
                    nodes.add(current)
                    current = parents[current]
                }

                return true
            }
        }

        return false
    }

    for (i in 1..n) {
        if (!visited[i] && dfs(i)) {
            break
        }
    }

    println(nodes.size)
    println(nodes.sorted().joinToString(" "))
}
