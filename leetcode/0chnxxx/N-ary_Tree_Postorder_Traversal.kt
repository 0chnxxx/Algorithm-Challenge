/**
 * Given the root of an n-ary tree, return the postorder traversal of its nodes' values.
 * Nary-Tree input serialization is represented in their level order traversal.
 * Each group of children is separated by the null value (See examples)
 *
 * Constraints:
 * The number of nodes in the tree is in the range [0, 10^4].
 * 0 <= Node.val <= 10^4
 * The height of the n-ary tree is less than or equal to 1000.
 *
 * Follow up:
 * Recursive solution is trivial, could you do it iteratively?
 */

fun main() {
    val root = Node(1)
    val node1 = Node(3)
    val node2 = Node(2)
    val node3 = Node(4)
    val node4 = Node(5)
    val node5 = Node(6)

    node1.children = listOf(node4, node5)
    root.children = listOf(node1, node2, node3)

    val result = Solution().postorder(root)

    println(result)
}

class Node(
    var `val`: Int
) {
    var children: List<Node?> = listOf()
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(N)
    fun postorder(root: Node?): List<Int> {
        val result = mutableListOf<Int>()

        dfs(root, result)

        return result
    }

    private fun dfs(node: Node?, result: MutableList<Int>) {
        if (node == null) return

        for (child in node.children) {
            dfs(child, result)
        }

        result.add(node.`val`)
    }
}
