import java.util.LinkedList

/**
 * Given the root of a binary tree, return the leftmost value in the last row of the tree.
 *
 * Constraints:
 * The number of nodes in the tree is in the range [1, 10^4].
 * -2^31 <= Node.val <= 2^31 - 1
 */

fun main() {
    val root = TreeNode(2)
    val node1 = TreeNode(1)
    val node2 = TreeNode(3)

    root.left = node1
    root.right = node2

    val result = Solution().findBottomLeftValue(root)

    println(result)
}

class TreeNode(
    var `val`: Int
) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(N)
    fun findBottomLeftValue(root: TreeNode?): Int {
        val queue = ArrayDeque<TreeNode>()

        queue.add(root!!)

        var result = root.`val`

        while (queue.isNotEmpty()) {
            val levelSize = queue.size

            for (i in 0 until levelSize) {
                val node = queue.removeFirst()

                if (i == 0) {
                    result = node.`val`
                }

                if (node.left != null) queue.add(node.left!!)
                if (node.right != null) queue.add(node.right!!)
            }
        }

        return result
    }
}