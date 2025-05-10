/**
 * Given the head of a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.
 * Return the linked list sorted as well.
 *
 * Constraints:
 * The number of nodes in the list is in the range [0, 300].
 * -100 <= Node.val <= 100
 * The list is guaranteed to be sorted in ascending order.
 */

class ListNode(
    var `val`: Int,
    var next: ListNode? = null
)

fun main() {
    var head: ListNode? = ListNode(1, ListNode(1, ListNode(1, ListNode(2, ListNode(3, null)))))

    var solution = Solution().deleteDuplicates(head)

    while (solution != null) {
        println(solution.`val`)
        solution = solution.next
    }
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun deleteDuplicates(head: ListNode?): ListNode? {
        val temp = ListNode(0, head)
        var prev: ListNode? = temp
        var current: ListNode? = head

        while (current != null) {
            // 현재 노드 값과 다음 노드 값이 같다면
            if (current.next != null && current.`val` == current.next?.`val`) {
                val duplicate = current.`val`

                // 현재 노드를 가리키는 포인터를 중복이 없을 때까지 이동
                while (current != null && current.`val` == duplicate) {
                    current = current.next
                }

                // 이전 노드에 중복되지 않은 노드를 연결
                prev?.next = current
            } else {
                // 포인터 이동
                prev = current
                current = current.next
            }
        }

        // 임시 노드를 제거 후 반환
        return temp.next
    }

//    // 시간 복잡도 : O(N)
//    // 공간 복잡도 : O(1)
//    fun deleteDuplicates(head: ListNode?): ListNode? {
//        val temp = ListNode(Int.MIN_VALUE, head)
//        var current: ListNode? = temp
//        var next: ListNode? = head
//
//        while (current != null) {
//            if (current.`val` != next?.`val`) {
//                var pointer = next
//                var isMove = false
//
//                while (pointer != null && pointer.`val` == pointer.next?.`val`) {
//                    pointer = pointer.next
//                    isMove = true
//                }
//
//                if (isMove) {
//                    current.next = pointer?.next
//                    next = pointer?.next
//                    continue
//                }
//            }
//
//            current = current.next
//            next = next?.next
//        }
//
//        return temp.next
//    }
}
