/**
 * Given the root of a binary tree, construct a 0-indexed m x n string matrix res that represents a formatted layout of the tree. The formatted layout matrix should be constructed using the following rules:
 *
 * The height of the tree is height and the number of rows m should be equal to height + 1.
 * The number of columns n should be equal to 2height+1 - 1.
 * Place the root node in the middle of the top row (more formally, at location res[0][(n-1)/2]).
 * For each node that has been placed in the matrix at position res[r][c], place its left child at res[r+1][c-2height-r-1] and its right child at res[r+1][c+2height-r-1].
 * Continue this process until all the nodes in the tree have been placed.
 * Any empty cells should contain the empty string "".
 * Return the constructed matrix res.
 *
 * Constraints:
 * The number of nodes in the tree is in the range [1, 210].
 * -99 <= Node.val <= 99
 * The depth of the tree will be in the range [1, 10].
 */

fun main() {
    val root = TreeNode(1)

    root.left = TreeNode(2)
    root.right = TreeNode(3)
    root.left!!.right = TreeNode(4)

    val result = Solution().printTree(root)

    for (row in result) {
        println(row)
    }
}

class TreeNode(
    var `val`: Int
) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class Solution {
    fun printTree(root: TreeNode?): List<List<String>> {
        val height = getHeight(root)
        val rows = height + 1
        val cols = (1 shl (height + 1)) - 1

        val res = Array(rows) { Array(cols) { "" } }

        fill(res, root, 0, (cols - 1) / 2, height)

        return res.map { it.toList() }
    }

    private fun getHeight(node: TreeNode?): Int {
        if (node == null) return -1
        return 1 + maxOf(getHeight(node.left), getHeight(node.right))
    }

    private fun fill(
        res: Array<Array<String>>,
        node: TreeNode?,
        row: Int,
        col: Int,
        height: Int
    ) {
        if (node == null) return

        res[row][col] = node.`val`.toString()

        val offset = 1 shl (height - row - 1)

        if (node.left != null) {
            fill(res, node.left, row + 1, col - offset, height)
        }
        if (node.right != null) {
            fill(res, node.right, row + 1, col + offset, height)
        }
    }
}
