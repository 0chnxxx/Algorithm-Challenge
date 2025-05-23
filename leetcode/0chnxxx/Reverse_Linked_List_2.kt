/**
 * Given the head of a singly linked list and two integers left and right where left <= right, reverse the nodes of the list from position left to position right, and return the reversed list.
 *
 * Constraints:
 * The number of nodes in the list is n.
 * 1 <= n <= 500
 * -500 <= Node.val <= 500
 * 1 <= left <= right <= n
 */

fun main() {
    var head: ListNode? = ListNode(1, ListNode(2, ListNode(3, ListNode(4, ListNode(5)))))
    val left = 2
    val right = 4

    Solution().reverseBetween(head, left, right)

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
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun reverseBetween(head: ListNode?, left: Int, right: Int): ListNode? {
        val dummy = ListNode(0)

        dummy.next = head

        // left - 1까지 이동
        var previous: ListNode? = dummy // 1

        for (i in 1 until left) {
            previous = previous?.next
        }

        // 뒤집기 시작점인 reverse를 잡음
        val reverseHead = previous?.next // 2
        var current = reverseHead?.next // 3

        // right - left번 반복해서 뒤집음
        for (i in 0 until right - left) {
            reverseHead?.next = current?.next // 2.next = 4
            current?.next = previous?.next // 3.next = 2
            previous?.next = current // 1.next = 3
            current = reverseHead?.next // current = 4
        }

        // reverseHead는 left - 1 위치 그대로 고정
        // current를 뒤로 1자리씩 미뤄가면서 reverseHead 위치와 swap

        return dummy.next
    }
}
