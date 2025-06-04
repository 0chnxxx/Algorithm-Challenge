/**
 * Given the head of a singly linked list where elements are sorted in ascending order, convert it to a height-balanced binary search tree.
 *
 * Constraints:
 * The number of nodes in head is in the range [0, 2 * 10^4].
 * -10^5 <= Node.val <= 10^5
 */

fun main() {
    val head = ListNode(-10, ListNode(-3, ListNode(0, ListNode(5, ListNode(9)))))

    val solution = Solution().sortedListToBST(head)

    println(solution)
}

class ListNode(
    var `val`: Int,
    var next: ListNode? = null
)

class TreeNode(
    var `val`: Int,
    var left: TreeNode? = null,
    var right: TreeNode? = null
)

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(log N)
    fun sortedListToBST(head: ListNode?): TreeNode? {
        var dummy = head
        var current = head
        var size = 0

        while (current != null) {
            size++
            current = current.next
        }

        fun convertListToBST(start: Int, end: Int): TreeNode? {
            if (start > end) return null

            val mid = (start + end) / 2

            val left = convertListToBST(start, mid - 1)
            val root = TreeNode(dummy!!.`val`)

            root.left = left
            dummy = dummy!!.next

            val right = convertListToBST(mid + 1, end)

            root.right = right

            return root
        }

        return convertListToBST(0, size - 1)
    }
}
