import kotlin.random.Random

/**
 * You are given an array of non-overlapping axis-aligned rectangles rects where rects[i] = [ai, bi, xi, yi] indicates that (ai, bi) is the bottom-left corner point of the ith rectangle and (xi, yi) is the top-right corner point of the ith rectangle.
 * Design an algorithm to pick a random integer point inside the space covered by one of the given rectangles.
 * A point on the perimeter of a rectangle is included in the space covered by the rectangle.
 * Any integer point inside the space covered by one of the given rectangles should be equally likely to be returned.
 * Note that an integer point is a point that has integer coordinates.
 *
 * Implement the Solution class:
 * Solution(int[][] rects) Initializes the object with the given rectangles rects.
 * int[] pick() Returns a random integer point [u, v] inside the space covered by one of the given rectangles.
 *
 * Constraints:
 * 1 <= rects.length <= 100
 * rects[i].length == 4
 * -10^9 <= ai < xi <= 10^9
 * -10^9 <= bi < yi <= 10^9
 * xi - ai <= 2000
 * yi - bi <= 2000
 * All the rectangles do not overlap.
 * At most 104 calls will be made to pick.
 */

fun main() {
    val rects = arrayOf(
        intArrayOf(-2, -2, 1, 1),
        intArrayOf(2, 2, 4, 6)
    )

    val solution = Solution(rects)

    println(solution.pick().joinToString(","))
    println(solution.pick().joinToString(","))
    println(solution.pick().joinToString(","))
    println(solution.pick().joinToString(","))
    println(solution.pick().joinToString(","))
}

class Solution(private val rects: Array<IntArray>) {
    private val prefix = IntArray(rects.size)
    private val totalPoints: Int

    init {
        var sum = 0

        for (i in rects.indices) {
            val r = rects[i]
            val count = (r[2] - r[0] + 1) * (r[3] - r[1] + 1)

            sum += count
            prefix[i] = sum
        }

        totalPoints = sum
    }

    // 시간 복잡도 : O(log N)
    // 공간 복잡도 : O(log N)
    fun pick(): IntArray {
        val target = Random.nextInt(totalPoints)
        val rectIndex = binarySearch(target)

        val r = rects[rectIndex]
        val x = Random.nextInt(r[0], r[2] + 1)
        val y = Random.nextInt(r[1], r[3] + 1)

        return intArrayOf(x, y)
    }

    private fun binarySearch(target: Int): Int {
        var left = 0
        var right = prefix.lastIndex

        while (left < right) {
            val mid = left + (right - left) / 2

            if (target < prefix[mid]) {
                right = mid
            } else {
                left = mid + 1
            }
        }

        return left
    }
}