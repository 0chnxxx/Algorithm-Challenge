/**
 * You are given two binary trees root1 and root2.
 * Imagine that when you put one of them to cover the other, some nodes of the two trees are overlapped while the others are not.
 * You need to merge the two trees into a new binary tree.
 * The merge rule is that if two nodes overlap, then sum node values up as the new value of the merged node.
 * Otherwise, the NOT null node will be used as the node of the new tree.
 * Return the merged tree.
 *
 * Note:
 * The merging process must start from the root nodes of both trees.
 *
 * Constraints:
 * The number of nodes in both trees is in the range [0, 2000].
 * -10^4 <= Node.val <= 10^4
 */

fun main() {
    val root1 = TreeNode(1)
    val node1 = TreeNode(3)
    val node2 = TreeNode(2)
    val node3 = TreeNode(5)

    node1.left = node3
    root1.left = node1
    root1.right = node2

    val root2 = TreeNode(2)
    val node4 = TreeNode(1)
    val node5 = TreeNode(3)
    val node6 = TreeNode(4)
    val node7 = TreeNode(7)

    node4.right = node6
    node5.right = node7
    root2.left = node4
    root2.right = node5

    val result = Solution().mergeTrees(root1, root2)

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
    fun mergeTrees(root1: TreeNode?, root2: TreeNode?): TreeNode? {
        if (root1 == null && root2 == null) return null

        if (root1 == null) return root2
        if (root2 == null) return root1

        val merged = TreeNode(root1.`val` + root2.`val`)

        merged.left = mergeTrees(root1.left, root2.left)
        merged.right = mergeTrees(root1.right, root2.right)

        return merged
    }
}