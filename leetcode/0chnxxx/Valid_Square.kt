/**
 * Given the coordinates of four points in 2D space p1, p2, p3 and p4, return true if the four points construct a square.
 * The coordinate of a point pi is represented as [xi, yi].
 * The input is not given in any order.
 * A valid square has four equal sides with positive length and four equal angles (90-degree angles).
 *
 * Constraints:
 * p1.length == p2.length == p3.length == p4.length == 2
 * -104 <= xi, yi <= 10^4
 */

fun main() {
    val p1 = intArrayOf(0, 0)
    val p2 = intArrayOf(1, 1)
    val p3 = intArrayOf(1, 0)
    val p4 = intArrayOf(0, 1)

    val result = Solution().validSquare(p1, p2, p3, p4)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(1)
    // 공간 복잡도 : O(1)
    fun validSquare(p1: IntArray, p2: IntArray, p3: IntArray, p4: IntArray): Boolean {
        val distances = mutableListOf<Int>()
        val points = listOf(p1, p2, p3, p4)

        for (i in 0 until 4) {
            for (j in i + 1 until 4) {
                distances.add(dist(points[i], points[j]))
            }
        }

        distances.sort()

        val side = distances[0]

        if (side == 0) return false

        for (i in 1..3) {
            if (distances[i] != side) return false
        }

        return distances[4] == distances[5]
    }

    private fun dist(a: IntArray, b: IntArray): Int {
        val dx = a[0] - b[0]
        val dy = a[1] - b[1]

        return dx * dx + dy * dy
    }
}