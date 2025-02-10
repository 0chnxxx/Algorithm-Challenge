import java.util.*

/**
 * 도넛 모양 그래프, 막대 모양 그래프, 8자 모양 그래프들이 있습니다.
 * 이 그래프들은 1개 이상의 정점과, 정점들을 연결하는 단방향 간선으로 이루어져 있습니다.
 *
 * 크기가 n인 도넛 모양 그래프는 n개의 정점과 n개의 간선이 있습니다.
 * 도넛 모양 그래프의 아무 한 정점에서 출발해 이용한 적 없는 간선을 계속 따라가면 나머지 n - 1개의 정점들을 한 번씩 방문한 뒤 원래 출발했던 정점으로 돌아오게 됩니다.
 *
 * 크기가 n인 막대 모양 그래프는 n개의 정점과 n - 1개의 간선이 있습니다.
 * 막대 모양 그래프는 임의의 한 정점에서 출발해 간선을 계속 따라가면 나머지 n - 1개의 정점을 한 번씩 방문하게 되는 정점이 단 하나 존재합니다.
 *
 * 크기가 n인 8자 모양 그래프는 2n + 1개의 정점과 2n + 2개의 간선이 있습니다.
 * 8자 모양 그래프는 크기가 동일한 2개의 도넛 모양 그래프에서 정점을 하나씩 골라 결합시킨 형태의 그래프입니다.
 *
 * 도넛 모양 그래프, 막대 모양 그래프, 8자 모양 그래프가 여러 개 있습니다.
 * 이 그래프들과 무관한 정점을 하나 생성한 뒤, 각 도넛 모양 그래프, 막대 모양 그래프, 8자 모양 그래프의 임의의 정점 하나로 향하는 간선들을 연결했습니다.
 * 그 후 각 정점에 서로 다른 번호를 매겼습니다.
 * 이때 당신은 그래프의 간선 정보가 주어지면 생성한 정점의 번호와 정점을 생성하기 전 도넛 모양 그래프의 수, 막대 모양 그래프의 수, 8자 모양 그래프의 수를 구해야 합니다.
 *
 * 그래프의 간선 정보를 담은 2차원 정수 배열 edges가 매개변수로 주어집니다.
 * 이때, 생성한 정점의 번호, 도넛 모양 그래프의 수, 막대 모양 그래프의 수, 8자 모양 그래프의 수를 순서대로 1차원 정수 배열에 담아 return 하도록 solution 함수를 완성해주세요.
 *
 * 1 <= edges의 길이 <= 1000000
 * edges의 원소는 [a, b] 형태이며, a번 정점에서 b번 정점으로 향하는 간선이 있다는 것을 나타냅니다.
 * 1 <= a, b <= 1000000
 * 도넛 모양 그래프, 막대 모양 그래프, 8자 모양 그래프의 수의 합은 2 이상입니다.
 */

fun main() {
    val edges = arrayOf(
        intArrayOf(4, 11),
        intArrayOf(1, 12),
        intArrayOf(8, 3),
        intArrayOf(12, 7),
        intArrayOf(4, 2),
        intArrayOf(7, 11),
        intArrayOf(4, 8),
        intArrayOf(9, 6),
        intArrayOf(10, 11),
        intArrayOf(6, 10),
        intArrayOf(3, 5),
        intArrayOf(11, 1),
        intArrayOf(5, 3),
        intArrayOf(11, 9),
        intArrayOf(3, 8)
    )

    val solution = Solution().solution(edges)

    println(solution.joinToString(", "))
}

class Solution {
    fun solution(edges: Array<IntArray>): IntArray {
        val n = edges.maxOf { maxOf(it[0], it[1]) }
        val inEdges = IntArray(n + 1)
        val outEdges = IntArray(n + 1)
        val graph = Array(n + 1) { mutableListOf<Int>() }

        // 단방향 간선 연결 및 노드별 in, out 계산
        for ((from, to) in edges) {
            graph[from].add(to)
            outEdges[from]++
            inEdges[to]++
        }

        // in이 0이고 out이 2개 이상이면 정점 노드
        val topNode = (1..n).find { inEdges[it] == 0 && outEdges[it] > 1 } ?: -1
        var donut = 0
        var stick = 0
        var eight = 0
        val visited = BooleanArray(n + 1)

        // 정점 노드 기준으로 연결된 노드들 그래프 모양 판별
        for (startNode in graph[topNode]) {
            // 방문 여부 확인
            if (visited[startNode]) {
                continue
            }

            val queue = LinkedList<Int>()
            val passedNodes = mutableSetOf<Int>()
            val passedEdges = mutableSetOf<Pair<Int, Int>>()

            queue.offer(startNode)
            visited[startNode] = true

            while (queue.isNotEmpty()) {
                val currentNode = queue.poll()

                passedNodes.add(currentNode)

                for (nextNode in graph[currentNode]) {
                    passedEdges.add(currentNode to nextNode)

                    if (!visited[nextNode]) {
                        queue.offer(nextNode)
                        visited[nextNode] = true
                    }
                }
            }

            // 그래프 모양 판별
            if (passedNodes.size == passedEdges.size) {
                donut++
            } else if (passedNodes.size == passedEdges.size + 1) {
                stick++
            } else if (passedNodes.size + 1 == passedEdges.size) {
                eight++
            }
        }

        return intArrayOf(topNode, donut, stick, eight)
    }
}
