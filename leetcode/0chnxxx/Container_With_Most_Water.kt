/**
 * You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
 *
 * Find two lines that together with the x-axis form a container, such that the container contains the most water.
 *
 * Return the maximum amount of water a container can store.
 *
 * Notice that you may not slant the container.
 *
 * n == height.length
 * 2 <= n <= 10^5
 * 0 <= height[i] <= 10^4
 */

fun main() {
    val height = intArrayOf(1, 8, 6, 2, 5, 4, 8, 3, 7)

    val solution = Solution().maxArea(height)

    println(solution)
}

class Solution {
    // O(N) 풀이
    fun maxArea(height: IntArray): Int {
        var max = 0
        var left = 0
        var right = height.size - 1

        while (left < right) {
            val vertical = minOf(height[left], height[right])
            val horizontal = right - left
            val area = vertical * horizontal

            max = maxOf(max, area)

            if (height[left] < height[right]) {
                left++
            } else {
                right--
            }
        }

        return max
    }
}
