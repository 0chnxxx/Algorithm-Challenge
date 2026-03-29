/**
 * You are given m arrays, where each array is sorted in ascending order.
 * You can pick up two integers from two different arrays (each array picks one) and calculate the distance.
 * We define the distance between two integers a and b to be their absolute difference |a - b|.
 * Return the maximum distance.
 *
 * Constraints:
 * m == arrays.length
 * 2 <= m <= 10^5
 * 1 <= arrays[i].length <= 500
 * -10^4 <= arrays[i][j] <= 10^4
 * arrays[i] is sorted in ascending order.
 * There will be at most 105 integers in all the arrays.
 */

fun main() {
    val arrays = listOf(
        listOf(1, 2, 3),
        listOf(4, 5),
        listOf(1, 2, 3)
    )

    val result = Solution().maxDistance(arrays)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun maxDistance(arrays: List<List<Int>>): Int {
        var minSoFar = arrays[0][0]
        var maxSoFar = arrays[0].last()

        var result = 0

        for (i in 1 until arrays.size) {
            val currentMin = arrays[i][0]
            val currentMax = arrays[i].last()

            result = maxOf(result, currentMax - minSoFar, maxSoFar - currentMin)

            minSoFar = minOf(minSoFar, currentMin)
            maxSoFar = maxOf(maxSoFar, currentMax)
        }

        return result
    }
}
