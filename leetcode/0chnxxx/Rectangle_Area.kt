/**
 * Given the coordinates of two rectilinear rectangles in a 2D plane, return the total area covered by the two rectangles.
 * The first rectangle is defined by its bottom-left corner (ax1, ay1) and its top-right corner (ax2, ay2).
 * The second rectangle is defined by its bottom-left corner (bx1, by1) and its top-right corner (bx2, by2).
 *
 * Constraints:
 * -10^4 <= ax1 <= ax2 <= 10^4
 * -10^4 <= ay1 <= ay2 <= 10^4
 * -10^4 <= bx1 <= bx2 <= 10^4
 * -10^4 <= by1 <= by2 <= 10^4
 */

fun main() {
    val ax1 = -3
    val ay1 = 0
    val ax2 = 3
    val ay2 = 4
    val bx1 = 0
    val by1 = -1
    val bx2 = 9
    val by2 = 2

    val result = Solution().computeArea(ax1, ay1, ax2, ay2, bx1, by1, bx2, by2)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(1)
    // 공간 복잡도 : O(1)
    fun computeArea(ax1: Int, ay1: Int, ax2: Int, ay2: Int, bx1: Int, by1: Int, bx2: Int, by2: Int): Int {
        val area1 = (ax2 - ax1) * (ay2- ay1)
        val area2 = (bx2 - bx1) * (by2 - by1)
        val area3 = maxOf(0, minOf(ax2, bx2) - maxOf(ax1, bx1)) * maxOf(0, minOf(ay2, by2) - maxOf(ay1, by1))

        return area1 + area2 - area3
    }
}
