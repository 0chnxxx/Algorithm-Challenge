/**
 * Given the root of a binary tree, return the preorder traversal of its nodes' values.
 *
 * Constraints:
 * The number of nodes in the tree is in the range [0, 100].
 * -100 <= Node.val <= 100
 *
 * Follow up:
 * Recursive solution is trivial, could you do it iteratively?
 */

fun main() {
    val root = TreeNode(1, null, TreeNode(2, TreeNode(3)))

    val result = Solution().preorderTraversal(root)

    println(result)
}

class TreeNode(
    var `val`: Int,
    var left: TreeNode? = null,
    var right: TreeNode? = null
)

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(N)
    fun preorderTraversal(root: TreeNode?): List<Int> {
        val result = mutableListOf<Int>()

        fun dfs(node: TreeNode?) {
            if (node == null) return

            result.add(node.`val`)

            dfs(node.left)
            dfs(node.right)
        }

        dfs(root)

        return result
    }
}