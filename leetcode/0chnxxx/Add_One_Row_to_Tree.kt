/**
 * Given the root of a binary tree and two integers val and depth, add a row of nodes with value val at the given depth depth.
 * Note that the root node is at depth 1.
 *
 * The adding rule is:
 * Given the integer depth, for each not null tree node cur at the depth depth - 1, create two tree nodes with value val as cur's left subtree root and right subtree root.
 * cur's original left subtree should be the left subtree of the new left subtree root.
 * cur's original right subtree should be the right subtree of the new right subtree root.
 * If depth == 1 that means there is no depth depth - 1 at all, then create a tree node with value val as the new root of the whole original tree, and the original tree is the new root's left subtree.
 *
 * Constraints:
 * The number of nodes in the tree is in the range [1, 10^4].
 * The depth of the tree is in the range [1, 10^4].
 * -100 <= Node.val <= 100
 * -10^5 <= val <= 10^5
 * 1 <= depth <= the depth of tree + 1
 */

class TreeNode(
    var `val`: Int
) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(N)
    fun addOneRow(root: TreeNode?, `val`: Int, depth: Int): TreeNode? {
        if (depth == 1) {
            val newRoot = TreeNode(`val`)

            newRoot.left = root

            return newRoot
        }

        fun dfs(node: TreeNode?, currentDepth: Int) {
            if (node == null) return

            if (currentDepth == depth - 1) {
                val oldLeft = node.left
                val oldRight = node.right

                node.left = TreeNode(`val`)
                node.left?.left = oldLeft

                node.right = TreeNode(`val`)
                node.right?.right = oldRight

                return
            }

            dfs(node.left, currentDepth + 1)
            dfs(node.right, currentDepth + 1)
        }

        dfs(root, 1)

        return root
    }
}
