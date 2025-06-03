/**
 * Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree and postorder is the postorder traversal of the same tree, construct and return the binary tree.
 *
 * Constraints:
 * 1 <= inorder.length <= 3000
 * postorder.length == inorder.length
 * -3000 <= inorder[i], postorder[i] <= 3000
 * inorder and postorder consist of unique values.
 * Each value of postorder also appears in inorder.
 * inorder is guaranteed to be the inorder traversal of the tree.
 * postorder is guaranteed to be the postorder traversal of the tree.
 */

fun main() {
    val inorder = intArrayOf(9, 3, 15, 20, 7)
    val postorder = intArrayOf(9, 15, 7, 20, 3)

    val solution = Solution().buildTree(inorder, postorder)
    val result = mutableListOf<Int?>()

    fun traversal(node: TreeNode?) {
        if (node == null) return

        result.add(node.`val`)

        traversal(node.left)
        traversal(node.right)
    }

    traversal(solution)
    println(result.joinToString(", "))
}

class TreeNode(
    var `val`: Int,
    var left: TreeNode? = null,
    var right: TreeNode? = null
)

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(N)
    fun buildTree(inorder: IntArray, postorder: IntArray): TreeNode? {
        val inorderIndexMap = HashMap<Int, Int>(inorder.size)

        for (i in inorder.indices) {
            inorderIndexMap[inorder[i]] = i
        }

        var postIndex = postorder.lastIndex

        fun build(left: Int, right: Int): TreeNode? {
            if (left > right) return null

            val rootValue = postorder[postIndex--]
            val root = TreeNode(rootValue)
            val index = inorderIndexMap[rootValue]!!

            // postorder 의 인덱스를 -1 해가면서 트리를 구성하므로 right 서브트리가 우선적으로 처리되어야함
            root.right = build(index + 1, right)
            root.left = build(left, index - 1)

            return root
        }

        return build(0, inorder.lastIndex)
    }
}
