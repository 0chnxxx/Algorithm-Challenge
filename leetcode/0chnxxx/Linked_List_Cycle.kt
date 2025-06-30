/**
 * Given head, the head of a linked list, determine if the linked list has a cycle in it.
 * There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer.
 * Internally, pos is used to denote the index of the node that tail's next pointer is connected to.
 * Note that pos is not passed as a parameter.
 * Return true if there is a cycle in the linked list.
 * Otherwise, return false.
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

    val result = Solution().hasCycle(head)

    println(result)
}

class ListNode(
    val `val`: Int,
    var next: ListNode? = null
)

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun hasCycle(head: ListNode?): Boolean {
        var slow = head
        var fast = head

        // slow는 한 번에 한 칸, fast는 한 번에 두 칸 이동
        // 사이클이 있다면 언젠가 만남
        while (fast != null && fast.next != null) {
            slow = slow?.next
            fast = fast.next?.next

            if (slow == fast) {
                return true
            }
        }

        return false
    }

//    // 시간 복잡도 : O(N)
//    // 공간 복잡도 : O(N)
//    fun hasCycle(head: ListNode?): Boolean {
//        val nodeSet = mutableSetOf<ListNode?>()
//        var current = head
//        var result = false
//
//        while (current != null) {
//            if (nodeSet.contains(current)) {
//                result = true
//                break
//            }
//
//            nodeSet.add(current)
//            current = current.next
//        }
//
//        return result
//    }
}
