import java.util.LinkedList

/**
 * Given the root of a binary tree, return its maximum depth.
 * A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 *
 * Constraints:
 * The number of nodes in the tree is in the range [0, 104].
 * -100 <= Node.val <= 100
 */

fun main() {
    val root = TreeNode(3, TreeNode(9), TreeNode(20, TreeNode(15), TreeNode(7)))

    val solution = Solution().maxDepth(root)

    println(solution)
}

class TreeNode(
    var `val`: Int,
    var left: TreeNode? = null,
    var right: TreeNode? = null
)

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(N)
    // 밸런스 트리일 경우 공간 복잡도가 O(log N)으로 최적화
    fun maxDepth(root: TreeNode?): Int {
        if (root == null) return 0

        val leftDepth = maxDepth(root.left)
        val rightDepth = maxDepth(root.right)

        return 1 + maxOf(leftDepth, rightDepth)
    }

//    // 시간 복잡도 : O(N)
//    // 공간 복잡도 : O(N)
//    fun maxDepth(root: TreeNode?): Int {
//        if (root == null) return 0
//
//        var depth = 0
//        val queue = LinkedList<TreeNode>()
//
//        queue.offer(root)
//
//        while (queue.isNotEmpty()) {
//            repeat(queue.size) {
//                val node = queue.poll()
//
//                node.left?.let { queue.offer(it) }
//                node.right?.let { queue.offer(it) }
//            }
//
//            depth++
//        }
//
//        return depth
//    }
}