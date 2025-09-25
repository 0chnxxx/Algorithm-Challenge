/**
 * A tree is an undirected graph in which any two vertices are connected by exactly one path.
 * In other words, any connected graph without simple cycles is a tree.
 * Given a tree of n nodes labelled from 0 to n - 1, and an array of n - 1 edges where edges[i] = [ai, bi] indicates that there is an undirected edge between the two nodes ai and bi in the tree, you can choose any node of the tree as the root.
 * When you select a node x as the root, the result tree has height h.
 * Among all possible rooted trees, those with minimum height (i.e. min(h))  are called minimum height trees (MHTs).
 * Return a list of all MHTs' root labels. You can return the answer in any order.
 * The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.
 *
 * Constraints:
 * 1 <= n <= 2 * 10^4
 * edges.length == n - 1
 * 0 <= ai, bi < n
 * ai != bi
 * All the pairs (ai, bi) are distinct.
 * The given input is guaranteed to be a tree and there will be no repeated edges.
 */

fun main() {
    val n = 4
    val edges = arrayOf(
        intArrayOf(1, 0),
        intArrayOf(1, 2),
        intArrayOf(1, 3)
    )

    val result = Solution().findMinHeightTrees(n, edges)

    println(result.joinToString(", "))
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(N)
    fun findMinHeightTrees(n: Int, edges: Array<IntArray>): List<Int> {
        if (n == 1) return listOf(0)

        val graph = mutableMapOf<Int, MutableList<Int>>()
        val degree = IntArray(n)

        // 양방향 그래프 생성 및 차수 계산
        for ((u, v) in edges) {
            graph.computeIfAbsent(u) { mutableListOf() }.add(v)
            graph.computeIfAbsent(v) { mutableListOf() }.add(u)
            degree[u]++
            degree[v]++
        }

        // 차수가 1인 노드들을 큐에 담음
        val queue = ArrayDeque<Int>()

        for (i in degree.indices) {
            if (degree[i] == 1) {
                queue.add(i)
            }
        }

        // 차수를 하나씩 leaf 부터 벗겨내기
        var remainingNodes = n
        var lastLayer = listOf<Int>()

        while (queue.isNotEmpty()) {
            val size = queue.size
            val currentLayer = mutableListOf<Int>()

            // 한 번 순회할 때 큐를 전부 털어냄
            repeat(size) {
                val node = queue.removeFirst()

                currentLayer.add(node)
                remainingNodes--

                // 연결된 노드들의 차수를 낮춤
                for (neighbor in graph[node] ?: emptyList()) {
                    degree[neighbor]--

                    // 새롭게 차수가 1이된 노드들을 다시 큐에 넣음
                    if (degree[neighbor] == 1) {
                        queue.add(neighbor)
                    }
                }
            }

            lastLayer = currentLayer
        }

        return lastLayer
    }
}