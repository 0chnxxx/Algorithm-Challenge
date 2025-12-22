/**
 * Given a root node reference of a BST and a key, delete the node with the given key in the BST.
 * Return the root node reference (possibly updated) of the BST.
 *
 * Basically, the deletion can be divided into two stages:
 * Search for a node to remove.
 * If the node is found, delete the node.
 *
 * Constraints:
 * The number of nodes in the tree is in the range [0, 10^4].
 * -10^5 <= Node.val <= 10^5
 * Each node has a unique value.
 * root is a valid binary search tree.
 * -10^5 <= key <= 10^5
 *
 * Follow up:
 * Could you solve it with time complexity O(height of tree)?
 */

fun main() {
    val root = TreeNode(5)
    val node3 = TreeNode(3)
    val node6 = TreeNode(6)
    val node2 = TreeNode(2)
    val node4 = TreeNode(4)
    val node7 = TreeNode(7)
    node3.left = node2
    node3.right = node4
    node6.right = node7
    root.left = node3
    root.right = node6

    val key = 3

    val result = Solution().deleteNode(root, key)

    println(result)
}

class TreeNode(
    var `val`: Int,
) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class Solution {
    // 시간 복잡도 : O(H)
    // 공간 복잡도 : O(H)
    fun deleteNode(root: TreeNode?, key: Int): TreeNode? {
        if (root == null) return null

        // 이진 트리 성질에 따라 key 를 찾기 위해 재귀로 타고 내려감
        when {
            // key가 더 작은 경우 왼쪽 서브트리로 내려감
            key < root.`val` -> {
                root.left = deleteNode(root.left, key)
                return root
            }
            // key가 더 큰 경우 오른쪽 서브트리로 내려감
            key > root.`val` -> {
                root.right = deleteNode(root.right, key)
                return root
            }
            // key를 찾은 경우
            else -> {
                // 자식이 없으면 해당 노드를 제거하기 위해 null을 반환함
                if (root.left == null && root.right == null) {
                    return null
                }

                // 오른쪽 자식 노드만 있는 경우 오른쪽 노드를 반환함
                if (root.left == null) {
                    return root.right
                }

                // 왼쪽 자식 노드만 있는 경우 왼쪽 노드를 반환함
                if (root.right == null) {
                    return root.left
                }

                // 자식 노드가 둘 다 있는 경우
                // 가장 작은 노드를 찾아냄
                var min = root.right!!

                while (min.left != null) {
                    min = min.left!!
                }

                root.`val` = min.`val`
                root.right = deleteNode(root.right, min.`val`)

                return root
            }
        }
    }
}