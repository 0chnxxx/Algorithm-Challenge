/**
 * Given the root of a binary tree, return the inorder traversal of its nodes' values.
 *
 * Constraints:
 * The number of nodes in the tree is in the range [0, 100].
 * -100 <= Node.val <= 100
 */

fun main() {
    val root = TreeNode(1, null, TreeNode(2, TreeNode(3, null, null), null))

    val solution = Solution().inorderTraversal(root)

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
    fun inorderTraversal(root: TreeNode?): List<Int> {
        val result = mutableListOf<Int>()

        fun travel(node: TreeNode?) {
            if (node == null) return

            travel(node.left)
            result.add(node.`val`)
            travel(node.right)
        }

        travel(root)

        return result
    }
}