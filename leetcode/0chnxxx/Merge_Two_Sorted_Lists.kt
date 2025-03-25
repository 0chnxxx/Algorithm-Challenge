/**
 * You are given the heads of two sorted linked lists list1 and list2.
 *
 * Merge the two lists into one sorted list. The list should be made by splicing together the nodes of the first two lists.
 *
 * Return the head of the merged linked list.
 *
 * The number of nodes in both lists is in the range [0, 50].
 * -100 <= Node.val <= 100
 * Both list1 and list2 are sorted in non-decreasing order.
 */

fun main() {
    val list1 = ListNode(1, ListNode(2, ListNode(4, null)))
    val list2 = ListNode(1, ListNode(3, ListNode(4, null)))

    val solution = Solution().mergeTwoLists(list1, list2)

    println(solution)
}

class ListNode(
    var `val`: Int,
    var next: ListNode? = null
)

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    // 새로운 노드를 만들지 않음으로써 공간 복잡도를 개선함
    fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
        val result = ListNode(0, null)
        var current = result

        var a = list1
        var b = list2

        while (a != null && b != null) {
            if (a.`val` < b.`val`) {
                current.next = a
                a = a.next
            } else {
                current.next = b
                b = b.next
            }

            current = current.next!!
        }

        current.next = a ?: b

        return result.next
    }

//    // 시간 복잡도 : O(N)
//    // 공간 복잡도 : O(N)
//    fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
//        val result = ListNode(0, null)
//        var current = result
//
//        var a = list1
//        var b = list2
//
//        while (a != null || b != null) {
//            if (a == null) {
//                current.next = ListNode(b!!.`val`, null)
//                current = current.next!!
//                b = b.next
//            } else if (b == null) {
//                current.next = ListNode(a!!.`val`, null)
//                current = current.next!!
//                a = a.next
//            } else if (a.`val` < b.`val`) {
//                current.next = ListNode(a!!.`val`, null)
//                current = current.next!!
//                a = a.next
//            } else {
//                current.next = ListNode(b!!.`val`, null)
//                current = current.next!!
//                b = b.next
//            }
//        }
//
//        return result.next
//    }
}
