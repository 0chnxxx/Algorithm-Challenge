/**
 * Given a list of 24-hour clock time points in "HH:MM" format, return the minimum minutes difference between any two time-points in the list.
 *
 * Constraints:
 * 2 <= timePoints.length <= 2 * 10^4
 * timePoints[i] is in the format "HH:MM".
 */

fun main() {
    val timePoints = listOf("23:59", "00:00")

    val result = Solution().findMinDifference(timePoints)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N log N)
    // 공간 복잡도 : O(N)
    fun findMinDifference(timePoints: List<String>): Int {
        val minutes = mutableListOf<Int>()

        for (time in timePoints) {
            val parts = time.split(":")
            val hour = parts[0].toInt()
            val minute = parts[1].toInt()
            minutes.add(hour * 60 + minute)
        }

        minutes.sort()

        var min = Int.MAX_VALUE

        for (i in 1 until minutes.size) {
            min = minOf(min, minutes[i] - minutes[i - 1])
        }

        val circular = 1440 - minutes.last() + minutes.first()

        min = minOf(min, circular)

        return min
    }
}
