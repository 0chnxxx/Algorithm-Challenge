/**
 * Given the root of a binary tree, return the most frequent subtree sum. If there is a tie, return all the values with the highest frequency in any order.
 *
 * The subtree sum of a node is defined as the sum of all the node values formed by the subtree rooted at that node (including the node itself).
 *
 * Constraints:
 * The number of nodes in the tree is in the range [1, 10^4].
 * -10^5 <= Node.val <= 10^5
 */

fun main() {
    val root = TreeNode(5)
    val node1 = TreeNode(2)
    val node2 = TreeNode(-3)

    root.left = node1
    root.right = node2

    val result = Solution().findFrequentTreeSum(root)

    println(result)
}

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class Solution {
    private val map = mutableMapOf<Int, Int>()

    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(N)
    fun findFrequentTreeSum(root: TreeNode?): IntArray {
        fun dfs(node: TreeNode?): Int {
            if (node == null) return  0

            val leftSum = dfs(node.left)
            val rightSum = dfs(node.right)

            val sum = node.`val` + leftSum + rightSum

            map[sum] = (map[sum] ?: 0) + 1

            return sum
        }

        dfs(root)

        val max = map.values.maxOrNull() ?: 0

        return map.filter { it.value == max }.keys.toIntArray()
    }
}