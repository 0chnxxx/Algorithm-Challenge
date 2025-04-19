/**
 * Given an array of intervals where intervals[i] = [starti, endi],
 * merge all overlapping intervals,
 * and return an array of the non-overlapping intervals that cover all the intervals in the input.
 *
 * 1 <= intervals.length <= 104
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 104
 */

fun main() {
    val intervals = arrayOf(
        intArrayOf(1, 3),
        intArrayOf(2, 6),
        intArrayOf(8, 10),
        intArrayOf(15, 18)
    )

    val solution = Solution().merge(intervals)

    solution.forEach { println(it.joinToString(",")) }
}

class Solution {
    // 시간 복잡도 : O(N log N)
    // 공간 복잡도 : O(N)
    // 느리지만 가독성을 챙기는 리팩토링
    fun merge(intervals: Array<IntArray>): Array<IntArray> {
        return intervals
            .sortedBy { it[0] }
            .fold(mutableListOf<IntArray>()) { acc, interval ->
                if (acc.isEmpty() || acc.last()[1] < interval[0]) {
                    acc.add(interval.copyOf())
                } else {
                    acc[acc.lastIndex] = intArrayOf(
                        acc.last()[0],
                        maxOf(acc.last()[1], interval[1])
                    )
                }
                acc
            }
            .toTypedArray()
    }

//    // 시간 복잡도 : O(N log N)
//    // 공간 복잡도 : O(N)
//    fun merge(intervals: Array<IntArray>): Array<IntArray> {
//        // start를 기준으로 오름차순 정렬
//        // O(N log N)
//        intervals.sortBy { it[0] }
//
//        // merge를 할 대상을 관리하는 포인터
//        var index = 0
//
//        // interval들을 순회
//        // O(N)
//        for (i in 1 until intervals.size) {
//            // 병합 대상
//            val last = intervals[index]
//            // 비교 대상
//            val current = intervals[i]
//
//            // 포함하는 경우
//            if (last[1] >= current[0]) {
//                // 병합 대상의 end를 변경
//                last[1] = maxOf(last[1], current[1])
//            // 포함하지 않는 경우
//            } else {
//                // 병합 대상 변경
//                index++
//                intervals[index] = current
//            }
//        }
//
//        // 병합으로 인해 줄어든 배열 처리
//        return intervals.copyOfRange(0, index + 1)
//    }
}
