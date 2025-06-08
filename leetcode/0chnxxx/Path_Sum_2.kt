/**
 * Given the root of a binary tree and an integer targetSum, return all root-to-leaf paths where the sum of the node values in the path equals targetSum.
 * Each path should be returned as a list of the node values, not node references.
 * A root-to-leaf path is a path starting from the root and ending at any leaf node.
 * A leaf is a node with no children.
 *
 * Constraints:
 * The number of nodes in the tree is in the range [0, 5000].
 * -1000 <= Node.val <= 1000
 * -1000 <= targetSum <= 1000
 */

fun main() {
//    val root = TreeNode(
//        5,
//        TreeNode(
//            4,
//            TreeNode(
//                11,
//                TreeNode(
//                    7
//                ),
//                TreeNode(
//                    2
//                )
//            )
//        ),
//        TreeNode(
//            8,
//            TreeNode(
//                13
//            ),
//            TreeNode(
//                4,
//                TreeNode(
//                    5
//                ),
//                TreeNode(
//                    1
//                )
//            )
//        )
//    )
//    val targetSum = 22
    val root = TreeNode(1, TreeNode(2))
    val targetSum = 1

    val solution = Solution().pathSum(root, targetSum)

    println(solution.joinToString(", "))
}

class TreeNode(
    var `val`: Int,
    var left: TreeNode? = null,
    var right: TreeNode? = null
)

class Solution {
    // 시간 복잡도 : O(N^2)
    // 공간 복잡도 : O(N^2)
    // sum()을 제거하고 인자로 누적합을 받아서 sum() 에 걸리는 시간을 최적화
    fun pathSum(root: TreeNode?, targetSum: Int): List<List<Int>> {
        val result = mutableListOf<List<Int>>()

        fun recursive(node: TreeNode?, nodes: MutableList<Int>, currentSum: Int) {
            if (node == null) return

            nodes.add(node.`val`)

            val newSum = currentSum + node.`val`

            if (node.left == null && node.right == null && newSum == targetSum) {
                result.add(nodes.toList())
            } else {
                recursive(node.left, nodes, newSum)
                recursive(node.right, nodes, newSum)
            }

            nodes.removeAt(nodes.size - 1)
        }

        recursive(root, mutableListOf(), 0)

        return result
    }

//    // 시간 복잡도 : O(N^2)
//    // 공간 복잡도 : O(N^2)
//    fun pathSum(root: TreeNode?, targetSum: Int): List<List<Int>> {
//        if (root == null) return emptyList()
//
//        val result = mutableListOf<List<Int>>()
//
//        fun recursive(node: TreeNode?, nodes: MutableList<Int>) {
//            if (node == null) return
//
//            if (node.left == null && node.right == null && nodes.sum() == targetSum) {
//                result.add(nodes)
//            }
//
//            node.left?.let {
//                val currentNodes = nodes.toMutableList()
//
//                currentNodes.add(it.`val`)
//
//                recursive(it, currentNodes)
//            }
//            node.right?.let {
//                val currentNodes = nodes.toMutableList()
//
//                currentNodes.add(it.`val`)
//
//                recursive(it, currentNodes)
//            }
//        }
//
//        recursive(root, mutableListOf(root.`val`))
//
//        return result
//    }
}
