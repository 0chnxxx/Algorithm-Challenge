/**
 * Given a reference of a node in a connected undirected graph.
 * Return a deep copy (clone) of the graph.
 * Each node in the graph contains a value (int) and a list (List[Node]) of its neighbors.
 *
 * class Node {
 *     public int val;
 *     public List<Node> neighbors;
 * }
 *
 * Test case format:
 * For simplicity, each node's value is the same as the node's index (1-indexed).
 * For example, the first node with val == 1, the second node with val == 2, and so on.
 * The graph is represented in the test case using an adjacency list.
 * An adjacency list is a collection of unordered lists used to represent a finite graph.
 * Each list describes the set of neighbors of a node in the graph.
 * The given node will always be the first node with val = 1.
 * You must return the copy of the given node as a reference to the cloned graph.
 *
 * Constraints:
 * The number of nodes in the graph is in the range [0, 100].
 * 1 <= Node.val <= 100
 * Node.val is unique for each node.
 * There are no repeated edges and no self-loops in the graph.
 * The Graph is connected and all nodes can be visited starting from the given node.
 */

fun main() {
    val node1 = Node(1)
    val node2 = Node(2)
    val node3 = Node(3)
    val node4 = Node(4)

    node1.neighbors.addAll(listOf(node2, node4))
    node2.neighbors.addAll(listOf(node1, node3))
    node3.neighbors.addAll(listOf(node2, node4))
    node4.neighbors.addAll(listOf(node1, node3))

    val solution = Solution().cloneGraph(node1)

    fun printGraph(node: Node?, visited: MutableSet<Node>) {
        if (node == null || visited.contains(node)) return

        visited.add(node)

        print("Node ${node.`val`} neighbors: ")
        println(node.neighbors.map { it?.`val` })

        for (neighbor in node.neighbors) {
            printGraph(neighbor, visited)
        }
    }

    println("Original Graph:")
    printGraph(node1, mutableSetOf())

    println("\nCloned Graph:")
    printGraph(solution, mutableSetOf())
}

class Node(
    var `val`: Int,
    var neighbors: ArrayList<Node?> = ArrayList<Node?>()
)

class Solution {
    // 시간 복잡도 : O(N + E)
    // 공간 복잡도 : O(N + E)
    fun cloneGraph(node: Node?): Node? {
        // 중복 방문을 제거하기 위한 맵
        val visited = HashMap<Node, Node>()

        // DFS를 통한 방문
        fun dfs(node: Node?): Node? {
            if (node == null) return null

            // 이미 방문했던 노드라면 복사된 노드를 반환함
            if (visited.containsKey(node)) {
                return visited[node]
            }

            // 노드를 복사하고 방문 여부에 넣어둠
            val clone = Node(node.`val`)
            visited[node] = clone

            // 해당 노드의 이웃노드들을 순회하면서 복사
            for (neighbor in node.neighbors) {
                clone.neighbors.add(dfs(neighbor))
            }

            // 복사된 노드를 반환함
            return clone
        }

        return dfs(node)
    }
}
