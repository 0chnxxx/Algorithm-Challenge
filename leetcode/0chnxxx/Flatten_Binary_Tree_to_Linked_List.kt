/**
 * Given the root of a binary tree, flatten the tree into a "linked list":
 * The "linked list" should use the same TreeNode class where the right child pointer points to the next node in the list and the left child pointer is always null.
 * The "linked list" should be in the same order as a pre-order traversal of the binary tree.
 *
 * Constraints:
 * The number of nodes in the tree is in the range [0, 2000].
 * -100 <= Node.val <= 100
 *
 * Follow up: Can you flatten the tree in-place (with O(1) extra space)?
 */

fun main() {
    val root = TreeNode(1, TreeNode(2, TreeNode(3), TreeNode(4)), TreeNode(5, null, TreeNode(6)))

    Solution().flatten(root)

    val result = mutableListOf<Int?>()
    var current: TreeNode? = root

    while (current != null) {
        result.add(current.`val`)
        result.add(null)
        current = current.right
    }

    println(result)
}

class TreeNode(
    val `val`: Int,
    var left: TreeNode? = null,
    var right: TreeNode? = null
)

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(N)
    private var prev: TreeNode? = null

    // 재귀를 통해 pre-order traversal의 역순으로 right를 재정렬해가는 방식
    fun flatten(root: TreeNode?): Unit {
        if (root == null) return

        flatten(root.right)
        flatten(root.left)

        root.right = prev
        root.left = null
        prev = root
    }
}