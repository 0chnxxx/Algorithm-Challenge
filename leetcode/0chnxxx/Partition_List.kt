/**
 * Given the head of a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
 * You should preserve the original relative order of the nodes in each of the two partitions.
 *
 * Constraints:
 * The number of nodes in the list is in the range [0, 200].
 * -100 <= Node.val <= 100
 * -200 <= x <= 200
 */

fun main() {
    val head = ListNode(1, ListNode(4, ListNode(3, ListNode(2, ListNode(5, ListNode(2))))))
    val x = 3

    var solution = Solution().partition(head,  x)

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
    fun partition(head: ListNode?, x: Int): ListNode? {
        val beforeHead = ListNode(0)
        var before = beforeHead
        val afterHead = ListNode(0)
        var after = afterHead
        var current = head

        while (current != null) {
            if (current.`val` < x) {
                before.next = current
                before = before.next!!
            } else {
                after.next = current
                after = after.next!!
            }

            current = current.next
        }

        after.next = null
        before.next = afterHead.next

        return beforeHead.next
    }
}
