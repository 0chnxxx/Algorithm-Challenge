import kotlin.random.Random

/**
 * Given the radius and the position of the center of a circle, implement the function randPoint which generates a uniform random point inside the circle.
 *
 * Implement the Solution class:
 * Solution(double radius, double x_center, double y_center) initializes the object with the radius of the circle radius and the position of the center (x_center, y_center).
 * randPoint() returns a random point inside the circle. A point on the circumference of the circle is considered to be in the circle. The answer is returned as an array [x, y].
 *
 * Constraints:
 * 0 < radius <= 10^8
 * -10^7 <= x_center, y_center <= 10^7
 * At most 3 * 10^4 calls will be made to randPoint.
 */

fun main() {
    val solution = Solution(1.0 ,0.0, 0.0)

    println("null")

    repeat(3) {
        val p = solution.randPoint()
        println("[${"%.5f".format(p[0])}, ${"%.5f".format(p[1])}]")
    }
}

class Solution(radius: Double, x_center: Double, y_center: Double) {
    val radius = radius
    val xCenter = x_center
    val yCenter = y_center

    // 시간 복잡도 : O(1)
    // 공간 복잡도 : O(1)
    fun randPoint(): DoubleArray {
        val theta = 2 * Math.PI * Random.nextDouble()
        val r = radius * Math.sqrt(Random.nextDouble())

        val x = xCenter + r * Math.cos(theta)
        val y = yCenter + r * Math.sin(theta)

        return doubleArrayOf(x, y)
    }
}
