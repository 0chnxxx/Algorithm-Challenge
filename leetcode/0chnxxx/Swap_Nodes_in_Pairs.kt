/**
 * Given a linked list, swap every two adjacent nodes and return its head. You must solve the problem without modifying the values in the list's nodes (i.e., only nodes themselves may be changed.)
 *
 * The number of nodes in the list is in the range [0, 100].
 * 0 <= Node.val <= 100
 */

fun main() {
    val head: ListNode? = ListNode(1, ListNode(2, ListNode(3, ListNode(4, null))))

    var solution = Solution().swapPairs(head)

    val result = mutableListOf<Int>()

    while (solution != null) {
        result.add(solution.`val`)
        solution = solution.next
    }

    println(result)
}

class ListNode(
    var `val`: Int,
    var next: ListNode? = null
)

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun swapPairs(head: ListNode?): ListNode? {
        if (head?.next == null) return head

        val root: ListNode? = head.next
        var current: ListNode? = head
        var prev: ListNode? = null

        while (current?.next != null) {
            val next = current.next

            current.next = next?.next
            next?.next = current

            if (prev != null) {
                prev.next = next
            }

            current = current.next
            prev = next?.next
        }

        return root
    }
}
