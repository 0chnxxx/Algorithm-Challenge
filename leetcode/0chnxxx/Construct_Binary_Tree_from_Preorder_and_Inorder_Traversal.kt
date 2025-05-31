/**
 * Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree and inorder is the inorder traversal of the same tree, construct and return the binary tree.
 *
 * Constraints:
 * 1 <= preorder.length <= 3000
 * inorder.length == preorder.length
 * -3000 <= preorder[i], inorder[i] <= 3000
 * preorder and inorder consist of unique values.
 * Each value of inorder also appears in preorder.
 * preorder is guaranteed to be the preorder traversal of the tree.
 * inorder is guaranteed to be the inorder traversal of the tree.
 */

fun main() {
    val preorder = intArrayOf(3, 9, 20, 15, 7)
    val inorder = intArrayOf(9, 3, 15, 20, 7)

    val solution = Solution().buildTree(preorder, inorder)
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
    // indexOf() -> HashMap을 통해 O(N) 에서 O(1)로 최적화
    // kotlin의 associate는 중간 객체를 계속 생성하기 때문에 성능에 불리하므로 직접 Map 생성
    fun buildTree(preorder: IntArray, inorder: IntArray): TreeNode? {
        var preorderIndex = 0
        val inorderIndexMap = HashMap<Int, Int>(inorder.size)

        for (i in inorder.indices) {
            inorderIndexMap[inorder[i]] = i
        }

        fun build(left: Int, right: Int): TreeNode? {
            if (left > right) return null

            val rootValue = preorder[preorderIndex++]
            val root = TreeNode(rootValue)
            val index = inorderIndexMap[rootValue]!! // O(1)

            root.left = build(left, index - 1)
            root.right = build(index + 1, right)

            return root
        }

        return build(0, inorder.size - 1)
    }

//    // 시간 복잡도 : O(N^2)
//    // 공간 복잡도 : O(N)
//    fun buildTree(preorder: IntArray, inorder: IntArray): TreeNode? {
//        var preorderIndex = 0
//
//        fun build(left: Int, right: Int): TreeNode? {
//            if (left > right) return null
//
//            val root = TreeNode(preorder[preorderIndex++])
//            val index = inorder.indexOf(root.`val`)
//
//            root.left = build(left, index - 1)
//            root.right = build(index + 1, right)
//
//            return root
//        }
//
//        return build(0, inorder.size - 1)
//    }
}
