/**
 * You are given the head of a singly linked-list. The list can be represented as:
 *
 * L0 → L1 → … → Ln - 1 → Ln
 * Reorder the list to be on the following form:
 *
 * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
 * You may not modify the values in the list's nodes.
 * Only nodes themselves may be changed.
 *
 * Constraints:
 * The number of nodes in the list is in the range [1, 5 * 10^4].
 * 1 <= Node.val <= 1000
 */

fun main() {
    val node1 = ListNode(1)
    val node2 = ListNode(2)
    val node3 = ListNode(3)
    val node4 = ListNode(4)

    node1.next = node2
    node2.next = node3
    node3.next = node4

    var head: ListNode? = node1

    Solution().reorderList(head)

    while (head != null) {
        println(head.`val`)
        head = head.next
    }
}

class ListNode(
    var `val`: Int,
    var next: ListNode? = null
)

class Solution {
    fun reorderList(head: ListNode?): Unit {
        if (head?.next == null) return

        // slow는 한 칸씩 이동, fast는 두 칸씩 이동
        // 중간 노드를 찾음
        var slow = head
        var fast = head

        while (fast?.next != null && fast.next?.next != null) {
            slow = slow?.next
            fast = fast.next?.next
        }

        // 중간 노드를 기준으로 앞과 뒤를 나눔
        var prev: ListNode? = null
        var current = slow?.next

        // 앞과 뒤의 연결을 끊음
        slow?.next = null

        // 뒷 부분을 역순으로 정렬함
        while (current != null) {
            val next = current.next
            current.next = prev
            prev = current
            current = next
        }

        // 앞과 뒤를 병합함
        var first = head
        var second = prev

        while (second != null) {
            val temp1 = first?.next
            val temp2 = second.next

            first?.next = second
            second.next = temp1
            first = temp1
            second = temp2
        }
    }
}