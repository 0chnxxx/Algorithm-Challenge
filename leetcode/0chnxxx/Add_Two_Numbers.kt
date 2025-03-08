/**
 * You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order, and each of their nodes contains a single digit.
 * Add the two numbers and return the sum as a linked list.
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * 1 <= Node의 갯수 <= 100
 * 0 <= Node.val <= 9
 */

fun main() {
    val l1 = ListNode(2)
    l1.next = ListNode(4)
    l1.next?.next = ListNode(3)

    val l2 = ListNode(5)
    l2.next = ListNode(6)
    l2.next?.next = ListNode(4)

    val solution = Solution().addTwoNumbers(l1, l2)

    printList(solution) // 출력: 7 -> 0 -> 8
}

fun printList(node: ListNode?) {
    var current = node

    while (current != null) {
        print(current.`val`)

        if (current.next != null) print(" -> ")

        current = current.next
    }
}

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

class Solution {
    // 시간 복잡도 : O(max(L1, L2))
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        val root = ListNode(0)
        var node = root
        var carry = 0

        var currentL1 = l1
        var currentL2 = l2

        // 모든 노드 순회
        while (currentL1 != null || currentL2 != null || carry != 0) {
            // 자릿수가 안 맞아서 없는 숫자의 경우 0으로 대체
            val val1 = currentL1?.`val` ?: 0
            val val2 = currentL2?.`val` ?: 0

            // 올림까지 계산
            val sum = val1 + val2 + carry
            carry = sum / 10

            // root 기준으로 링크드 리스트 연결
            node.next = ListNode(sum % 10)
            node = node.next!!

            currentL1 = currentL1?.next
            currentL2 = currentL2?.next
        }

        // root는 0으로 버리는 값이기 때문에 next를 반환
        return root.next
    }
}
