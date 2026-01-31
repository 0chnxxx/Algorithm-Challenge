/**
 * Given the root of a binary tree, return an array of the largest value in each row of the tree (0-indexed).
 *
 * Constraints:
 * The number of nodes in the tree will be in the range [0, 10^4].
 * -2^31 <= Node.val <= 2^31 - 1
 */

fun main() {
    val root = TreeNode(1)
    val node1 = TreeNode(3)
    val node2 = TreeNode(2)
    val node3 = TreeNode(5)
    val node4 = TreeNode(3)
    val node5 = TreeNode(9)

    node1.left = node3
    node1.right = node4

    node2.right = node5

    root.left = node1
    root.right = node2

    val result = Solution().largestValues(root)

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
    fun largestValues(root: TreeNode?): List<Int> {
        if (root == null) return emptyList()

        val result = mutableListOf<Int>()
        val queue = ArrayDeque<TreeNode>()

        queue.add(root)

        while (queue.isNotEmpty()) {
            val size = queue.size
            var max = Int.MIN_VALUE

            repeat(size) {
                val node = queue.removeFirst()
                max = maxOf(max, node.`val`)

                node.left?.let { queue.add(it) }
                node.right?.let { queue.add(it) }
            }

            result.add(max)
        }

        return result
    }
}
