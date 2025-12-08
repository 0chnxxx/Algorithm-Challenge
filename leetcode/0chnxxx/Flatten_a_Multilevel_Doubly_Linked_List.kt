/**
 * You are given a doubly linked list, which contains nodes that have a next pointer, a previous pointer, and an additional child pointer.
 * This child pointer may or may not point to a separate doubly linked list, also containing these special nodes.
 * These child lists may have one or more children of their own, and so on, to produce a multilevel data structure as shown in the example below.
 * Given the head of the first level of the list, flatten the list so that all the nodes appear in a single-level, doubly linked list.
 * Let curr be a node with a child list.
 * The nodes in the child list should appear after curr and before curr.next in the flattened list.
 * Return the head of the flattened list.
 * The nodes in the list must have all of their child pointers set to null.
 *
 * Constraints:
 * The number of Nodes will not exceed 1000.
 * 1 <= Node.val <= 10^5
 *
 * How the multilevel linked list is represented in test cases:
 *
 * We use the multilevel linked list from Example 1 above:
 *  1---2---3---4---5---6--NULL
 *          |
 *          7---8---9---10--NULL
 *              |
 *              11--12--NULL
 *
 * The serialization of each level is as follows:
 * [1,2,3,4,5,6,null]
 * [7,8,9,10,null]
 * [11,12,null]
 *
 * To serialize all levels together, we will add nulls in each level to signify no node connects to the upper node of the previous level. The serialization becomes:
 * [1,    2,    3, 4, 5, 6, null]
 *              |
 * [null, null, 7,    8, 9, 10, null]
 *                    |
 * [            null, 11, 12, null]
 *
 * Merging the serialization of each level and removing trailing nulls we obtain:
 * [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
 */

fun main() {
    val n1 = Node(1)
    val n2 = Node(2)
    val n3 = Node(3)
    val n4 = Node(4)
    val n5 = Node(5)
    val n6 = Node(6)
    val n7 = Node(7)
    val n8 = Node(8)
    val n9 = Node(9)
    val n10 = Node(10)
    val n11 = Node(11)
    val n12 = Node(12)

    // 1-2-3-4-5-6
    n1.next = n2
    n2.prev = n1
    n2.next = n3
    n3.prev = n2
    n3.next = n4
    n4.prev = n3
    n4.next = n5
    n5.prev = n4
    n5.next = n6
    n6.prev = n5

    // 3의 child = 7
    n3.child = n7

    // 7-8-9-10
    n7.next = n8
    n8.prev = n7
    n8.next = n9
    n9.prev = n8
    n9.next = n10
    n10.prev = n9

    // 8의 child = 11
    n8.child = n11

    // 11-12
    n11.next = n12
    n12.prev = n11

    val result = Solution().flatten(n1)
    var current = result

    while (current != null) {
        print("${current.`val`} ")
        current = current.next
    }
}

class Node(
    var `val`: Int
) {
    var prev: Node? = null
    var next: Node? = null
    var child: Node? = null
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(N)
    fun flatten(root: Node?): Node? {
        if (root == null) return null

        dfs(root)

        return root
    }

    private fun dfs(node: Node?): Node? {
        var current = node
        var last: Node? = null

        while (current != null) {
            val next = current.next

            if (current.child != null) {
                val childHead = current.child
                val childTail = dfs(childHead)

                current.next = childHead
                childHead?.prev = current

                if (next != null) {
                    childTail?.next = next
                    next.prev = childTail
                }

                current.child = null
                last = childTail
            } else {
                last = current
            }

            current = next
        }

        return last
    }
}