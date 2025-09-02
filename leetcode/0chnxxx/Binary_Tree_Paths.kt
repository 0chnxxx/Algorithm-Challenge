/**
 * Given the root of a binary tree, return all root-to-leaf paths in any order.
 * A leaf is a node with no children.
 *
 * Constraints:
 * The number of nodes in the tree is in the range [1, 100].
 * -100 <= Node.val <= 100
 */

fun main() {
    val root = TreeNode(1, TreeNode(2, null, TreeNode(5)), TreeNode(3))

    val result = Solution().binaryTreePaths(root)

    println(result.joinToString(", "))
}

class TreeNode(
    var `val`: Int,
    var left: TreeNode? = null,
    var right: TreeNode? = null
)

class Solution {
    // 시간 복잡도 : O(N * H)
    // 공간 복잡도 : O(H)
    fun binaryTreePaths(root: TreeNode?): List<String> {
        val paths = mutableListOf<String>()

        if (root == null) return paths

        fun traversal(node: TreeNode, path: MutableList<Int>) {
            path.add(node.`val`)

            if (node.left == null && node.right == null) {
                paths.add(path.joinToString("->"))
            } else {
                node.left?.let { traversal(it, path) }
                node.right?.let { traversal(it, path) }
            }

            path.removeAt(path.size - 1)
        }

        traversal(root, mutableListOf())

        return paths
    }

//    // 시간 복잡도 : O(N * H)
//    // 공간 복잡도 : O(N * H)
//    fun binaryTreePaths(root: TreeNode?): List<String> {
//        if (root == null) return emptyList()
//
//        val paths = mutableListOf<String>()
//
//        fun traversal(node: TreeNode?, list: List<Int>) {
//            if (node?.left == null && node?.right == null) {
//                paths.add(list.joinToString("->"))
//                return
//            }
//
//            val left = mutableListOf<Int>()
//            val right = mutableListOf<Int>()
//
//            left.addAll(list)
//            right.addAll(list)
//
//            node.left?.let {
//                left.add(it.`val`)
//                traversal(it, left)
//            }
//
//            node.right?.let {
//                right.add(it.`val`)
//                traversal(it, right)
//            }
//        }
//
//        traversal(root, listOf(root.`val`))
//
//        return paths
//    }
}