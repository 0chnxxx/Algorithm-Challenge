import java.util.LinkedList

/**
 * Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).
 *
 * Constraints:
 * The number of nodes in the tree is in the range [0, 2000].
 * -1000 <= Node.val <= 1000
 */

fun main() {
    val root = TreeNode(3, TreeNode(9), TreeNode(20, TreeNode(15), TreeNode(7)))

    val solution = Solution().levelOrder(root)

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
    // LinkedList<List<TreeNode>> -> LinkedList<TreeNode> 로 변경하여 공간 최적화
    // queue는 트리의 차수만큼 반복, nodeSize를 통해 내부적으로 반복하며 같은 차수의 노드들에 대한 처리
    fun levelOrder(root: TreeNode?): List<List<Int>> {
        if (root == null) return emptyList()

        val result = mutableListOf<List<Int>>()
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

            result.add(values)
        }

        return result
    }

//    // 시간 복잡도 : O(N)
//    // 공간 복잡도 : O(N)
//    fun levelOrder(root: TreeNode?): List<List<Int>> {
//        if (root == null) return emptyList()
//
//        val result = mutableListOf<List<Int>>()
//        val queue = LinkedList<List<TreeNode>>()
//
//        queue.offer(listOf(root!!))
//
//        while (queue.isNotEmpty()) {
//            val nodes = queue.poll()
//            val values = mutableListOf<Int>()
//            val next = mutableListOf<TreeNode>()
//
//            for (node in nodes) {
//                values.add(node.`val`)
//
//                node.left?.let { next.add(it) }
//                node.right?.let { next.add(it) }
//            }
//
//            if (values.isNotEmpty()) {
//                result.add(values)
//            }
//
//            if (next.isNotEmpty()) {
//                queue.offer(next)
//            }
//        }
//
//        return result
//    }
}