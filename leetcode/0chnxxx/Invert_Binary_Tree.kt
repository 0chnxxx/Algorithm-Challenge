/**
 * Given the root of a binary tree, invert the tree, and return its root.
 *
 * Constraints:
 * The number of nodes in the tree is in the range [0, 100].
 * -100 <= Node.val <= 100
 */

fun main() {
    val root = TreeNode(4, TreeNode(2, TreeNode(1), TreeNode(3)), TreeNode(7, TreeNode(6), TreeNode(9)))

    val result = Solution().invertTree(root)

    val output = mutableListOf<Int>()
    val queue = ArrayDeque<TreeNode?>()

    queue.add(result)

    while (queue.isNotEmpty()) {
        val node = queue.removeFirst()

        if (node != null) {
            output.add(node.`val`)
            queue.add(node.left)
            queue.add(node.right)
        }
    }

    println(output)
}

class TreeNode(
    var `val`: Int,
    var left: TreeNode? = null,
    var right: TreeNode? = null
)

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(N)
    fun invertTree(root: TreeNode?): TreeNode? {
        if (root == null) return null

        val left = invertTree(root.left)
        val right = invertTree(root.right)

        root.left = right
        root.right = left

        return root
    }
}
