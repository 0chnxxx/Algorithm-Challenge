/**
 * Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.
 *
 * 1 <= nums.length <= 6
 * -10 <= nums[i] <= 10
 * All the integers of nums are unique.
 */

fun main() {
    val nums = intArrayOf(1, 2, 3)

    val solution = Solution().permute(nums)

    println(solution)
}

class Solution {
    // 시간 복잡도 : O(N * N!)
    // 공간 복잡도 : O(N * N!)
    fun permute(nums: IntArray): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        val used = BooleanArray(nums.size)

        fun dfs(current: MutableList<Int>) {
            if (current.size == nums.size) {
                result.add(current.toList())
                return
            }

            for (i in nums.indices) {
                if (used[i]) continue

                used[i] = true
                current.add(nums[i])
                dfs(current)
                current.removeAt(current.size - 1)
                used[i] = false
            }
        }

        dfs(mutableListOf())

        return result
    }
}
