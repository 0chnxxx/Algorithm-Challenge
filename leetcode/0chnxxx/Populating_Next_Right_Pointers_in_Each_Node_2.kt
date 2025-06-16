import java.util.LinkedList

/**
 * populate each next pointer to point to its next right node.
 * if there is no next right node, the next pointer should be set to null.
 * initially, all next pointers are set to null.
 *
 * constraints:
 * the number of nodes in the tree is in the range [0, 6000].
 * -100 <= node.val <= 100
 *
 *
 * follow-up:
 * you may only use constant extra space.
 * the recursive approach is fine.
 * you may assume implicit stack space does not count as extra space for this problem.
 */

fun main() {
//    val root = Node(1, Node(2, Node(4), Node(5)), Node(3, null, Node(7)))
    val root = Node(1, Node(2, Node(4)), Node(3, null, Node(5)))

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
    val `val`: Int,
    var left: Node? = null,
    var right: Node? = null,
    var next: Node? = null
)

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun connect(root: Node?): Node? {
        var start = root

        while (start != null) {
            val dummy = Node(0)
            var prev = dummy
            var current = start

            while (current != null) {
                current.left?.let {
                    prev.next = it
                    prev = it
                }
                current.right?.let {
                    prev.next = it
                    prev = it
                }
                current = current.next
            }

            start = dummy.next
        }

        return root
    }

//    // 시간 복잡도 : O(N)
//    // 공간 복잡도 : O(N)
//    fun connect(root: Node?): Node? {
//        val queue = LinkedList<List<Node?>>()
//
//        queue.offer(mutableListOf(root))
//
//        while (queue.isNotEmpty()) {
//            val nodes = queue.poll()
//            val nextNodes = mutableListOf<Node?>()
//
//            if (nodes.isEmpty()) break
//
//            for (i in 0 until nodes.size) {
//                val node = nodes[i]
//
//                if (i == nodes.size - 1) {
//                    node?.next = null
//                } else {
//                    node?.next = nodes[i + 1]
//                }
//
//                node?.left?.let { nextNodes.add(it) }
//                node?.right?.let { nextNodes.add(it) }
//            }
//
//            queue.offer(nextNodes)
//        }
//
//        return root
//    }
}