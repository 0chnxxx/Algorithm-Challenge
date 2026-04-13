/**
 * Given the root of a binary tree, return all duplicate subtrees.
 * For each kind of duplicate subtrees, you only need to return the root node of any one of them.
 * Two trees are duplicate if they have the same structure with the same node values.
 *
 * Constraints:
 * The number of the nodes in the tree will be in the range [1, 5000]
 * -200 <= Node.val <= 200
 */

fun main() {
    val root = TreeNode(1)
    val node1 = TreeNode(2)
    val node2 = TreeNode(3)
    val node3 = TreeNode(4)
    val node4 = TreeNode(2)
    val node5 = TreeNode(4)
    val node6 = TreeNode(4)

    node1.left = node3

    node2.left = node6
    node2.right = node3

    node4.left = node6
    node2.right = node5

    val result = Solution().findDuplicateSubtrees(root)

    println(result)
}

class TreeNode(
    var `val`: Int
) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class Solution {
    // 시간 복잡도 : O(N^2)
    // 공간 복잡도 : O(N^2)
    fun findDuplicateSubtrees(root: TreeNode?): List<TreeNode?> {
        val map = HashMap<String, Int>()
        val result = mutableListOf<TreeNode?>()

        fun dfs(node: TreeNode?): String {
            if (node == null) return "#"

            val left = dfs(node.left)
            val right = dfs(node.right)

            val serial = "${node.`val`},$left,$right"

            val count = map.getOrDefault(serial, 0)

            if (count == 1) {
                result.add(node)
            }

            map[serial] = count + 1

            return serial
        }

        dfs(root)
        return result
    }
}
