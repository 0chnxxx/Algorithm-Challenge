/**
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
 *
 * Constraints:
 * The number of nodes in the list is the range [0, 5000].
 * -5000 <= Node.val <= 5000
 *
 * Follow up:
 * A linked list can be reversed either iteratively or recursively. Could you implement both?
 */

fun main() {
    val head = ListNode(1, ListNode(2, ListNode(3, ListNode(4, ListNode(5)))))

    var result = Solution().reverseList(head)

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
    fun reverseList(head: ListNode?): ListNode? {
        var prev: ListNode? = null
        var curr = head

        while (curr != null) {
            val nextTemp = curr.next

            curr.next = prev
            prev = curr
            curr = nextTemp
        }

        return prev
    }

//    // 시간 복잡도 : O(N)
//    // 공간 복잡도 : O(N)
//    fun reverseList(head: ListNode?): ListNode? {
//        val dummyHead = ListNode(0)
//        var dummy = head
//        val temp = mutableListOf<ListNode>()
//
//        while (true) {
//            if (dummy?.next == null) {
//                dummyHead.next = dummy
//                dummy = dummyHead.next
//                break
//            }
//
//            temp.add(dummy)
//            dummy = dummy.next
//        }
//
//        temp.reverse()
//
//        for (tempNode in temp) {
//            dummy?.next = tempNode
//            dummy = dummy?.next
//        }
//
//        dummy?.next = null
//
//        return dummyHead.next
//    }
}
