/**
 * Given an integer rowIndex, return the rowIndexth (0-indexed) row of the Pascal's triangle.
 * In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:
 *
 * Constraints:
 * 0 <= rowIndex <= 33
 *
 * Follow up:
 * Could you optimize your algorithm to use only O(rowIndex) extra space?
 */

fun main() {
    val rowIndex = 3

    val solution = Solution().getRow(rowIndex)

    println(solution.joinToString(", "))
}

class Solution {
    // 시간 복잡도 : O(N^2)
    // 공간 복잡도 : O(N)
    fun getRow(rowIndex: Int): List<Int> {
        // 한 개의 row만 메모리에 유지
        val row = MutableList(rowIndex + 1) { 0 }

        row[0] = 1

        // 직전 row와 현재 row만을 다룸
        // 뒤에서부터 직전 row의 두 수(j, j - 1)를 기반으로 계산함
        for (i in 1..rowIndex) {
            for (j in i downTo 1) {
                row[j] = row[j] + row[j - 1]
            }
        }

        return row
    }

//    // 시간 복잡도 : O(N^2)
//    // 공간 복잡도 : O(N^2)
//    fun getRow(rowIndex: Int): List<Int> {
//        val result = mutableListOf<List<Int>>()
//
//        for (i in 0 until rowIndex + 1) {
//            val row = MutableList(i + 1) { 1 }
//
//            for (j in 1 until i) {
//                row[j] = result[i - 1][j - 1] + result[i - 1][j]
//            }
//
//            result.add(row)
//        }
//
//        return result.last()
//    }
}
