/**
 * n개의 섬 사이에 다리를 건설하는 비용(costs)이 주어질 때,
 * 최소의 비용으로 모든 섬이 서로 통행 가능하도록 만들 때 필요한 최소 비용을 return 하도록 solution을 완성하세요.
 *
 * 다리를 여러 번 건너더라도, 도달할 수만 있으면 통행 가능하다고 봅니다.
 * 예를 들어 A 섬과 B 섬 사이에 다리가 있고, B 섬과 C 섬 사이에 다리가 있으면 A 섬과 C 섬은 서로 통행 가능합니다.
 *
 * 1 <= n <= 100
 * costs의 길이 <= ((n - 1) * n) / 2
 *
 * costs[i][0] 과 costs[i][1] 은 다리가 연결되는 두 섬의 번호, costs[i][2] 는 비용입니다.
 *
 * 모든 섬 사이의 다리 건설 비용이 주어지지 않습니다.
 * 이 경우, 두 섬 사이의 건설이 불가능한 것으로 봅니다.
 */

fun main() {
    val n = 4
    val costs = arrayOf(
        intArrayOf(0, 1, 1),
        intArrayOf(0, 2, 2),
        intArrayOf(1, 2, 5),
        intArrayOf(1, 3, 1),
        intArrayOf(2, 3, 8)
    )

    val result = Solution().solution(n, costs)

    println(result)
}

class Solution {
    fun solution(n: Int, costs: Array<IntArray>): Int {
        // 비용 기준으로 간선 오름차순 정렬
        costs.sortBy { it[2] }

        // Union-Find 알고리즘
        val parent = IntArray(n) { it }

        // Find (재귀를 통해 부모 노드 찾기)
        fun find(node: Int): Int {
            if (parent[node] != node) {
                parent[node] = find(parent[node])
            }

            return parent[node]
        }

        // Union (두 노드 연결)
        fun union(a: Int, b: Int) {
            val rootA = find(a)
            val rootB = find(b)

            if (rootA != rootB) {
                parent[rootB] = rootA
            }
        }

        var result = 0
        var bridge = 0

        for ((from, to, cost) in costs) {
            if (find(from) != find(to)) {
                union(from, to)
                result += cost
                bridge++

                if (bridge == n - 1) {
                    break
                }
            }
        }

        return result
    }
}
