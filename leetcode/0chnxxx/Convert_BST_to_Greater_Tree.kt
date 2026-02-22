/**
 * Given the root of a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the original key plus the sum of all keys greater than the original key in BST.
 *
 * As a reminder, a binary search tree is a tree that satisfies these constraints:
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 *
 * Constraints:
 * The number of nodes in the tree is in the range [0, 10^4].
 * -10^4 <= Node.val <= 10^4
 * All the values in the tree are unique.
 * root is guaranteed to be a valid binary search tree.
 *
 * Note:
 * This question is the same as 1038: https://leetcode.com/problems/binary-search-tree-to-greater-sum-tree/
 */

fun main() {
    val root = TreeNode(4)

    val node1 = TreeNode(1)
    val node2 = TreeNode(6)

    root.left = node1
    root.right = node2

    val node3 = TreeNode(0)
    val node4 = TreeNode(2)

    node1.left = node3
    node1.right = node4

    val node5 = TreeNode(5)
    val node6 = TreeNode(7)

    node2.left = node5
    node2.right = node6

    val node7 = TreeNode(3)
    val node8 = TreeNode(8)

    node4.right = node7
    node6.right = node8

    val result = Solution().convertBST(root)

    fun printInorder(node: TreeNode?) {
        if (node == null) return
        printInorder(node.left)
        print("${node.`val`} ")
        printInorder(node.right)
    }

    printInorder(result)
}

class TreeNode(
    var `val`: Int
) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class Solution {
    private var sum = 0

    fun convertBST(root: TreeNode?): TreeNode? {
        fun reverseInorder(node: TreeNode?) {
            if (node == null) return

            reverseInorder(node.right)

            sum += node.`val`
            node.`val` = sum

            reverseInorder(node.left)
        }

        reverseInorder(root)

        return root
    }
}
