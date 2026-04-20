/**
 * An image smoother is a filter of the size 3 x 3 that can be applied to each cell of an image by rounding down the average of the cell and the eight surrounding cells (i.e., the average of the nine cells in the blue smoother).
 * If one or more of the surrounding cells of a cell is not present, we do not consider it in the average (i.e., the average of the four cells in the red smoother).
 * Given an m x n integer matrix img representing the grayscale of an image, return the image after applying the smoother on each cell of it.
 *
 * Constraints:
 * m == img.length
 * n == img[i].length
 * 1 <= m, n <= 200
 * 0 <= img[i][j] <= 255
 */

fun main() {
    val img = arrayOf(
        intArrayOf(1, 1, 1),
        intArrayOf(1, 0, 1),
        intArrayOf(1, 1, 1)
    )

    val result = Solution().imageSmoother(img)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(M * N)
    // 공간 복잡도 : O(M * N)
    fun imageSmoother(img: Array<IntArray>): Array<IntArray> {
        val m = img.size
        val n = img[0].size
        val result = Array(m) { IntArray(n) }

        val dirs = arrayOf(
            intArrayOf(-1, -1), intArrayOf(-1, 0), intArrayOf(-1, 1),
            intArrayOf(0, -1),  intArrayOf(0, 0),  intArrayOf(0, 1),
            intArrayOf(1, -1),  intArrayOf(1, 0),  intArrayOf(1, 1)
        )

        for (i in 0 until m) {
            for (j in 0 until n) {
                var sum = 0
                var count = 0

                for (d in dirs) {
                    val ni = i + d[0]
                    val nj = j + d[1]

                    if (ni in 0 until m && nj in 0 until n) {
                        sum += img[ni][nj]
                        count++
                    }
                }

                result[i][j] = sum / count
            }
        }

        return result
    }
}