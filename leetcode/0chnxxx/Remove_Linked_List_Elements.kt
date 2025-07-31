/**
 * Given the head of a linked list and an integer val, remove all the nodes of the linked list that has Node.val == val, and return the new head.
 *
 * Constraints:
 * The number of nodes in the list is in the range [0, 10^4].
 * 1 <= Node.val <= 50
 * 0 <= val <= 50
 */

fun main() {
    val head = ListNode(1, ListNode(2, ListNode(6, ListNode(3, ListNode(4, ListNode(5, ListNode(6)))))))
    val `val` = 6

    var result = Solution().removeElements(head, `val`)

    while (result != null) {
        println(result.`val`)
        result = result.next
    }
}

class ListNode(
    var `val`: Int,
    var next: ListNode? = null
)

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun removeElements(head: ListNode?, `val`: Int): ListNode? {
        var node = head
        val dummyHead = ListNode(0)
        var dummy = dummyHead

        while (node != null) {
            if (node.`val` != `val`) {
                dummy.next = node
                dummy = dummy.next!!
            }

            node = node.next
        }

        dummy.next = null

        return dummyHead.next
    }
}