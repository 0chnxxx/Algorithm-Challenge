/**
 * Given the root of a Binary Search Tree (BST), return the minimum absolute difference between the values of any two different nodes in the tree.
 *
 * Constraints:
 * The number of nodes in the tree is in the range [2, 10^4].
 * 0 <= Node.val <= 10^5
 *
 * Note:
 * This question is the same as 783: https://leetcode.com/problems/minimum-distance-between-bst-nodes/
 */

fun main() {
    val root = TreeNode(4)
    val node1 = TreeNode(2)
    val node2 = TreeNode(6)
    val node3 = TreeNode(1)
    val node4 = TreeNode(3)

    node1.left = node3
    node1.right = node4

    root.left = node1
    root.right = node2

    val result = Solution().getMinimumDifference(root)

    println(result)
}

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class Solution {
    private var prev: Int? = null
    private var minDiff = Int.MAX_VALUE

    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(N)
    fun getMinimumDifference(root: TreeNode?): Int {
        inorder(root)

        return minDiff
    }

    private fun inorder(node: TreeNode?) {
        if (node == null) return

        // 왼쪽
        inorder(node.left)

        // 현재 노드 처리
        if (prev != null) {
            minDiff = minOf(minDiff, node.`val` - prev!!)
        }
        prev = node.`val`

        // 오른쪽
        inorder(node.right)
    }
}