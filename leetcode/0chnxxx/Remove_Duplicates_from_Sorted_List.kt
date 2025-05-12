/**
 * Given the head of a sorted linked list, delete all duplicates such that each element appears only once.
 * Return the linked list sorted as well.
 *
 * Constraints:
 * The number of nodes in the list is in the range [0, 300].
 * -100 <= Node.val <= 100
 * The list is guaranteed to be sorted in ascending order.
 */

fun main() {
    val head = ListNode(1, ListNode(1, ListNode(2)))

    var solution = Solution().deleteDuplicates(head)

    while (solution != null) {
        println(solution.`val`)
        solution = solution.next
    }
}

class ListNode(
    var `val`: Int,
    var next: ListNode? = null
)

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun deleteDuplicates(head: ListNode?): ListNode? {
        var current: ListNode? = head

        while (current != null) {
            if (current.`val` == current.next?.`val`) {
                current.next = current.next?.next
            } else {
                current = current.next
            }
        }

        return head
    }
}
