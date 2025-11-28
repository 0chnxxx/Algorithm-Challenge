/**
 * There is an m x n rectangular island that borders both the Pacific Ocean and Atlantic Ocean.
 * The Pacific Ocean touches the island's left and top edges, and the Atlantic Ocean touches the island's right and bottom edges.
 * The island is partitioned into a grid of square cells. You are given an m x n integer matrix heights where heights[r][c] represents the height above sea level of the cell at coordinate (r, c).
 * The island receives a lot of rain, and the rain water can flow to neighboring cells directly north, south, east, and west if the neighboring cell's height is less than or equal to the current cell's height.
 * Water can flow from any cell adjacent to an ocean into the ocean.
 * Return a 2D list of grid coordinates result where result[i] = [ri, ci] denotes that rain water can flow from cell (ri, ci) to both the Pacific and Atlantic oceans.
 *
 * Constraints:
 * m == heights.length
 * n == heights[r].length
 * 1 <= m, n <= 200
 * 0 <= heights[r][c] <= 10^5
 */

fun main() {
    val heights = arrayOf(
        intArrayOf(1, 2, 2, 3, 5),
        intArrayOf(3, 2, 3, 4, 4),
        intArrayOf(2, 4, 5, 3, 1),
        intArrayOf(6, 7, 1, 4, 5),
        intArrayOf(5, 1, 1, 2, 4)
    )

    val result = Solution().pacificAtlantic(heights)

    println(result.map { it.joinToString(",") })
}

class Solution {
    // 시간 복잡도 : O(m * n)
    // 공간 복잡도 : O(m * n)
    fun pacificAtlantic(heights: Array<IntArray>): List<List<Int>> {
        val directions = arrayOf(
            intArrayOf(1, 0),
            intArrayOf(-1, 0),
            intArrayOf(0, 1),
            intArrayOf(0, -1)
        )

        val m = heights.size
        val n = heights[0].size

        val pacific = Array(m) { BooleanArray(n) }
        val atlantic = Array(m) { BooleanArray(n) }

        val pacificQueue = ArrayDeque<Pair<Int, Int>>()
        val atlanticQueue = ArrayDeque<Pair<Int, Int>>()

        // 2차원 배열에서의 각 면의 맨 끝부터 BFS 탐색을 위한 큐 삽입
        for (c in 0 until n) {
            if (!pacific[0][c]) {
                pacificQueue.add(0 to c)
                pacific[0][c] = true
            }
        }

        for (r in 0 until m) {
            if (!pacific[r][0]) {
                pacificQueue.add(r to 0)
                pacific[r][0] = true
            }
        }

        for (c in 0 until n) {
            if (!atlantic[m - 1][c]) {
                atlanticQueue.add(m - 1 to c)
                atlantic[m - 1][c] = true
            }
        }

        for (r in 0 until m) {
            if (!atlantic[r][n - 1]) {
                atlanticQueue.add(r to n - 1)
                atlantic[r][n - 1] = true
            }
        }

        // heights가 더 높은 곳들을 찾기 위한 BFS 탐색
        fun bfs(heights: Array<IntArray>, queue: ArrayDeque<Pair<Int, Int>>, visited: Array<BooleanArray>) {
            while (queue.isNotEmpty()) {
                val (r, c) = queue.removeFirst()

                for (d in directions) {
                    val newR = r + d[0]
                    val newC = c + d[1]

                    if (newR !in 0 until m || newC !in 0 until n) continue
                    if (visited[newR][newC]) continue

                    if (heights[newR][newC] >= heights[r][c]) {
                        visited[newR][newC] = true
                        queue.add(newR to newC)
                    }
                }
            }
        }

        bfs(heights, pacificQueue, pacific)
        bfs(heights, atlanticQueue, atlantic)

        // BFS 탐색을 통해 마킹된 곳들을 result에 추가하여 반환
        val result = ArrayList<List<Int>>()

        for (r in 0 until m) {
            for (c in 0 until n) {
                if (pacific[r][c] && atlantic[r][c]) {
                    result.add(listOf(r, c))
                }
            }
        }

        return result
    }
}