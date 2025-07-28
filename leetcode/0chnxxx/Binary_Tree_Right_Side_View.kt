import java.util.LinkedList

/**
 * Given the root of a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.
 *
 * Constraints:
 * The number of nodes in the tree is in the range [0, 100].
 * -100 <= Node.val <= 100
 */

fun main() {
    val root = TreeNode(1, TreeNode(2, null, TreeNode(5)), TreeNode(3, null, TreeNode(4)))

    val result = Solution().rightSideView(root)

    println(result)
}

class TreeNode(
    val `val`: Int,
    var left: TreeNode? = null,
    var right: TreeNode? = null
)

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(W)
    fun rightSideView(root: TreeNode?): List<Int> {
        val result = mutableListOf<Int>()

        if (root == null) return result

        // BFS로 차수별로 노드들을 탐색
        val queue = LinkedList<TreeNode>()

        queue.offer(root)

        while (queue.isNotEmpty()) {
            val size = queue.size

            // 해당 차수를 모두 탐색
            for (i in 0 until size) {
                val node = queue.poll()

                // 맨 마지막 노드면 result에 추가
                if (i == size - 1) {
                    result.add(node.`val`)
                }

                // 다음 차수의 노드를 큐에 등록
                node.left?.let { queue.offer(it) }
                node.right?.let { queue.offer(it) }
            }
        }

        return result
    }
}
