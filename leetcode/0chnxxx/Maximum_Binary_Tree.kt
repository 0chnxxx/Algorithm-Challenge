/**
 * You are given an integer array nums with no duplicates.
 * A maximum binary tree can be built recursively from nums using the following algorithm:
 * Create a root node whose value is the maximum value in nums.
 * Recursively build the left subtree on the subarray prefix to the left of the maximum value.
 * Recursively build the right subtree on the subarray suffix to the right of the maximum value.
 * Return the maximum binary tree built from nums.
 *
 * Constraints:
 * 1 <= nums.length <= 1000
 * 0 <= nums[i] <= 1000
 * All integers in nums are unique.
 */

fun main() {
    val nums = intArrayOf(3, 2, 1, 6, 0, 5)

    val result = Solution().constructMaximumBinaryTree(nums)

    println(result)
}

class TreeNode(
    var `val`: Int
) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(N)
    fun constructMaximumBinaryTree(nums: IntArray): TreeNode? {
        val stack = ArrayDeque<TreeNode>()

        for (num in nums) {
            val curr = TreeNode(num)

            // 작은 값들은 왼쪽 자식으로 붙음
            while (stack.isNotEmpty() && stack.last().`val` < num) {
                curr.left = stack.removeLast()
            }

            // 현재 값이 더 작으면 오른쪽 자식
            if (stack.isNotEmpty()) {
                stack.last().right = curr
            }

            stack.addLast(curr)
        }

        return stack.first()
    }
}