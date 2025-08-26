/**
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) node of two given nodes in the BST.
 *
 * According to the definition of LCA on Wikipedia:
 * “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
 *
 * Constraints:
 * The number of nodes in the tree is in the range [2, 10^5].
 * -10^9 <= Node.val <= 10^9
 * All Node.val are unique.
 * p != q
 * p and q will exist in the BST.
 */

fun main() {
    val root = TreeNode(6, TreeNode(2, TreeNode(0), TreeNode(4, TreeNode(3), TreeNode(5))), TreeNode(8, TreeNode(7), TreeNode(9)))
    val p = 2
    val q = 8

    val result = Solution().lowestCommonAncestor(root, TreeNode(p), TreeNode(q))

    println(result)
}

class TreeNode(
    var `val`: Int = 0,
    var left: TreeNode? = null,
    var right: TreeNode? = null
)

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        var node = root
        val pVal = p!!.`val`
        val qVal = q!!.`val`

        while (node != null) {
            val rootVal = node.`val`

            // p, q가 root보다 작은 경우 (왼쪽)
            if (pVal < rootVal && qVal < rootVal) {
                node = node.left
            // p, q가 root보다 큰 경우 (오른쪽)
            } else if (pVal > rootVal && qVal > rootVal) {
                node = node.right
            // p, q가 root와 같은 경우
            } else {
                return node
            }
        }

        return null
    }
}