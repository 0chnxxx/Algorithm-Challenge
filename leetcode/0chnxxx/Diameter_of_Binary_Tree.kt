/**
 * Given the root of a binary tree, return the length of the diameter of the tree.
 * The diameter of a binary tree is the length of the longest path between any two nodes in a tree.
 * This path may or may not pass through the root.
 * The length of a path between two nodes is represented by the number of edges between them.
 *
 * Constraints:
 * The number of nodes in the tree is in the range [1, 10^4].
 * -100 <= Node.val <= 100
 */

fun main() {
    val root = TreeNode(1)
    val node1 = TreeNode(2)
    val node2 = TreeNode(3)
    val node3 = TreeNode(4)
    val node4 = TreeNode(5)

    root.left = node1
    root.right = node2

    node1.left = node3
    node1.right = node4

    val result = Solution().diameterOfBinaryTree(root)

    println(result)
}

class TreeNode(
    var `val`: Int
) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class Solution {
    private var diameter = 0

    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(N)
    fun diameterOfBinaryTree(root: TreeNode?): Int {
        fun height(node: TreeNode?): Int {
            if (node == null) return 0

            val left = height(node.left)
            val right = height(node.right)

            diameter = maxOf(diameter, left + right)

            return maxOf(left, right) + 1
        }

        height(root)

        return diameter
    }
}
