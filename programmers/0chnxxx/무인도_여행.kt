/**
 * 메리는 여름을 맞아 무인도로 여행을 가기 위해 지도를 보고 있습니다.
 * 지도에는 바다와 무인도들에 대한 정보가 표시돼 있습니다.
 * 지도는 1 * 1 크기의 사각형들로 이루어진 직사각형 격자 형태이며, 격자의 각 칸에는 'X' 또는 1에서 9 사이의 자연수가 적혀있습니다.
 * 지도의 'X'는 바다를 나타내며, 숫자는 무인도를 나타냅니다.
 * 이때, 상하좌우로 연결되는 땅들은 하나의 무인도를 이룹니다.
 * 지도의 각 칸에 적힌 숫자는 식량을 나타내는데, 상하좌우로 연결되는 칸에 적힌 숫자를 모두 합한 값은 해당 무인도에서 최대 며칠동안 머물 수 있는지를 나타냅니다.
 * 어떤 섬으로 놀러갈지 못 정한 메리는 우선 각 섬에서 최대 며칠씩 머물 수 있는지 알아본 후 놀러갈 섬을 결정하려합니다.
 *
 * 지도를 나타내는 문자열 배열 maps가 매개변수로 주어질 때
 * 각 섬에서 최대 며칠씩 머무를 수 있는지 배열에 오름차순으로 담아 return하는 solution 함수를 완성해주세요.
 * 만약 지낼 수 있는 무인도가 없다면 -1을 배열에 담아 return 해주세요.
 *
 * 3 <= maps의 길이 <= 100
 * 3 <= maps[i]의 길이 <= 100
 */

fun main() {
    val maps = arrayOf("X591X", "X1X5X", "X231X", "1XXX1")

    val result = Solution().solution(maps)

    println(result.joinToString(" "))
}

class Solution {
    fun solution(maps: Array<String>): IntArray {
        val n = maps.size
        val m = maps[0].length
        val visited = Array(n) { BooleanArray(m) }
        val directions = listOf(Pair(-1, 0), Pair(1, 0), Pair(0, -1), Pair(0, 1))

        fun isValid(x: Int, y: Int): Boolean {
            return x in 0 until n && y in 0 until m && maps[x][y] != 'X' && !visited[x][y]
        }

        fun dfs(x: Int, y: Int): Int {
            var leave = maps[x][y].toString().toInt()

            visited[x][y] = true

            for ((dx, dy) in directions) {
                val nx = x + dx
                val ny = y + dy

                if (isValid(nx, ny)) {
                    leave += dfs(nx, ny)
                }
            }

            return leave
        }

        val result = mutableListOf<Int>()

        for (i in 0 until n) {
            for (j in 0 until m) {
                if (maps[i][j] != 'X' && !visited[i][j]) {
                    val leave = dfs(i, j)

                    result.add(leave)
                }
            }
        }

        return if (result.isEmpty()) {
            intArrayOf(-1)
        } else {
            result.sorted().toIntArray()
        }
    }
}
