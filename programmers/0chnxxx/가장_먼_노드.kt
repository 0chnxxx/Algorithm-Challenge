/**
 * n개의 노드가 있는 그래프가 있습니다.
 * 각 노드는 1부터 n까지 번호가 적혀있습니다.
 * 1번 노드에서 가장 멀리 떨어진 노드의 갯수를 구하려고 합니다.
 * 가장 멀리 떨어진 노드란 최단경로로 이동했을 때 간선의 개수가 가장 많은 노드들을 의미합니다.
 *
 * 노드의 개수 n, 간선에 대한 정보가 담긴 2차원 배열 vertex가 매개변수로 주어질 때
 * 1번 노드로부터 가장 멀리 떨어진 노드가 몇 개인지를 return하도록 solution 함수를 작성해주세요.
 *
 * 2 <= n <= 20000
 * 1 <= vertex의 길이 <= 50000 (간선은 양방향)
 */

fun main() {
    val n = 6
    val vertex = arrayOf(
        intArrayOf(3, 6),
        intArrayOf(4, 3),
        intArrayOf(3, 2),
        intArrayOf(1, 3),
        intArrayOf(1, 2),
        intArrayOf(2, 4),
        intArrayOf(5, 2)
    )

    val result = Solution().solution(n, vertex)

    println(result)
}

class Solution {
    fun solution(n: Int, edge: Array<IntArray>): Int {
        val graph = Array(n + 1) { mutableListOf<Int>() }

        for ((from, to) in edge) {
            graph[from].add(to)
            graph[to].add(from)
        }

        val queue = ArrayDeque<Int>()
        val distances = IntArray(n + 1) { -1 }

        queue.add(1)
        distances[1] = 0

        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()

            for (neighbor in graph[current]) {
                if (distances[neighbor] == -1) {
                    queue.add(neighbor)
                    distances[neighbor] = distances[current] + 1
                }
            }
        }

        val maxDistance = distances.maxOrNull()!!

        return distances.count { it == maxDistance }
    }
}
