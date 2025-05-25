/**
 * You are given the root of a binary search tree (BST), where the values of exactly two nodes of the tree were swapped by mistake.
 * Recover the tree without changing its structure.
 *
 * Constraints:
 * The number of nodes in the tree is in the range [2, 1000].
 * -2^31 <= Node.val <= 2^31 - 1
 *
 * Follow up:
 * A solution using O(n) space is pretty straight-forward.
 * Could you devise a constant O(1) space solution?
 */

fun main() {
    val root = TreeNode(1, TreeNode(3, null, TreeNode(2)), null)

    Solution().recoverTree(root)

    fun printInOrder(node: TreeNode?) {
        if (node == null) return

        printInOrder(node.left)
        print("${node.`val`} ")
        printInOrder(node.right)
    }

    printInOrder(root)
}

class TreeNode(
    var `val`: Int = 0,
    var left: TreeNode? = null,
    var right: TreeNode? = null
)

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun recoverTree(root: TreeNode?): Unit {
        // Morris Traversal
        // 기존 재귀 호출이나 스택을 사용해서 중위 순회에서 방문한 노드를 기억하는 방식에서
        // 왼쪽 서브트리의 가장 오른쪽 노드에 현재 노드로 돌아가기 위한 링크를 임시로 연결해서 스택 없이 돌아옴

        // 현재 노드
        var current = root

        // 이전 노드
        var prev: TreeNode? = null

        // 불규칙이 발생한 노드 중 가장 큰 노드와 가장 작은 노드
        var first: TreeNode? = null
        var second: TreeNode? = null

        while (current != null) {
            // 왼쪽 노드가 없는 경우
            if (current.left == null) {
                // 불규칙을 확인
                if (prev != null && prev.`val` > current.`val`) {
                    if (first == null) first = prev
                    second = current
                }

                // 오른쪽 노드로 이동
                prev = current
                current = current.right
            // 왼쪽 노드가 있는 경우
            } else {
                // 왼쪽 서브 트리를 링크 노드로 사용
                var predecessor = current.left

                // 왼쪽 서브 트리에서 가장 낮은 차수의 오른쪽 노드를 찾음
                while (predecessor?.right != null && predecessor.right != current) {
                    predecessor = predecessor.right
                }

                // 링크 노드에 첫번째 방문 시
                if (predecessor?.right == null) {
                    // 현재 노드를 링크
                    predecessor?.right = current
                    current = current.left
                // 링크 노드에 두번째 방문 시
                } else {
                    // 링크를 해제
                    predecessor.right = null

                    // 불규칙 확인
                    if (prev != null && prev.`val` > current.`val`) {
                        if (first == null) first = prev
                        second = current
                    }

                    // 오른쪽 노드로 이동
                    prev = current
                    current = current.right
                }
            }
        }

        // 불규칙 노드의 값을 swap
        val temp = first!!.`val`
        first!!.`val` = second!!.`val`
        second!!.`val` = temp
    }

//    // 시간 복잡도 : O(N) = 노드 갯수
//    // 공간 복잡도 : O(H) = 트리 높이
//    fun recoverTree(root: TreeNode?): Unit {
//        // 불규칙이 발생한 두 노드
//        var first: TreeNode? = null
//        var second: TreeNode? = null
//
//        // 중위 순회 중 이전 노드
//        var prev: TreeNode? = TreeNode(Int.MIN_VALUE)
//
//        fun findAnomal(node: TreeNode?) {
//            if (node == null) return
//
//            // 좌측 노드 탐색
//            findAnomal(node.left)
//
//            // 이전 노드가 더 큰 값인 경우 (중위 순회 불규칙 발생)
//            if (prev != null && prev!!.`val` > node.`val`) {
//                // first가 이미 있는 경우엔 더 큰 값을 유지해야함
//                if (first == null) {
//                    first = prev
//                }
//
//                second = node
//            }
//
//            // 현재 노드를 이전 노드로 사용
//            prev = node
//
//            // 우측 노드 탐색
//            findAnomal(node.right)
//        }
//
//        // 루트에서부터 탐색
//        findAnomal(root)
//
//        // 불규칙을 발견한 두 노드끼리 값을 swap
//        val temp = first!!.`val`
//        first!!.`val` = second!!.`val`
//        second!!.`val` = temp
//    }
}
