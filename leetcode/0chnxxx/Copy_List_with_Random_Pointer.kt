/**
 * A linked list of length n is given such that each node contains an additional random pointer, which could point to any node in the list, or null.
 * Construct a deep copy of the list.
 *
 * The deep copy should consist of exactly n brand new nodes, where each new node has its value set to the value of its corresponding original node.
 * Both the next and random pointer of the new nodes should point to new nodes in the copied list such that the pointers in the original list and copied list represent the same list state.
 * None of the pointers in the new list should point to nodes in the original list.
 *
 * For example, if there are two nodes X and Y in the original list, where X.random --> Y, then for the corresponding two nodes x and y in the copied list, x.random --> y.
 * Return the head of the copied linked list.
 *
 * The linked list is represented in the input/output as a list of n nodes.
 * Each node is represented as a pair of [val, random_index] where:
 *
 * val: an integer representing Node.val
 * random_index: the index of the node (range from 0 to n-1) that the random pointer points to, or null if it does not point to any node.
 * Your code will only be given the head of the original linked list.
 *
 * Constraints:
 * 0 <= n <= 1000
 * -10^4 <= Node.val <= 10^4
 * Node.random is null or is pointing to some node in the linked list.
 */

fun main() {
    val input = listOf(
        Pair(7, null),
        Pair(13, 0),
        Pair(11, 4),
        Pair(10, 2),
        Pair(1, 0)
    )

    val nodes = input.map { Node(it.first) }

    for (i in nodes.indices) {
        if (i < nodes.size - 1) {
            nodes[i].next = nodes[i + 1]
        }

        val randomIndex = input[i].second

        if (randomIndex != null) {
            nodes[i].random = nodes[randomIndex]
        }
    }

    val copiedHead = Solution().copyRandomList(nodes[0])
    val result = mutableListOf<Pair<Int, Int?>>()
    val copiedNodeToIndex = mutableMapOf<Node, Int>()

    var index = 0
    var current = copiedHead

    while (current != null) {
        copiedNodeToIndex[current] = index++
        current = current.next
    }

    current = copiedHead

    while (current != null) {
        val valCopy = current.`val`
        val randomIndex = current.random?.let { copiedNodeToIndex[it] }

        result.add(Pair(valCopy, randomIndex))
        current = current.next
    }

    println(result)
}

class Node(
    var `val`: Int,
    var next: Node? = null,
    var random: Node? = null
)

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(N)
    fun copyRandomList(node: Node?): Node? {
        if (node == null) return null

        val map = mutableMapOf<Node, Node>()

        var currentNode = node

        while (currentNode != null) {
            map[currentNode] = Node(currentNode.`val`)
            currentNode = currentNode.next
        }

        currentNode = node

        while (currentNode != null) {
            map[currentNode]?.next = map[currentNode.next]
            map[currentNode]?.random = map[currentNode.random]
            currentNode = currentNode.next
        }

        return map[node]
    }
}