/**
 * Given an array of intervals intervals where intervals[i] = [starti, endi], return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.
 * Note that intervals which only touch at a point are non-overlapping.
 * For example, [1, 2] and [2, 3] are non-overlapping.
 *
 * Constraints:
 * 1 <= intervals.length <= 10^5
 * intervals[i].length == 2
 * -5 * 10^4 <= starti < endi <= 5 * 10^4
 */

fun main() {
    val intervals = arrayOf(
        intArrayOf(1, 2),
        intArrayOf(2, 3),
        intArrayOf(3, 4),
        intArrayOf(1, 3)
    )

    val result = Solution().eraseOverlapIntervals(intervals)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N log N)
    // 공간 복잡도 : O(1)
    fun eraseOverlapIntervals(intervals: Array<IntArray>): Int {
        if (intervals.isEmpty()) return 0

        intervals.sortBy { it[1] }

        var count = 1
        var end = intervals[0][1]

        for (i in 1 until intervals.size) {
            if (intervals[i][0] >= end) {
                count++
                end = intervals[i][1]
            }
        }

        return intervals.size - count
    }
}