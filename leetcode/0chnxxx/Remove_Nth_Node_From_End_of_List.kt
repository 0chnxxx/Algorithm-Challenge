/**
 * Given the head of a linked list, remove the nth node from the end of the list and return its head.
 *
 * The number of nodes in the list is sz.
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 */

fun main() {
    val head = ListNode(1, ListNode(2, ListNode(3, ListNode(4, ListNode(5, null)))))
//    val head = ListNode(1, ListNode(2, null))
//    val head = ListNode(1, null)
    val n = 2

    val solution = Solution().removeNthFromEnd(head, n)

    val result = mutableListOf<Int>()
    var current: ListNode? = solution

    while (current != null) {
        result.add(current.`val`)
        current = current.next
    }

    println(result)
}

class ListNode(
    var `val`: Int,
    var next: ListNode? = null
)

class Solution {
    // 시간 복잡도 : O(N), 공간 복잡도 : O(1) 풀이
    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        // 첫번째 노드가 삭제되는 경우를 위해 dummy로 head를 감쌈
        val dummy = ListNode(0, head)
        var first: ListNode? = dummy
        var second: ListNode? = dummy

        // first를 먼저 n + 1칸 이동
        for (i in 0..n) {
            first = first?.next
        }

        // first를 null까지 이동시킴으로써
        // second를 뒤에서 n만큼 떨어진 삭제할 위치 이전에 정확하게 위치시킴
        while (first != null) {
            first = first.next
            second = second?.next
        }

        // 삭제할 위치 이전 노드의 next를 그 다음 노드로 변경함으로써 노드 삭제
        second?.next = second?.next?.next

        // dummy를 벗겨낸 후 반환
        return dummy.next
    }

//    // 시간 복잡도: O(N), 공간 복잡도 : O(N) 풀이
//    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
//        val stack = Stack<ListNode>()
//        var node = head
//
//        while (node != null) {
//            stack.push(node)
//            node = node.next
//        }
//
//        var count = 1
//        var nextNode: ListNode? = null
//        var result = head
//
//        while (stack.isNotEmpty()) {
//            val current = stack.pop()
//
//            if (count != n) {
//                count++
//                nextNode = current
//                continue
//            }
//
//            if (stack.isNotEmpty()) {
//                val prevNode = stack.peek()
//                prevNode.next = nextNode
//                break
//            } else {
//                result = nextNode
//                break
//            }
//        }
//
//        return result
//    }
}
