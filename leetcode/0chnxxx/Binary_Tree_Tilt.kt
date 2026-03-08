/**
 * Given the root of a binary tree, return the sum of every tree node's tilt.
 * The tilt of a tree node is the absolute difference between the sum of all left subtree node values and all right subtree node values.
 * If a node does not have a left child, then the sum of the left subtree node values is treated as 0.
 * The rule is similar if the node does not have a right child.
 *
 * Constraints:
 * The number of nodes in the tree is in the range [0, 10^4].
 * -1000 <= Node.val <= 1000
 */

fun main() {
    val root = TreeNode(1)
    val left = TreeNode(2)
    val right = TreeNode(3)

    root.left = left
    right.right = right

    val result = Solution().findTilt(root)

    println(result)
}

class TreeNode(
    var `val`: Int
) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class Solution {
    private var totalTilt = 0

    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(H)
    fun findTilt(root: TreeNode?): Int {
        dfs(root)
        return totalTilt
    }

    private fun dfs(node: TreeNode?): Int {
        if (node == null) return 0

        val leftSum = dfs(node.left)
        val rightSum = dfs(node.right)

        val tilt = Math.abs(leftSum - rightSum)

        totalTilt += tilt

        return leftSum + rightSum + node.`val`
    }
}