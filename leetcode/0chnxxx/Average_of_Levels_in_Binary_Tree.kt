import java.util.LinkedList

/**
 * Given the root of a binary tree, return the average value of the nodes on each level in the form of an array.
 * Answers within 10-5 of the actual answer will be accepted.
 *
 * Constraints:
 * The number of nodes in the tree is in the range [1, 10^4].
 * -2^31 <= Node.val <= 2^31 - 1
 */

fun main() {
    val root = TreeNode(3)
    val node1 = TreeNode(9)
    val node2 = TreeNode(20)
    val node3 = TreeNode(15)
    val node4 = TreeNode(7)

    node2.left = node3
    node2.right = node4

    root.left = node1
    root.right = node2

    val result = Solution().averageOfLevels(root)

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
    // 공간 복잡도 : O(W)
    fun averageOfLevels(root: TreeNode?): DoubleArray {
        if (root == null) return doubleArrayOf()

        val result = mutableListOf<Double>()
        val queue = LinkedList<TreeNode>()

        queue.offer(root)

        while (queue.isNotEmpty()) {
            val size = queue.size
            var sum = 0L

            repeat(size) {
                val node = queue.poll()

                sum += node.`val`

                node.left?.let { queue.offer(it) }
                node.right?.let { queue.offer(it) }
            }

            result.add(sum.toDouble() / size)
        }

        return result.toDoubleArray()
    }
}