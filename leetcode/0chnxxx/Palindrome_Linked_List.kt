import java.util.Stack

/**
 * Given the head of a singly linked list, return true if it is a palindrome or false otherwise.
 *
 * Constraints:
 * The number of nodes in the list is in the range [1, 10^5].
 * 0 <= Node.val <= 9
 *
 * Follow up:
 * Could you do it in O(n) time and O(1) space?
 */

fun main() {
    val head = ListNode(1, ListNode(2, ListNode(2, ListNode(1))))

    val result = Solution().isPalindrome(head)

    println(result)
}

class ListNode(
    var `val`: Int,
    var next: ListNode? = null
)

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun isPalindrome(head: ListNode?): Boolean {
        if (head == null || head.next == null) return true

        // slow는 중간 fast는 맨끝
        var slow = head
        var fast = head

        while (fast?.next != null && fast.next?.next != null) {
            slow = slow?.next
            fast = fast.next?.next
        }

        // 연결 리스트를 뒤집는 함수
        fun reverse(head: ListNode?): ListNode? {
            var prev: ListNode? = null
            var current = head

            while (current != null) {
                val temp = current.next
                current.next = prev
                prev = current
                current = temp
            }

            return prev
        }

        // 중간부터 뒤집힌 서브리스트
        val half = reverse(slow?.next)

        // head와 뒤집힌 half를 비교
        var first = head
        var second = half
        var result = true

        while (second != null) {
            if (first?.`val` != second.`val`) {
                result = false
                break
            }

            first = first.next
            second = second.next
        }

        // 원복
        slow?.next = reverse(half)

        return result
    }
}