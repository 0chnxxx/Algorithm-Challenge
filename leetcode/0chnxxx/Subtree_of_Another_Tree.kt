/**
 * Given the roots of two binary trees root and subRoot, return true if there is a subtree of root with the same structure and node values of subRoot and false otherwise.
 * A subtree of a binary tree tree is a tree that consists of a node in tree and all of this node's descendants.
 * The tree tree could also be considered as a subtree of itself.
 *
 * Constraints:
 * The number of nodes in the root tree is in the range [1, 2000].
 * The number of nodes in the subRoot tree is in the range [1, 1000].
 * -10^4 <= root.val <= 10^4
 * -10^4 <= subRoot.val <= 10^4
 */

fun main() {
    val root = TreeNode(3)
    val node1 = TreeNode(4)
    val node2 = TreeNode(5)
    val node3 = TreeNode(1)
    val node4 = TreeNode(2)

    node1.left = node3
    node2.right = node4
    root.left = node1
    root.right = node2

    val subRoot = TreeNode(4)
    val node5 = TreeNode(1)
    val node6 = TreeNode(2)

    subRoot.left = node5
    subRoot.right = node6

    val result = Solution().isSubtree(root, subRoot)

    println(result)
}

class TreeNode(
    var `val`: Int
) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class Solution {
    // 시간 복잡도 : O(N * M)
    // 공간 복잡도 : O(N)
    fun isSubtree(root: TreeNode?, subRoot: TreeNode?): Boolean {
        if (root == null) return false

        if (isSameTree(root, subRoot)) return true

        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot)
    }

    private fun isSameTree(a: TreeNode?, b: TreeNode?): Boolean {
        if (a == null && b == null) return true
        if (a == null || b == null) return false

        if (a.`val` != b.`val`) return false

        return isSameTree(a.left, b.left) && isSameTree(a.right, b.right)
    }
}