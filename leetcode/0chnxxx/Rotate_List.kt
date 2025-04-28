/**
 * Given the head of a linked list, rotate the list to the right by k places.
 *
 * The number of nodes in the list is in the range [0, 500].
 * -100 <= Node.val <= 100
 * 0 <= k <= 2 * 109
 */

fun main() {
    val head = ListNode(ListNode(ListNode(ListNode(ListNode(null, 5), 4), 3), 2), 1)
    val k = 2

    var solution = Solution().rotateRight(head, k)

    while (solution != null) {
        println(solution.`val`)
        solution = solution.next
    }
}

class ListNode(
    var next: ListNode? = null,
    var `val`: Int
)

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun rotateRight(head: ListNode?, k: Int): ListNode? {
        if (head?.next == null || k == 0) return head

        var length = 1
        var tail = head

        while (tail?.next != null) {
            tail = tail.next
            length++
        }

        // 나누기 연산으로 최소 회전 횟수를 구함
        val move = k % length

        if (move == 0) return head

        // Circular Linked List로 변경
        tail?.next = head

        var newTail = head

        // 맨 끝에서 최소 회전 횟수만큼 떨어진 곳을 newTail로 구함
        for (i in 1 until length - move) {
            newTail = newTail?.next
        }

        // Circular Linked List기 때문에 newTail의 next가 헤드가 됨
        val newHead = newTail?.next

        // newTail의 next를 끊어냄으로써 Circular Linked List에서 Linked List로 다시 변경
        newTail?.next = null

        return newHead
    }

//    // 시간 복잡도 : O(K * N)
//    // 공간 복잡도 : O(1)
//    fun rotateRight(head: ListNode?, k: Int): ListNode? {
//        if (head?.next == null) return head
//
//        var length = 1
//        var pointer = head
//
//        while (pointer?.next != null) {
//            pointer = pointer.next
//            length++
//        }
//
//        pointer = head
//
//        repeat(k) {
//            var last = pointer
//
//            for (i in 1 until length - 1) {
//                last = last?.next
//            }
//
//            last?.next?.next = pointer
//            pointer = last?.next
//            last?.next = null
//        }
//
//        return pointer
//    }
}
