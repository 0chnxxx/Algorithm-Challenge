/**
 * Given the root of a binary search tree and an integer k, return true if there exist two elements in the BST such that their sum is equal to k, or false otherwise.
 *
 * Constraints:
 * The number of nodes in the tree is in the range [1, 10^4].
 * -10^4 <= Node.val <= 10^4
 * root is guaranteed to be a valid binary search tree.
 * -10^5 <= k <= 10^5
 */

fun main() {
    val k = 9

    val root = TreeNode(5)
    val node1 = TreeNode(3)
    val node2 = TreeNode(6)
    val node3 = TreeNode(2)
    val node4 = TreeNode(4)
    val node5 = TreeNode(7)

    node1.left = node3
    node1.right = node4

    node2.right = node5

    root.left = node1
    root.right = node2

    val result = Solution().findTarget(root, k)

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
    fun findTarget(root: TreeNode?, k: Int): Boolean {
        val set = HashSet<Int>()
        return dfs(root, k, set)
    }

    private fun dfs(node: TreeNode?, k: Int, set: HashSet<Int>): Boolean {
        if (node == null) return false

        if (set.contains(k - node.`val`)) return true

        set.add(node.`val`)

        return dfs(node.left, k, set) || dfs(node.right, k, set)
    }
}