import java.util.Arrays

/**
 * Winter is coming! During the contest, your first job is to design a standard heater with a fixed warm radius to warm all the houses.
 * Every house can be warmed, as long as the house is within the heater's warm radius range.
 * Given the positions of houses and heaters on a horizontal line, return the minimum radius standard of heaters so that those heaters could cover all houses.
 * Notice that all the heaters follow your radius standard, and the warm radius will be the same.
 *
 * Constraints:
 * 1 <= houses.length, heaters.length <= 3 * 10^4
 * 1 <= houses[i], heaters[i] <= 10^9
 */

fun main() {
    val houses = intArrayOf(1, 2, 3)
    val heaters = intArrayOf(2)

    val result = Solution().findRadius(houses, heaters)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N log M)
    // 공간 복잡도 : O(1)
    fun findRadius(houses: IntArray, heaters: IntArray): Int {
        Arrays.sort(houses)
        Arrays.sort(heaters)

        var answer = 0

        for (house in houses) {
            val idx = Arrays.binarySearch(heaters, house)

            val distance = if (idx >= 0) {
                0
            } else {
                val position = -idx - 1

                val rightDistance = if (position < heaters.size) {
                    Math.abs(heaters[position] - house)
                } else {
                    Int.MAX_VALUE
                }

                val leftDistance = if (position - 1 >= 0) {
                    Math.abs(heaters[position - 1] - house)
                } else {
                    Int.MAX_VALUE
                }

                minOf(leftDistance, rightDistance)
            }

            answer = maxOf(answer, distance)
        }

        return answer
    }
}