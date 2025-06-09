import kotlin.math.abs

/**
 * Given a binary tree, determine if it is height-balanced.
 *
 * Constraints:
 * The number of nodes in the tree is in the range [0, 5000].
 * -10^4 <= Node.val <= 10^4
 */

fun main() {
    val root = TreeNode(3, TreeNode(9), TreeNode(20, TreeNode(15), TreeNode(7)))

    val solution = Solution().isBalanced(root)

    println(solution)
}

class TreeNode(
    var `val`: Int = 0,
    var left: TreeNode? = null,
    var right: TreeNode? = null
)

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(H)
    fun isBalanced(root: TreeNode?): Boolean {
        fun check(node: TreeNode?): Int {
            // node가 null이면 0을 반환
            if (node == null) return 0

            // 왼쪽 서브트리를 재귀로 태움
            val left = check(node.left)

            // 왼쪽 서브트리가 부적절한 트리면 -1을 반환
            if (left == -1) return -1

            // 오른쪽 서브트리를 재귀로 태움
            val right = check(node.right)

            // 오른쪽 서브트리가 부적절한 트리면 -1을 반환
            if (right == -1) return -1

            // 왼쪽 서브트리와 오른쪽 서브트리의 차수가 다르다면 부적절한 트리로 -1을 반환
            if (abs(left - right) > 1) return -1

            // 여기까지 오면 정상적인 균형 트리라고 판단
            // null일 경우 0을 반환하기 때문에 1을 더함으로써 차수를 카운트해서 반환
            return maxOf(left, right) + 1
        }

        // root를 기준으로 재귀 탐색
        // -1 은 부적절한 트리에 대한 매직넘버
        return check(root) != -1
    }
}
