/**
 * The thief has found himself a new place for his thievery again.
 * There is only one entrance to this area, called root.
 * Besides the root, each house has one and only one parent house.
 * After a tour, the smart thief realized that all houses in this place form a binary tree.
 * It will automatically contact the police if two directly-linked houses were broken into on the same night.
 * Given the root of the binary tree, return the maximum amount of money the thief can rob without alerting the police.
 *
 * Constraints:
 * The number of nodes in the tree is in the range [1, 10^4].
 * 0 <= Node.val <= 10^4
 */

fun main() {
    val root = TreeNode(3, TreeNode(2, null, TreeNode(3)), TreeNode(3, null, TreeNode(1)))

    val result = Solution().rob(root)

    println(result)
}

class TreeNode(
    var `val`: Int,
    var left: TreeNode? = null,
    var right: TreeNode? = null
)

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(N)
    fun rob(root: TreeNode?): Int {
        fun dfs(node: TreeNode?): Pair<Int, Int> {
            if (node == null) return 0 to 0

            val (leftRob, leftNotRob) = dfs(node.left)
            val (rightRob, rightNotRob) = dfs(node.right)

            val robCurrent = node.`val` + leftNotRob + rightNotRob
            val notRobCurrent = maxOf(leftRob, leftNotRob) + maxOf(rightRob, rightNotRob)

            return robCurrent to notRobCurrent
        }

        val (rob, notRob) = dfs(root)

        return maxOf(rob, notRob)
    }
}
