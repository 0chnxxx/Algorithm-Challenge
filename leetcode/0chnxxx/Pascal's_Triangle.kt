/**
 * Given an integer numRows, return the first numRows of Pascal's triangle.
 *
 * In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:
 *
 * Constraints:
 * 1 <= numRows <= 30
 */

fun main() {
    val numRows = 5

    val solution = Solution().generate(numRows)

    println(solution.joinToString(", "))
}

class Solution {
    // 시간 복잡도 : O(N^2)
    // 공간 복잡도 : O(N^2)
    fun generate(numRows: Int): List<List<Int>> {
        // 2차원 배열을 생성
        val result = mutableListOf<List<Int>>()

        // numRows 만큼 반복
        for (i in 0 until numRows) {
            // 각 row를 생성
            val row = MutableList(i + 1) { 1 }

            // row의 최대 칸 수만큼 반복
            for (j in 1 until i) {
                // (왼쪽 위칸) + (위칸)
                row[j] = result[i - 1][j - 1] + result[i - 1][j]
            }

            // row를 저장
            result.add(row)
        }

        return result
    }
}