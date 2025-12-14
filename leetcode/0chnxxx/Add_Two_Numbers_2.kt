/**
 * You are given two non-empty linked lists representing two non-negative integers.
 * The most significant digit comes first and each of their nodes contains a single digit.
 * Add the two numbers and return the sum as a linked list.
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * Constraints:
 * The number of nodes in each linked list is in the range [1, 100].
 * 0 <= Node.val <= 9
 * It is guaranteed that the list represents a number that does not have leading zeros.
 *
 * Follow up:
 * Could you solve it without reversing the input lists?
 */

fun main() {
    val l1 = ListNode(7)
    val l2 = ListNode(2)
    val l3 = ListNode(4)
    val l4 = ListNode(3)

    l1.next = l2
    l2.next = l3
    l3.next = l4

    val l5 = ListNode(5)
    val l6 = ListNode(6)
    val l7 = ListNode(4)

    l5.next = l6
    l6.next = l7

    var result = Solution().addTwoNumbers(l1, l5)

    while (result != null) {
        print(result.`val`)
        result = result.next
    }
}

class ListNode(
    var `val`: Int
) {
    var next: ListNode? = null
}

class Solution {
    // 시간 복잡도 : O(N + M)
    // 공간 복잡도 : O(N + M)
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        val s1 = ArrayDeque<Int>()
        val s2 = ArrayDeque<Int>()

        var p1 = l1
        var p2 = l2

        while (p1 != null) {
            s1.add(p1.`val`)
            p1 = p1.next
        }

        while (p2 != null) {
            s2.add(p2.`val`)
            p2 = p2.next
        }

        var carry = 0
        var head: ListNode? = null

        while (s1.isNotEmpty() || s2.isNotEmpty() || carry != 0) {
            val a = if (s1.isNotEmpty()) s1.removeLast() else 0
            val b = if (s2.isNotEmpty()) s2.removeLast() else 0

            val sum = a + b + carry
            carry = sum / 10

            val node = ListNode(sum % 10)
            node.next = head
            head = node
        }

        return head
    }
}