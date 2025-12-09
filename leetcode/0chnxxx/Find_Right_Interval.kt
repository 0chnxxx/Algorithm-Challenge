/**
 * You are given an array of intervals, where intervals[i] = [starti, endi] and each starti is unique.
 * The right interval for an interval i is an interval j such that startj >= endi and startj is minimized.
 * Note that i may equal j.
 * Return an array of right interval indices for each interval i.
 * If no right interval exists for interval i, then put -1 at index i.
 *
 * Constraints:
 * 1 <= intervals.length <= 2 * 10^4
 * intervals[i].length == 2
 * -10^6 <= starti <= endi <= 10^6
 * The start point of each interval is unique.
 */

fun main() {
    val intervals = arrayOf(
        intArrayOf(1, 2)
    )

    val result = Solution().findRightInterval(intervals)

    println(result.joinToString(", "))
}

class Solution {
    // 시간 복잡도 : O(N log N)
    // 공간 복잡도 : O(N)
    fun findRightInterval(intervals: Array<IntArray>): IntArray {
        val n = intervals.size
        val result = IntArray(n)

        val starts = Array(n) { i -> intArrayOf(intervals[i][0], i) }

        starts.sortBy { it[0] }

        for (i in 0 until n) {
            val end = intervals[i][1]
            var left = 0
            var right = n - 1
            var index = -1

            while (left <= right) {
                val mid = (left + right) / 2

                if (starts[mid][0] >= end) {
                    index = starts[mid][1]
                    right = mid - 1
                } else {
                    left = mid + 1
                }
            }

            result[i] = index
        }

        return result
    }
}