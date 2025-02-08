/**
 * 각 칸마다 S, L 또는 R이 써져 있는 격자가 있습니다.
 * 당신은 이 격자에서 빛을 쏘고자 합니다.
 *
 * 이 격자의 각 칸에는 다음과 같은 특이한 성질이 있습니다.
 * - 빛이 S가 써진 칸에 도달한 경우 직진합니다.
 * - 빛이 L이 써진 칸에 도달한 경우 좌회전을 합니다.
 * - 빛이 R이 써진 칸에 도달한 경우 우회전을 합니다.
 * - 빛이 격자의 끝을 넘어갈 경우 반대쪽 끝으로 다시 돌아옵니다.
 *
 * 당신은 이 격자 내에서 빛이 이동할 수 있는 경로 사이클이 몇 개 있고, 각 사이클의 길이가 얼마인지 알고 싶습니다.
 * 경로 사이클이란 빛이 이동하는 순환 경로를 의미합니다.
 *
 * 격자의 정보를 나타내는 1차원 문자열 배열 grid가 매개변수로 주어집니다.
 * 주어진 격자를 통해 만들어지는 빛의 경로 사이클의 모든 길이들을 배열에 담아 오름차순으로 정렬하여 return 하도록 solution 함수를 완성해주세요.
 *
 * 1 <= grid의 길이 <= 500
 * 1 <= grid의 각 문자열의 길이 <= 500
 * grid의 모든 문자열의 길이는 서로 같습니다.
 * grid의 모든 문자열은 L, R, S 로 이루어져 있습니다.
 */

// 4

fun main() {
    val grid = arrayOf(
        "SL",
        "LR"
    )

    val solution = Solution().solution(grid)

    println(solution.joinToString(", "))
}

class Solution {
    fun solution(grid: Array<String>): IntArray {
        val n = grid.size
        val m = grid[0].length
        val cycles = mutableListOf<Int>()
        val visited = Array(n) { Array(m) { BooleanArray(4) } }

        // 상(0), 하(1), 좌(2), 우(3)
        val dx = arrayOf(-1, 1, 0, 0)
        val dy = arrayOf(0, 0, -1, 1)

        // 모든 격자 칸에 모든 방향으로 빛을 쏨
        for (x in 0 until n) {
            for (y in 0 until m) {
                for (direction in 0..3) {
                    // 방문하지 않은 칸이 있다는 것은 새로운 사이클이 존재한다는 것
                    if (!visited[x][y][direction]) {
                        var newX = x
                        var newY = y
                        var newDirection = direction
                        var length = 0

                        while (!visited[newX][newY][newDirection]) {
                            visited[newX][newY][newDirection] = true
                            length++

                            // 현재 방향과 L, R에 따라 방향을 회전시켜주는 로직
                            when (grid[newX][newY]) {
                                'L' -> newDirection = when (newDirection) {
                                    0 -> 2  // 상 → 좌
                                    1 -> 3  // 하 → 우
                                    2 -> 1  // 좌 → 하
                                    3 -> 0  // 우 → 상
                                    else -> newDirection
                                }
                                'R' -> newDirection = when (newDirection) {
                                    0 -> 3  // 상 → 우
                                    1 -> 2  // 하 → 좌
                                    2 -> 0  // 좌 → 상
                                    3 -> 1  // 우 → 하
                                    else -> newDirection
                                }
                            }

                            // grid의 길이를 더하고 나눠서 나머지를 구하는 것은 (토러스 구조)
                            // 범위 밖으로 나갔을 때 반대편으로 돌아오게끔 처리하는 것
                            newX = (newX + dx[newDirection] + n) % n
                            newY = (newY + dy[newDirection] + m) % m
                        }

                        // 문제 조건에 따라 사이클은 무조건 순환하기 때문에
                        // 따로 도착 조건을 확인할 필요 없이 다 방문했다면 순환한 것
                        cycles.add(length)
                    }
                }
            }
        }

        return cycles.sorted().toIntArray()
    }
}
