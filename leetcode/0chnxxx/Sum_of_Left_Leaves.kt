/**
 * Given the root of a binary tree, return the sum of all left leaves.
 * A leaf is a node with no children.
 * A left leaf is a leaf that is the left child of another node.
 *
 * Constraints:
 * The number of nodes in the tree is in the range [1, 1000].
 * -1000 <= Node.val <= 1000
 */

class TreeNode(
    var `val`: Int,
    var left: TreeNode? = null,
    var right: TreeNode? = null
)

fun main() {
    val root = TreeNode(3, TreeNode(9), TreeNode(20, TreeNode(15), TreeNode(7)))

    val result = Solution().sumOfLeftLeaves(root)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(N)
    fun sumOfLeftLeaves(root: TreeNode?): Int {
        if (root == null) return 0

        var sum = 0

        if (root.left != null && root.left!!.left == null && root.left!!.right == null) {
            sum += root.left!!.`val`
        }

        sum += sumOfLeftLeaves(root.left)
        sum += sumOfLeftLeaves(root.right)

        return sum
    }
}