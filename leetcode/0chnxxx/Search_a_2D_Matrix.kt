/**
 * You are given an m x n integer matrix matrix with the following two properties:
 *
 * - Each row is sorted in non-decreasing order.
 * - The first integer of each row is greater than the last integer of the previous row.
 *
 * Given an integer target, return true if target is in matrix or false otherwise.
 *
 * You must write a solution in O(log(m * n)) time complexity.
 *
 * Constraints:
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 100
 * -10^4 <= matrix[i][j], target <= 10^4
 */

fun main() {
    val matrix = arrayOf(
        intArrayOf(1, 3, 5, 7),
        intArrayOf(10, 11, 16, 20),
        intArrayOf(23, 30, 34, 60)
    )
    val target = 3

    val solution = Solution().searchMatrix(matrix, target)

    println(solution)
}

class Solution {
    // 시간 복잡도 : O(log(N * M))
    // 공간 복잡도 : O(1)
    fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
        // 비어있는 matrix인 경우 얼리 리턴
        if (matrix.isEmpty() || matrix[0].isEmpty()) return false

        val n = matrix.size
        val m = matrix[0].size

        // 2차원 배열을 1차원 배열로 가정하여 이진 탐색
        var left = 0
        var right = n * m - 1

        while (left <= right) {
            // 중간값을 구함
            val mid = (left + right) / 2
            val value = matrix[mid / m][mid % m]

            // 중간값을 확인
            when {
                value == target -> return true
                value < target -> left = mid + 1
                else -> right = mid - 1
            }
        }

        return false
    }

//    // 시간 복잡도 : O(N + M)
//    // 공간 복잡도 : O(1)
//    fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
//        var row = intArrayOf()
//        var result = false
//
//        for (i in matrix.indices) {
//            if (matrix[i][0] <= target && target <= matrix[i][matrix[i].lastIndex]) {
//                row = matrix[i]
//                break
//            }
//        }
//
//        if (row.isNotEmpty()) {
//            for (j in row.indices) {
//                if (row[j] == target) {
//                    result = true
//                    break
//                }
//            }
//        }
//
//        return result
//    }
}
