/**
 * You are given an integer array nums of length n where nums is a permutation of the numbers in the range [0, n - 1].
 *
 * You should build a set s[k] = {nums[k], nums[nums[k]], nums[nums[nums[k]]], ... } subjected to the following rule:
 * The first element in s[k] starts with the selection of the element nums[k] of index = k.
 * The next element in s[k] should be nums[nums[k]], and then nums[nums[nums[k]]], and so on.
 * We stop adding right before a duplicate element occurs in s[k].
 * Return the longest length of a set s[k].
 *
 * Constraints:
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i] < nums.length
 * All the values of nums are unique.
 */

fun main() {
    val nums = intArrayOf(5, 4, 0, 3, 1, 6, 2)

    val result = Solution().arrayNesting(nums)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(N)
    fun arrayNesting(nums: IntArray): Int {
        val visited = BooleanArray(nums.size)
        var maxLength = 0

        for (i in nums.indices) {
            if (visited[i]) continue

            var count = 0
            var current = i

            while (!visited[current]) {
                visited[current] = true
                current = nums[current]
                count++
            }

            maxLength = maxOf(maxLength, count)
        }

        return maxLength
    }
}