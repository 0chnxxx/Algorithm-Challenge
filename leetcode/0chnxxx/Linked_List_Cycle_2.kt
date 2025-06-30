/**
 * Given the head of a linked list, return the node where the cycle begins.
 * If there is no cycle, return null.
 * There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer.
 * Internally, pos is used to denote the index of the node that tail's next pointer is connected to (0-indexed).
 * It is -1 if there is no cycle.
 * Note that pos is not passed as a parameter.
 * Do not modify the linked list.
 *
 * Constraints:
 * The number of the nodes in the list is in the range [0, 10^4].
 * -10^5 <= Node.val <= 10^5
 * pos is -1 or a valid index in the linked-list.
 *
 * Follow up:
 * Can you solve it using O(1) (i.e. constant) memory?
 */

fun main() {
    val node1 = ListNode(3)
    val node2 = ListNode(2)
    val node3 = ListNode(0)
    val node4 = ListNode(-4)

    node1.next = node2
    node2.next = node3
    node3.next = node4
    node4.next = node2

    val head = node1

    val result = Solution().detectCycle(head)

    println(result)
}

class ListNode(
    val `val`: Int,
    var next: ListNode? = null
)

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun detectCycle(head: ListNode?): ListNode? {
        var slow = head
        var fast = head

        while (fast?.next != null) {
            slow = slow?.next
            fast = fast.next?.next

            if (slow == fast) {
                var entry = head

                // cycle의 시작점을 찾기 위해 같이 한 칸씩 이동시킴
                while (entry != slow) {
                    entry = entry?.next
                    slow = slow?.next
                }

                return entry
            }
        }

        return null
    }

//    // 시간 복잡도 : O(N)
//    // 공간 복잡도 : O(N)
//    fun detectCycle(head: ListNode?): ListNode? {
//        var current = head
//        val visited = mutableSetOf<ListNode?>()
//
//        while (current != null) {
//            if (visited.contains(current)) {
//                return current
//            }
//
//            visited.add(current)
//            current = current.next
//        }
//
//        return null
//    }
}
