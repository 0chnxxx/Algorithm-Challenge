import java.util.LinkedList

/**
 * Given the root of a complete binary tree, return the number of the nodes in the tree.
 * According to Wikipedia, every level, except possibly the last, is completely filled in a complete binary tree, and all nodes in the last level are as far left as possible.
 * It can have between 1 and 2h nodes inclusive at the last level h.
 *
 * Design an algorithm that runs in less than O(n) time complexity.
 *
 * Constraints:
 * The number of nodes in the tree is in the range [0, 5 * 10^4].
 * 0 <= Node.val <= 5 * 10^4
 * The tree is guaranteed to be complete.
 */

fun main() {
//    val root = TreeNode(1, TreeNode(2, TreeNode(4), TreeNode(5)), TreeNode(3, TreeNode(6)))
    val root = TreeNode(1, TreeNode(2, TreeNode(4)), TreeNode(3))

    val result = Solution().countNodes(root)

    println(result)
}

class TreeNode(
    var `val`: Int,
    var left: TreeNode? = null,
    var right: TreeNode? = null
)

class Solution {
    // 시간 복잡도 : O((log N)^2)
    // 공간 복잡도 : O(log N)
    fun countNodes(root: TreeNode?): Int {
        if (root == null) return 0

        fun calculcate(node: TreeNode?): Int {
            var height = 0
            var current = node

            while (current != null) {
                height++
                current = current.left
            }

            return height
        }

        val leftHeight = calculcate(root.left)
        val rightHeight = calculcate(root.right)

        return if (leftHeight == rightHeight) { // 왼쪽 서브트리가 꽉 찬 경우
            (1 shl leftHeight) + countNodes(root.right) // 오른쪽 서브트리만 따로 재귀로 계산해서 더함
        } else { // 오른쪽 서브트리가 꽉 찬 경우
            (1 shl rightHeight) + countNodes(root.left) // 왼쪾 서브트리만 따로 재귀로 계산해서 더함
        }

        // (1 shl height) == 2^height 이기 때문에 포화 이진 트리 공식인 2^height - 1에서 루트 노드 1개를 포함한 결과
    }

//    // 시간 복잡도 : O(N)
//    // 공간 복잡도 : O(N)
//    fun countNodes(root: TreeNode?): Int {
//        if (root == null) return 0
//
//        var count = 0
//        val queue = LinkedList<List<TreeNode>>()
//
//        queue.add(listOf(root))
//
//        while (queue.isNotEmpty()) {
//            val nodes = queue.poll()
//
//            if (nodes.isEmpty()) {
//                break
//            } else {
//                count += nodes.size
//            }
//
//            val list = mutableListOf<TreeNode>()
//
//            for (node in nodes) {
//                node.left?.let { it -> list.add(it) }
//                node.right?.let { it -> list.add(it) }
//            }
//
//            queue.add(list)
//        }
//
//        return count
//    }
}
