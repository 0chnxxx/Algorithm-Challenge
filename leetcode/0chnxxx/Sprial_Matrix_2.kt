/**
 * Given a positive integer n, generate an n x n matrix filled with elements from 1 to n^2 in spiral order.
 *
 * 1 <= n <= 20
 */

fun main() {
    val n = 3

    val solution = Solution().generateMatrix(n)

    solution.forEach { println(it.joinToString(", ")) }
}

class Solution {
    // 시간 복잡도 : O(N^2)
    // 공간 복잡도 : O(N^2)
    fun generateMatrix(n: Int): Array<IntArray> {
        val array = Array(n) { IntArray(n) }
        var num = 1

        var top = 0
        var bottom = n - 1
        var left = 0
        var right = n - 1

        while (top <= bottom && left <= right) {
            for (i in left..right) {
                array[top][i] = num
                num++
            }

            top++

            for (i in top..bottom) {
                array[i][right] = num
                num++
            }

            right--

            for (i in right downTo left) {
                array[bottom][i] = num
                num++
            }

            bottom--

            for (i in bottom downTo top) {
                array[i][left] = num
                num++
            }

            left++
        }

        return array
    }
}
