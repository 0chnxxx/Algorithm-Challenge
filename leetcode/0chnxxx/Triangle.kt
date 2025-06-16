/**
 * Given a triangle array, return the minimum path sum from top to bottom.
 *
 * For each step, you may move to an adjacent number of the row below.
 * More formally, if you are on index i on the current row, you may move to either index i or index i + 1 on the next row.
 *
 * Constraints:
 * 1 <= triangle.length <= 200
 * triangle[0].length == 1
 * triangle[i].length == triangle[i - 1].length + 1
 * -10^4 <= triangle[i][j] <= 10^4
 *
 *
 * Follow up:
 * Could you do this using only O(n) extra space, where n is the total number of rows in the triangle?
 */

fun main() {
    val triangle = listOf(
        listOf(2),
        listOf(3, 4),
        listOf(6, 5, 7),
        listOf(4, 1, 8, 3)
    )

    val solution = Solution().minimumTotal(triangle)

    println(solution)
}

class Solution {
    // 시간 복잡도 : O(N^2)
    // 공간 복잡도 : O(N)
    fun minimumTotal(triangle: List<List<Int>>): Int {
        // 맨 마지막 줄부터 탐색
        val n = triangle.size
        val row = triangle.last().toMutableList()

        // 마지막에서 윗줄부터 첫번째 줄까지 탐색
        for (i in n - 2 downTo 0) {
            // 해당 줄의 크기만큼을 탐색
            for (j in 0..i) {
                // 이전 경로와 다음으로 갈 수 있는 경로의 최소합을 저장
                row[j] = triangle[i][j] + minOf(row[j], row[j + 1])
            }
        }

        // 루트 부분에 최소합이 누적되기 때문에 정답
        return row[0]
    }

//    // 시간 복잡도 : O(N^2)
//    // 공간 복잡도 : O(N^2)
//    fun minimumTotal(triangle: List<List<Int>>): Int {
//        val tree = triangle.map { it.toMutableList() }.toMutableList()
//
//        for (i in 1 until tree.size) {
//            for (j in tree[i].indices) {
//                val fromLeft = if (j > 0) tree[i - 1][j - 1] else Int.MAX_VALUE
//                val fromRight = if (j < tree[i - 1].size) tree[i - 1][j] else Int.MAX_VALUE
//
//                tree[i][j] += minOf(fromLeft, fromRight)
//            }
//        }
//
//        return tree.last().minOrNull()!!
//    }
}
