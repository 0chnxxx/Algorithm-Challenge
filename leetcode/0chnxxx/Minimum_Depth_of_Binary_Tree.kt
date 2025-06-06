import java.util.LinkedList

/**
 * Given a binary tree, find its minimum depth.
 * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
 * Note: A leaf is a node with no children.
 *
 * Constraints:
 * The number of nodes in the tree is in the range [0, 10^5].
 * -1000 <= Node.val <= 1000
 */

fun main() {
    val root = TreeNode(3, TreeNode(9), TreeNode(20, TreeNode(15), TreeNode(7)))

    val solution = Solution().minDepth(root)

    println(solution)
}

class TreeNode(
    var `val`: Int,
    var left: TreeNode? = null,
    var right: TreeNode? = null
)

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(N)
    fun minDepth(root: TreeNode?): Int {
        if (root == null) return 0

        val queue = LinkedList<Pair<TreeNode, Int>>()

        queue.offer(Pair(root, 1))

        while (queue.isNotEmpty()) {
            val (node, depth) = queue.poll()

            // 리프 노드를 찾으면 얼리 리턴
            if (node.left == null && node.right == null) return depth

            node.left?.let { queue.offer(Pair(it, depth + 1)) }
            node.right?.let { queue.offer(Pair(it, depth + 1)) }
        }

        return 0
    }
}
