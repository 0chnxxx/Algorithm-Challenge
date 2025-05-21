/**
 * Given an integer n, return all the structurally unique BST's (binary search trees), which has exactly n nodes of unique values from 1 to n.
 * Return the answer in any order.
 *
 * Constraints:
 * 1 <= n <= 8
 */

fun main() {
    val n = 3

    val solution = Solution().generateTrees(n)

    val output = solution.map { tree ->
        val result = mutableListOf<Int?>()
        val queue: ArrayDeque<TreeNode?> = ArrayDeque()

        queue.add(tree)

        while (queue.isNotEmpty()) {
            val node = queue.removeFirst()

            if (node != null) {
                result.add(node.`val`)
                queue.add(node.left)
                queue.add(node.right)
            } else {
                result.add(null)
            }
        }

        while (result.isNotEmpty() && result.last() == null) {
            result.removeAt(result.size - 1)
        }

        result
    }

    println(output)
}

class TreeNode(
    var `val`: Int,
    var left: TreeNode? = null,
    var right: TreeNode? = null
)

class Solution {
    // 시간 복잡도 : O(Cn * N)
    // 공간 복잡도 : O(Cn * N)
    fun generateTrees(n: Int): List<TreeNode?> {
        // 노드가 없는 경우 빈 리스트를 반환
        if (n == 0) return emptyList()

        // 트리 구축
        fun build(start: Int, end: Int): List<TreeNode?> {
            val trees = mutableListOf<TreeNode?>()

            // start가 end보다 큰 경우엔 트리가 존재하지 않으므로 null을 반환
            if (start > end) {
                trees.add(null)
                return trees
            }

            // 1부터 n까지 순회
            for (i in start..end) {
                // 재귀를 통해 i를 기준으로 만들 수 있는 좌, 우 트리를 찾는다
                val lefts = build(start, i - 1)
                val rights = build(i + 1, end)

                // null / 1 / 2, 3
                // 1 / 2 / 3
                // 1, 2 / 3 / null
                // null / 1 / 3, 2
                // 1, 2 / 3 / null

                // 찾은 트리를 i를 root로 삼아서 채워넣는다.
                for (left in lefts) {
                    for (right in rights) {
                        val root = TreeNode(i)

                        root.left = left
                        root.right = right

                        trees.add(root)
                    }
                }
            }

            return trees
        }

        return build(1, n)
    }
}