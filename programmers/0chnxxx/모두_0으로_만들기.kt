import java.util.*
import kotlin.math.absoluteValue

/**
 * 각 점에 가중치가 부여된 트리가 주어집니다.
 *
 * 당신은 다음 연산을 통하여, 이 트리의 모든 점들의 가중치를 0으로 만들고자 합니다.
 * - 임의의 연결된 두 점을 골라서 한쪽은 1 증가시키고, 다른 한쪽은 1 감소시킵니다.
 *
 * 하지만, 모든 트리가 위의 행동을 통하여 모든 점들의 가중치를 0으로 만들 수 있는 것은 아닙니다.
 * 당신은 주어진 트리에 대해서 해당 사항이 가능한지 판별하고, 만약 가능하다면 최소한의 행동을 통하여 모든 점들의 가중치를 0으로 만들고자 합니다.
 *
 * 트리의 각 점의 가중치를 의미하는 1차원 정수 배열 a와 트리의 간선 정보를 의미하는 edges가 매개변수로 주어집니다.
 * 주어진 행동을 통해 트리의 모든 점들의 가중치를 0으로 만드는 것이 불가능하다면 -1을, 가능하다면 최소 몇 번만에 가능한지를 찾아 return 하도록 solution 함수를 완성해주세요.
 * (만약 처음부터 트리의 모든 정점의 가중치가 0이라면, 0을 return해야 합니다.)
 *
 * 2 <= a의 길이 <= 300000
 * -1000000 <= a의 모든 수 <= 1000000
 * a[i]는 i번 정점의 가중치를 의미합니다.
 * edges의 행의 개수 = a의 길이 - 1
 * edges의 각 행은 [u, v]이며 u번 정점과 v번 정점이 간선으로 연결되어 있음을 의미합니다.
 * edges가 나타내는 그래프는 항상 트리로 주어집니다.
 */

fun main() {
    val a = intArrayOf(-5, 0, 2, 1, 2)
    val edges = arrayOf(
        intArrayOf(0, 1),
        intArrayOf(3, 4),
        intArrayOf(2, 3),
        intArrayOf(0, 3)
    )

    val solution = Solution().solution(a, edges)

    println(solution)
}

class Solution {
    fun solution(a: IntArray, edges: Array<IntArray>): Long {
        // Int -> Long
        val aLong = a.map { it.toLong() }.toLongArray()
        val totalWeight = aLong.sum()

        // 총 가중치가 0이 아니면 불가능
        if (totalWeight != 0L) {
            return -1
        }

        // 양방향 그래프 생성
        val graph = Array(a.size) { mutableListOf<Int>() }

        for ((u, v) in edges) {
            graph[u].add(v)
            graph[v].add(u)
        }

        var result = 0L
        val visited = BooleanArray(a.size)
        val stack = Stack<Int>()
        val parent = IntArray(a.size) { -1 }

        stack.add(0)

        // 비재귀 DFS
        // 후위 순회를 위해 방문한 경우에 pop하고 처리
        // 방문하지 않았을 때 push를 함으로써 stack의 reverse 효과를 기대
        while (stack.isNotEmpty()) {
            val node = stack.peek()

            if (visited[node]) {
                stack.pop()

                if (node != 0) {
                    val p = parent[node]

                    // 가중치는 자식 -> 부모로 합산하고 자식은 0으로 변경
                    // 가중치의 절댓값은 이동 횟수로 누적 합산
                    aLong[p] += aLong[node]
                    result += aLong[node].absoluteValue
                    aLong[node] = 0
                }
            } else {
                visited[node] = true

                for (next in graph[node]) {
                    if (!visited[next]) {
                        parent[next] = node
                        stack.push(next)
                    }
                }
            }
        }

        return result
    }
}
