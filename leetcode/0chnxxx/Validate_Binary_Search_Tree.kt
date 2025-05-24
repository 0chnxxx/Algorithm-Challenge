/**
 * Given the root of a binary tree, determine if it is a valid binary search tree (BST).
 *
 * A valid BST is defined as follows:
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 *
 * Constraints:
 * The number of nodes in the tree is in the range [1, 104].
 * -2^31 <= Node.val <= 2^31 - 1
 */

fun main() {
    val root = TreeNode(5, TreeNode(4), TreeNode(6, TreeNode(3), TreeNode(7)))

    val solution = Solution().isValidBST(root)

    println(solution)
}

class TreeNode(
    var `val`: Int = 0,
    var left: TreeNode? = null,
    var right: TreeNode? = null
)

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(N)
    fun isValidBST(root: TreeNode?): Boolean {
        fun isValid(node: TreeNode?, min: Long, max: Long): Boolean {
            // 빈 노드면 true를 리턴
            if (node == null) return true

            // 이진 트리 조건에 맞지 않는다면 false를 리턴
            if (node.`val` <= min || node.`val` >= max) return false

            // 자식 노드들을 확인
            // min, max를 부모 노드를 기준으로 설정하여 이진 트리 조건에 맞는지 확인
            val isLeftValid = isValid(node.left, min, node.`val`.toLong())
            val isRightValid = isValid(node.right, node.`val`.toLong(), max)

            // 두 뎁스 모두 이진 트리 조건에 맞는지 여부 리턴
            return isLeftValid && isRightValid
        }

        return isValid(root, Long.MIN_VALUE, Long.MAX_VALUE)
    }
}
