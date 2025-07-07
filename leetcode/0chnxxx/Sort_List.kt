/**
 * Given the head of a linked list, return the list after sorting it in ascending order.
 *
 * Constraints:
 * The number of nodes in the list is in the range [0, 5 * 10^4].
 * -10^5 <= Node.val <= 10^5
 *
 * Follow up:
 * Can you sort the linked list in O(n logn) time and O(1) memory (i.e. constant space)?
 */

fun main() {
    val head = ListNode(4, ListNode(2, ListNode(1, ListNode(3))))

    val result = Solution().sortList(head)

    println(result)
}

class ListNode(
    var `val`: Int,
    var next: ListNode? = null
)

class Solution {
    // 시간 복잡도 : O(n log n)
    // 공간 복잡도 : O(1)
    fun sortList(head: ListNode?): ListNode? {
        if (head?.next == null) return head

        // 1. 리스트의 길이 구하기
        val length = getLength(head)

        // 더미 헤드 노드 생성
        val dummy = ListNode(0)
        dummy.next = head

        // 2. Bottom-up merge sort
        var size = 1
        while (size < length) {
            var prev = dummy
            var current = dummy.next

            while (current != null) {
                // 첫 번째 서브리스트 분리
                val left = current
                val leftTail = split(left, size)

                // 두 번째 서브리스트 분리
                val right = leftTail?.next
                val rightTail = split(right, size)

                // 다음 반복을 위해 저장
                val nextStart = rightTail?.next

                // 연결 끊기
                leftTail?.next = null
                rightTail?.next = null

                // 병합하고 이전 노드와 연결
                val mergedHead = merge(left, right)
                prev.next = mergedHead

                // prev를 병합된 리스트의 끝으로 이동
                while (prev.next != null) {
                    prev = prev.next!!
                }

                current = nextStart
            }

            size *= 2
        }

        return dummy.next
    }

    // 리스트의 길이를 구하는 함수
    private fun getLength(head: ListNode?): Int {
        var length = 0
        var current = head
        while (current != null) {
            length++
            current = current.next
        }
        return length
    }

    // 주어진 크기만큼 노드를 분리하고 마지막 노드를 반환
    private fun split(head: ListNode?, size: Int): ListNode? {
        if (head == null) return null

        var current = head
        for (i in 1 until size) {
            current = current?.next ?: break
        }

        return current
    }

    // 두 정렬된 리스트를 병합
    private fun merge(list1: ListNode?, list2: ListNode?): ListNode? {
        val dummy = ListNode(0)
        var current = dummy
        var l1 = list1
        var l2 = list2

        while (l1 != null && l2 != null) {
            if (l1.`val` <= l2.`val`) {
                current.next = l1
                l1 = l1.next
            } else {
                current.next = l2
                l2 = l2.next
            }
            current = current.next!!
        }

        // 남은 노드들 연결
        current.next = l1 ?: l2

        return dummy.next
    }
}
