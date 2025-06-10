import java.util.LinkedList

/**
 * You are given a perfect binary tree where all leaves are on the same level, and every parent has two children.
 * The binary tree has the following definition:
 * Populate each next pointer to point to its next right node.
 * If there is no next right node, the next pointer should be set to NULL.
 * Initially, all next pointers are set to NULL.
 *
 * Constraints:
 * The number of nodes in the tree is in the range [0, 2^12 - 1].
 * -1000 <= Node.val <= 1000
 *
 *
 * Follow-up:
 * You may only use constant extra space.
 * The recursive approach is fine.
 * You may assume implicit stack space does not count as extra space for this problem.
 */

fun main() {
    val root = Node(1, Node(2, Node(4), Node(5)), Node(3, Node(6), Node(7)))

    var solution = Solution().connect(root)

    while (solution != null) {
        var currentNode: Node? = solution

        while (currentNode != null) {
            print("${currentNode.`val`} ")

            currentNode = currentNode.next
        }

        println("#")

        solution = solution.left
    }
}

class Node(
    var `val`: Int,
    var left: Node? = null,
    var right: Node? = null,
    var next: Node? = null
)

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun connect(root: Node?): Node? {
        var leftMost = root

        while (leftMost?.left != null) {
            var head = leftMost

            while (head != null) {
                // 같은 부모 내 left -> right 연결
                head.left?.next = head.right

                // 서로 다른 부모 사이 right -> next.left 연결
                if (head.next != null) {
                    head.right?.next = head.next?.left
                }

                head = head.next
            }

            // 가장 왼쪽에 해당하는 노드들로만 이동
            leftMost = leftMost.left
        }

        return root
    }

//    // 시간 복잡도 : O(N)
//    // 공간 복잡도 : O(N)
//    fun connect(root: Node?): Node? {
//        if (root == null) return null
//
//        val queue = LinkedList<Node>()
//
//        queue.offer(root)
//
//        while (queue.isNotEmpty()) {
//            val size = queue.size
//
//            // 해당 level에 node 수 만큼 반복
//            for (i in 0 until size) {
//                val current = queue.poll()
//
//                // 마지막 이전 것들만 next 연결
//                if (i < size - 1) {
//                    current.next = queue.peek()
//                }
//
//                // 다음 level의 노드들 수집
//                current.left?.let { queue.offer(it) }
//                current.right?.let { queue.offer(it) }
//            }
//        }
//
//        return root
//    }
}