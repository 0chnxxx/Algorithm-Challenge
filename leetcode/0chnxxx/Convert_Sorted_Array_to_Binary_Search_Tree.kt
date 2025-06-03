/**
 * Given an integer array nums where the elements are sorted in ascending order, convert it to a height-balanced binary search tree.
 *
 * Constraints:
 * 1 <= nums.length <= 104
 * -104 <= nums[i] <= 104
 * nums is sorted in a strictly increasing order.
 */

fun main() {
    val nums = intArrayOf(-10, -3, 0, 5, 9)

    val solution = Solution().sortedArrayToBST(nums)

    fun treeToLevelOrderList(root: TreeNode?): List<String> {
        val result = mutableListOf<String>()
        val queue: ArrayDeque<TreeNode?> = ArrayDeque()
        queue.add(root)

        while (queue.isNotEmpty()) {
            val node = queue.removeFirst()

            if (node == null) {
                result.add("null")
            } else {
                result.add(node.`val`.toString())
                queue.add(node.left)
                queue.add(node.right)
            }
        }

        // 마지막 null 들은 의미 없는 padding일 수 있으니 제거할 수도 있음
        // result는 [0, -10, 5, null, -3, null, 9, null, null, null, null]
        // 마지막에 붙은 null은 제거하려면:
        while (result.isNotEmpty() && result.last() == "null") {
            result.removeLast()
        }

        return result
    }

    val list = treeToLevelOrderList(solution)

    println(list)
}

class TreeNode(
    var `val`: Int,
    var left: TreeNode? = null,
    var right: TreeNode? = null
)

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(log N)
    fun sortedArrayToBST(nums: IntArray): TreeNode? {
        fun build(left: Int, right: Int): TreeNode? {
            if (left > right) return null

            val mid = (left + right) / 2
            val node = TreeNode(nums[mid])

            node.left = build(left, mid - 1)
            node.right = build(mid + 1, right)

            return node
        }

        return build(0, nums.lastIndex)
    }
}
