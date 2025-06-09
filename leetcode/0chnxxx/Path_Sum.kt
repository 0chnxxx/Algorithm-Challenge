/**
 * Given the root of a binary tree and an integer targetSum, return true if the tree has a root-to-leaf path such that adding up all the values along the path equals targetSum.
 * A leaf is a node with no children.
 *
 * Constraints:
 * The number of nodes in the tree is in the range [0, 5000].
 * -1000 <= Node.val <= 1000
 * -1000 <= targetSum <= 1000
 */

fun main() {
    val root = TreeNode(
        5,
        TreeNode(4,
            TreeNode(11,
                TreeNode(7),
                TreeNode(2)
            )
        ),
        TreeNode(8,
            TreeNode(13),
            TreeNode(4,
                null,
                TreeNode(1)
            )
        )
    )
    val targetSum = 22

    val solution = Solution().hasPathSum(root, targetSum)

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
    fun hasPathSum(root: TreeNode?, targetSum: Int): Boolean {
        if (root == null) return false

        var result = false

        fun recursive(node: TreeNode?, sum: Int) {
            if (node == null) return

            val currentSum = sum + node.`val`

            if (node.left == null && node.right == null && currentSum == targetSum) {
                result = true
            }

            node.left?.let { recursive(it, currentSum) }
            node.right?.let { recursive(it, currentSum) }
        }

        recursive(root, 0)

        return result
    }
}
