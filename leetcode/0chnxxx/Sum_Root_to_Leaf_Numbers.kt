/**
 * You are given the root of a binary tree containing digits from 0 to 9 only.
 * Each root-to-leaf path in the tree represents a number.
 *
 * For example, the root-to-leaf path 1 -> 2 -> 3 represents the number 123.
 * Return the total sum of all root-to-leaf numbers.
 * Test cases are generated so that the answer will fit in a 32-bit integer.
 *
 * A leaf node is a node with no children.
 *
 * Constraints:
 * The number of nodes in the tree is in the range [1, 1000].
 * 0 <= Node.val <= 9
 * The depth of the tree will not exceed 10.
 */

fun main() {
    val root = TreeNode(4, TreeNode(9, TreeNode(5), TreeNode(1)), TreeNode(0))

    val solution = Solution().sumNumbers(root)

    println(solution)
}

class TreeNode(
    var `val`: Int,
    var left: TreeNode? = null,
    var right: TreeNode? = null,
)

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(N)
    fun sumNumbers(root: TreeNode?): Int {
        fun dfs(node: TreeNode?, current: Int): Int {
            if (node == null) return 0

            val newValue = current * 10 + node.`val`

            if (node.left == null && node.right == null) {
                return newValue
            }

            return dfs(node.left, newValue) + dfs(node.right, newValue)
        }

        return dfs(root, 0)
    }

//    // 시간 복잡도 : O(Node * Distance)
//    // 공간 복잡도 : O(Node * Distance)
//    fun sumNumbers(root: TreeNode?): Int {
//        val result = mutableListOf<List<Int>>()
//
//        fun dfs(node: TreeNode?, path: MutableList<Int>) {
//            if (node == null) return
//
//            if (node.left == null && node.right == null) {
//                result.add(path)
//                return
//            }
//
//            node.left?.let {
//                val newPath = path.toMutableList()
//                newPath.add(it.`val`)
//                dfs(it, newPath)
//            }
//            node.right?.let {
//                val newPath = path.toMutableList()
//                newPath.add(it.`val`)
//                dfs(it, newPath)
//            }
//        }
//
//        dfs(root, mutableListOf(root!!.`val`))
//
//        return result.sumOf { it.joinToString("").toInt() }
//    }
}