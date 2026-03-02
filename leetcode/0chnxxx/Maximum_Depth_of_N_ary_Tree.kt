/**
 * Given a n-ary tree, find its maximum depth.
 * The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 * Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by the null value (See examples).
 *
 * Constraints:
 * The total number of nodes is in the range [0, 10^4].
 * The depth of the n-ary tree is less than or equal to 1000.
 */

class Node(
    var `val`: Int
) {
    var children: List<Node?> = listOf()
}

class Solution {
    // 시간 복잡도 : O(N)
    // 시간 복잡도 : O(H)
    fun maxDepth(root: Node?): Int {
        if (root == null) return 0

        var max = 0

        for (child in root.children) {
            max = maxOf(max, maxDepth(child))
        }

        return max + 1
    }
}
