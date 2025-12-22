/**
 * You are given n points in the plane that are all distinct, where points[i] = [xi, yi].
 * A boomerang is a tuple of points (i, j, k) such that the distance between i and j equals the distance between i and k (the order of the tuple matters).
 * Return the number of boomerangs.
 *
 * Constraints:
 * n == points.length
 * 1 <= n <= 500
 * points[i].length == 2
 * -10^4 <= xi, yi <= 10^4
 * All the points are unique.
 */

fun main() {
    val points = arrayOf(
        intArrayOf(0, 0),
        intArrayOf(1, 0),
        intArrayOf(2, 0)
    )

    val result = Solution().numberOfBoomerangs(points)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N^2)
    // 공간 복잡도 : O(N)
    fun numberOfBoomerangs(points: Array<IntArray>): Int {
        var result = 0

        for (i in points.indices) {
            val map = HashMap<Int, Int>()

            for (j in points.indices) {
                if (i == j) continue

                val dx = points[i][0] - points[j][0]
                val dy = points[i][1] - points[j][1]
                val distance = dx * dx + dy * dy

                map[distance] = map.getOrDefault(distance, 0) + 1
            }

            for (count in map.values) {
                result += count * (count - 1)
            }
        }

        return result
    }
}