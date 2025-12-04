/**
 * Given an n-ary tree, return the level order traversal of its nodes' values.
 * Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by the null value (See examples).
 *
 * Constraints:
 * The height of the n-ary tree is less than or equal to 1000
 * The total number of nodes is between [0, 10^4]
 */

fun main() {
    val childNode = Node(3)
    childNode.children = listOf(Node(5), Node(6))

    val rootNode = Node(1)
    rootNode.children = listOf(childNode, Node(2), Node(4))

    val result = Solution().levelOrder(rootNode)

    println(result.joinToString(", "))
}

class Node(
    var `val`: Int,
) {
    var children: List<Node?> = listOf()
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(N)
    fun levelOrder(root: Node?): List<List<Int>> {
        if (root == null) return emptyList()

        val result = mutableListOf<List<Int>>()
        val queue: ArrayDeque<Node> = ArrayDeque()

        queue.add(root)

        while (queue.isNotEmpty()) {
            val levelSize = queue.size
            val levelList = mutableListOf<Int>()

            repeat(levelSize) {
                val node = queue.removeFirst()

                levelList.add(node.`val`)
                node.children.forEach { child ->
                    if (child != null) queue.add(child)
                }
            }

            result.add(levelList)
        }

        return result
    }
}