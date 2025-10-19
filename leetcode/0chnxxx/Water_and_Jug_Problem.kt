/**
 * You are given two jugs with capacities x liters and y liters. You have an infinite water supply.
 * Return whether the total amount of water in both jugs may reach target using the following operations:
 * - Fill either jug completely with water.
 * - Completely empty either jug.
 * - Pour water from one jug into another until the receiving jug is full, or the transferring jug is empty.
 *
 * Constraints:
 * 1 <= x, y, target <= 10^3
 */

fun main() {
    val x = 3
    val y = 5
    val target = 4

    val result = Solution().canMeasureWater(x, y, target)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(log(min(X, Y))
    // 공간 복잡도 : O(1)
    fun canMeasureWater(x: Int, y: Int, target: Int): Boolean {
        fun gcd(x: Int, y: Int): Int {
            var r = x % y

            if (r == 0) {
                return y
            } else {
                return gcd(y, r)
            }
        }

        return target <= x + y && target % gcd(x, y) == 0
    }
}
