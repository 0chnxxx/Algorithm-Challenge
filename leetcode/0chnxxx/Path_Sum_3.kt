/**
 * Given the root of a binary tree and an integer targetSum, return the number of paths where the sum of the values along the path equals targetSum.
 * The path does not need to start or end at the root or a leaf, but it must go downwards (i.e., traveling only from parent nodes to child nodes).
 *
 * Constraints:
 * The number of nodes in the tree is in the range [0, 1000].
 * -10^9 <= Node.val <= 10^9
 * -1000 <= targetSum <= 1000
 */

fun main() {
    val node1 = TreeNode(3)
    val node2 = TreeNode(-2)
    val node3 = TreeNode(3)
    node3.left = node1
    node3.right = node2

    val node4 = TreeNode(1)
    val node5 = TreeNode(2)
    node5.right = node4

    val node6 = TreeNode(5)
    node6.left = node3
    node6.right = node5

    val node7 = TreeNode(11)
    val node8 = TreeNode(-3)
    node8.right = node7

    val root = TreeNode(10)
    root.left = node6
    root.right = node8

    val result = Solution().pathSum(root, 8)

    println(result)
}

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class Solution {
    // 시간 복잡도 : O(N^2)
    // 공간 복잡도 : O(H)
    fun pathSum(root: TreeNode?, targetSum: Int): Int {
        if (root == null) return 0

        val rootPaths = dfs(root, targetSum.toLong())

        return rootPaths + pathSum(root.left, targetSum) + pathSum(root.right, targetSum)
    }

    private fun dfs(node: TreeNode?, target: Long): Int {
        if (node == null) return 0

        var count = 0

        if (node.`val`.toLong() == target) count++

        count += dfs(node.left, target - node.`val`)
        count += dfs(node.right, target - node.`val`)

        return count
    }
}