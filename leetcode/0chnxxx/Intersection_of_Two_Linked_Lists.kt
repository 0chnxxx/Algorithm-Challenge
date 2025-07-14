/**
 * Given the heads of two singly linked-lists headA and headB, return the node at which the two lists intersect. If the two linked lists have no intersection at all, return null.
 *
 * For example, the following two linked lists begin to intersect at node c1:
 * The test cases are generated such that there are no cycles anywhere in the entire linked structure.
 * Note that the linked lists must retain their original structure after the function returns.
 *
 * Custom Judge:
 * The inputs to the judge are given as follows (your program is not given these inputs):
 * intersectVal - The value of the node where the intersection occurs. This is 0 if there is no intersected node.
 * listA - The first linked list.
 * listB - The second linked list.
 * skipA - The number of nodes to skip ahead in listA (starting from the head) to get to the intersected node.
 * skipB - The number of nodes to skip ahead in listB (starting from the head) to get to the intersected node.
 *
 * The judge will then create the linked structure based on these inputs and pass the two heads, headA and headB to your program. If you correctly return the intersected node, then your solution will be accepted.
 *
 * Constraints:
 * The number of nodes of listA is in the m.
 * The number of nodes of listB is in the n.
 * 1 <= m, n <= 3 * 10^4
 * 1 <= Node.val <= 10^5
 * 0 <= skipA <= m
 * 0 <= skipB <= n
 * intersectVal is 0 if listA and listB do not intersect.
 * intersectVal == listA[skipA] == listB[skipB] if listA and listB intersect.
 *
 * Follow up:
 * Could you write a solution that runs in O(m + n) time and use only O(1) memory?
 */

fun main() {
    val intersect = ListNode(8)
    intersect.next = ListNode(4)
    intersect.next!!.next = ListNode(5)

    val headA = ListNode(4)
    headA.next = ListNode(1)
    headA.next!!.next = intersect

    // 리스트 B: 99 -> 1 -> 8 -> 10
    val headB = ListNode(5)
    headB.next = ListNode(6)
    headB.next!!.next = ListNode(1)
    headB.next!!.next!!.next = intersect

    val result = Solution().getIntersectionNode(headA, headB)

    println(result?.`val`)
}

class ListNode(
    var `val`: Int,
    var next: ListNode? = null
)

class Solution {
    // 시간 복잡도 : O(M + N)
    // 공간 복잡도 : O(1)
    fun getIntersectionNode(headA:ListNode?, headB:ListNode?):ListNode? {
        var pointerA = headA
        var pointerB = headB

        // 같은 node가 나올 때까지 반복
        while (pointerA != pointerB) {
            // 자기 길이만큼 다 이동하면 상대방의 head로 이동해서 길이를 보정함
            pointerA = if (pointerA == null) headB else pointerA.next
            pointerB = if (pointerB == null) headA else pointerB.next
        }

        return pointerA
    }
}