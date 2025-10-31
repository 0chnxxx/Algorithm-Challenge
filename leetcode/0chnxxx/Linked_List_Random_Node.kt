import java.util.Random

/**
 * Given a singly linked list, return a random node's value from the linked list.
 * Each node must have the same probability of being chosen.
 *
 * Implement the Solution class:
 * Solution(ListNode head) Initializes the object with the head of the singly-linked list head.
 * int getRandom() Chooses a node randomly from the list and returns its value. All the nodes of the list should be equally likely to be chosen.
 *
 * Constraints:
 * The number of nodes in the linked list will be in the range [1, 10^4].
 * -10^4 <= Node.val <= 10^4
 * At most 104 calls will be made to getRandom.
 *
 *
 * Follow up:
 * What if the linked list is extremely large and its length is unknown to you?
 * Could you solve this efficiently without using extra space?
 */

fun main() {
    val head = ListNode(1, ListNode(2, ListNode(3)))

    val result = Solution(head).getRandom()

    println(result)
}

class ListNode(
    var `val`: Int,
    var next: ListNode? = null
)

class Solution(head: ListNode?) {
    private val values = mutableListOf<Int>()

    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(N)
    init {
        var current = head

        while (current != null) {
            values.add(current.`val`)
            current = current.next
        }
    }

    // 시간 복잡도 : O(1)
    // 공간 복잡도 : O(N)
    fun getRandom(): Int {
        return values[Random().nextInt(values.size)]
    }
}