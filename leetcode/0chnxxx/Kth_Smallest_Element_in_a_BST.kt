/**
 * Given the root of a binary search tree, and an integer k, return the kth smallest value (1-indexed) of all the values of the nodes in the tree.
 *
 * Constraints:
 * The number of nodes in the tree is n.
 * 1 <= k <= n <= 10^4
 * 0 <= Node.val <= 10^4
 *
 * Follow up:
 * If the BST is modified often (i.e., we can do insert and delete operations) and you need to find the kth smallest frequently, how would you optimize?
 */

fun main() {
    val root = TreeNode(3, TreeNode(1, null, TreeNode(2)), TreeNode(4))
    val k = 1

    val result = Solution().kthSmallest(root, k)

    println(result)
}

class TreeNode(
    val `val`: Int,
    var left: TreeNode? = null,
    var right: TreeNode? = null,
)

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(N)
    fun kthSmallest(root: TreeNode?, k: Int): Int {
        var count = 0
        var small = -1

        fun recursive(node: TreeNode?, k: Int) {
            if (node == null) return

            recursive(node.left, k)

            count++

            if (count == k) {
                small = node.`val`
                return
            }

            recursive(node.right, k)
        }

        recursive(root, k)

        return small
    }
}
