import java.util.LinkedList

/**
 * Given the root of a binary tree, return the bottom-up level order traversal of its nodes' values.
 * (i.e., from left to right, level by level from leaf to root).
 *
 * Constraints:
 * The number of nodes in the tree is in the range [0, 2000].
 * -1000 <= Node.val <= 1000
 */

fun main() {
    val root = TreeNode(3, TreeNode(9), TreeNode(20, TreeNode(15), TreeNode(7)))

    val solution = Solution().levelOrderBottom(root)

    println(solution.joinToString(", "))
}

class TreeNode(
    var `val`: Int,
    var left: TreeNode? = null,
    var right: TreeNode? = null
)

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(N)
    // reversed() 를 제거하여 성능 최적화
    fun levelOrderBottom(root: TreeNode?): List<List<Int>> {
        if (root == null) return emptyList()

        val result = LinkedList<List<Int>>()
        val queue = LinkedList<TreeNode>()

        queue.offer(root)

        while (queue.isNotEmpty()) {
            val nodeSize = queue.size
            val values = mutableListOf<Int>()

            repeat(nodeSize) {
                val node = queue.poll()

                values.add(node.`val`)

                node.left?.let { queue.offer(it) }
                node.right?.let { queue.offer(it) }
            }

            result.addFirst(values)
        }

        return result
    }

//    // 시간 복잡도 : O(N)
//    // 공간 복잡도 : O(N)
//    fun levelOrderBottom(root: TreeNode?): List<List<Int>> {
//        if (root == null) return emptyList()
//
//        val result = mutableListOf<List<Int>>()
//        val queue = LinkedList<TreeNode>()
//
//        queue.offer(root)
//
//        while (queue.isNotEmpty()) {
//            val nodeSize = queue.size
//            val values = mutableListOf<Int>()
//
//            repeat(nodeSize) {
//                val node = queue.poll()
//
//                values.add(node.`val`)
//
//                node.left?.let { queue.offer(it) }
//                node.right?.let { queue.offer(it) }
//            }
//
//            result.add(values)
//        }
//
//        return result.reversed()
//    }
}