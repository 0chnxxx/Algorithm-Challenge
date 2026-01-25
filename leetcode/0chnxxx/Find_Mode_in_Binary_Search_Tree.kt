/**
 * Given the root of a binary search tree (BST) with duplicates, return all the mode(s) (i.e., the most frequently occurred element) in it.
 * If the tree has more than one mode, return them in any order.
 *
 * Assume a BST is defined as follows:
 * The left subtree of a node contains only nodes with keys less than or equal to the node's key.
 * The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
 * Both the left and right subtrees must also be binary search trees.
 *
 * Constraints:
 * The number of nodes in the tree is in the range [1, 104].
 * -10^5 <= Node.val <= 10^5
 *
 * Follow up:
 * Could you do that without using any extra space? (Assume that the implicit stack space incurred due to recursion does not count).
 */

fun main() {
    val root = TreeNode(1, null, TreeNode(2, TreeNode(2, null, null), null))

    val result = Solution().findMode(root)

    println(result.contentToString())
}

class TreeNode(
    var `val`: Int,
    var left: TreeNode? = null,
    var right: TreeNode? = null
)

class Solution {
    private var prev: Int? = null
    private var count = 0
    private var maxCount = 0
    private val result = mutableListOf<Int>()

    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun findMode(root: TreeNode?): IntArray {
        fun inorder(node: TreeNode?) {
            if (node == null) return

            inorder(node.left)

            if (prev == node.`val`) {
                count++
            } else {
                prev = node.`val`
                count = 1
            }

            when {
                count > maxCount -> {
                    maxCount = count
                    result.clear()
                    result.add(node.`val`)
                }
                count == maxCount -> {
                    result.add(node.`val`)
                }
            }

            inorder(node.right)
        }

        inorder(root)

        return result.toIntArray()
    }
}