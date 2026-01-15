/**
 * Given an integer array nums, return all the different possible non-decreasing subsequences of the given array with at least two elements.
 * You may return the answer in any order.
 *
 * Constraints*
 * 1 <= nums.length <= 15
 * -100 <= nums[i] <= 100
 */

fun main() {
    val nums = intArrayOf(4, 6, 7, 7)

    val result = Solution().findSubsequences(nums)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N * 2^N)
    // 공간 복잡도 : O(N)
    fun findSubsequences(nums: IntArray): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        val path = mutableListOf<Int>()

        fun dfs(start: Int) {
            if (path.size >= 2) {
                result.add(path.toList())
            }

            val used = HashSet<Int>()

            for (i in start until nums.size) {
                if (path.isNotEmpty() && nums[i] < path.last()) continue
                if (nums[i] in used) continue

                used.add(nums[i])
                path.add(nums[i])
                dfs(i + 1)
                path.removeAt(path.size - 1)
            }
        }

        dfs(0)

        return result
    }
}