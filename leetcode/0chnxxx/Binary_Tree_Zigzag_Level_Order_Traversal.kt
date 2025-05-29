import java.util.LinkedList

/**
 * Given the root of a binary tree, return the zigzag level order traversal of its nodes' values.
 * (i.e., from left to right, then right to left for the next level and alternate between).
 *
 * Constraints:
 * The number of nodes in the tree is in the range [0, 2000].
 * -100 <= Node.val <= 100
 */

fun main() {
    val root = TreeNode(3, TreeNode(9), TreeNode(20, TreeNode(15), TreeNode(7)))

    val solution = Solution().zigzagLevelOrder(root)

    println(solution.map{ it.joinToString(", ") })
}

class TreeNode(
    var `val`: Int,
    var left: TreeNode? = null,
    var right: TreeNode? = null
)

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(N)
    // reversed() 로 인해 매번 리스트가 복사되는걸 최적화
    fun zigzagLevelOrder(root: TreeNode?): List<List<Int>> {
        if (root == null) return emptyList()

        val result = mutableListOf<List<Int>>()
        val queue = LinkedList<TreeNode>()
        var isReverse = false

        queue.offer(root)

        while (queue.isNotEmpty()) {
            val nodeSize = queue.size
            val values = LinkedList<Int>()

            repeat(nodeSize) {
                val node = queue.poll()

                if (!isReverse) {
                    values.addLast(node.`val`)
                } else {
                    values.addFirst(node.`val`)
                }

                node.left?.let { queue.offer(it) }
                node.right?.let { queue.offer(it) }
            }

            result.add(values)
            isReverse = !isReverse
        }

        return result
    }

//    // 시간 복잡도 : O(N)
//    // 공간 복잡도 : O(N)
//    fun zigzagLevelOrder(root: TreeNode?): List<List<Int>> {
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
//            if (result.size % 2 == 0) {
//                result.add(values)
//            } else {
//                result.add(values.reversed())
//            }
//        }
//
//        return result
//    }
}
