/**
 * Given the roots of two binary trees p and q, write a function to check if they are the same or not.
 * Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.
 *
 * Constraints:
 * The number of nodes in both trees is in the range [0, 100].
 * -10^4 <= Node.val <= 10^4
 */

fun main() {
    val p = TreeNode(1, TreeNode(2), TreeNode(3))
    val q = TreeNode(1, TreeNode(2), TreeNode(3))

    val solution = Solution().isSameTree(p, q)

    println(solution)
}

class TreeNode(
    var `val`: Int,
    var left: TreeNode? = null,
    var right: TreeNode? = null
)

class Solution {
    // 시간 복잡도 : O(N) = 노드의 갯수
    // 공간 복잡도 : O(H) = 트리의 높이
    fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
        var result = true

        fun check(first: TreeNode?, second: TreeNode?) {
            if (first == null && second == null) return

            check(first?.left, second?.left)

            if (first?.`val` != second?.`val`) {
                result = false
                return
            }

            check(first?.right, second?.right)
        }

        check(p, q)

        return result
    }
}