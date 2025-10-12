/**
 * Given the head of a singly linked list, group all the nodes with odd indices together followed by the nodes with even indices, and return the reordered list.
 * The first node is considered odd, and the second node is even, and so on.
 * Note that the relative order inside both the even and odd groups should remain as it was in the input.
 * You must solve the problem in O(1) extra space complexity and O(n) time complexity.
 *
 * Constraints:
 * The number of nodes in the linked list is in the range [0, 10^4].
 * -10^6 <= Node.val <= 10^6
 */

fun main() {
    val head = ListNode(1, ListNode(2, ListNode(3, ListNode(4, ListNode(5)))))

    val result = Solution().oddEvenList(head)

    println(result)
}

class ListNode(
    var `val`: Int,
    var next: ListNode? = null
)

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun oddEvenList(head: ListNode?): ListNode? {
        if (head == null || head.next == null) return head

        var odd = head
        var even = head.next
        val evenHead = even

        while (even != null && even.next != null) {
            odd?.next = even.next
            odd = odd?.next

            even.next = odd?.next
            even = even.next
        }

        odd?.next = evenHead

        return head
    }
}
