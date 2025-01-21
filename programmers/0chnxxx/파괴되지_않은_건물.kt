/**
 * N * N 크기의 행렬 모양의 게임 맵이 있습니다.
 * 이 맵에는 내구도를 가진 건물이 각 칸마다 하나씩 있습니다.
 * 적은 이 건물들을 공격하여 파괴하려고 합니다.
 * 건물은 적의 공격을 받으면 내구도가 감소하고 내구도가 0이하가 되면 파괴됩니다.
 * 반대로, 아군은 회복 스킬을 사용하여 건물들의 내구도를 높이려고 합니다.
 *
 * 적의 공격과 아군의 회복 스킬은 항상 직사각형 모양입니다.
 * 예를 들어, 크기가 4 * 5인 맵에 내구도가 5인 건물들이 있는 상태입니다.
 * (5, 5, 5, 5, 5)
 * (5, 5, 5, 5, 5)
 * (5, 5, 5, 5, 5)
 * (5, 5, 5, 5, 5)
 * 첫 번째로 적이 맵의 (0, 0)부터 (3, 4)까지 공격하여 4만큼 건물의 내구도를 낮추면 아래와 같은 상태가 됩니다.
 * (1, 1, 1, 1, 1)
 * (1, 1, 1, 1, 1)
 * (1, 1, 1, 1, 1)
 * (1, 1, 1, 1, 1)
 * 두 번째로 적이 맵의 (2, 0)부터 (2, 3)까지 공격하여 2만큼의 건물 내구도를 낮추면 아래와 같이 4개의 건물이 파괴되는 상태가 됩니다.
 * (1, 1, 1, 1, 1)
 * (1, 1, 1, 1, 1)
 * (-1, -1, -1, -1, 1)
 * (1, 1, 1, 1, 1)
 * 세 번째로 아군이 맵의 (1, 0)부터 (3, 1)까지 회복하여 2만큼 건물의 내구도를 높이면 아래와 같이 2개의 건물이 파괴되었다가 복구되고 2개의 건물만 파괴되어 있는 상태가 됩니다.
 * (1, 1, 1, 1, 1)
 * (3, 3, 1, 1, 1)
 * (1, 1, -1, -1, 1)
 * (3, 3, 1, 1, 1)
 * 마지막으로 적이 맵의 (0, 1)부터 (3, 3)까지 공격하여 1만큼 건물의 내구도를 낮추면 아래와 같이 8개의 건물이 더 파괴되어 총 10개의 건물이 파괴된 상태가 됩니다.
 * (내구도가 0 이하가 된 이미 파괴된 건물도 공격을 받으면 계속해서 내구도가 하락하는 것에 유의해주세요.)
 * (1, 0, 0, 0, 1)
 * (3, 2, 0, 0, 1)
 * (1, 0, -2, -2, 1)
 * (3, 2, 0, 0, 1)
 *
 * 최종적으로 총 10개의 건물이 파괴되지 않았습니다.
 *
 * 건물의 내구도를 나타내는 2차원 정수 배열 board
 * 적의 공격 혹은 아군의 회복 스킬을 나타내는 2차원 정수 배열 skill
 *
 * 적의 공격 혹은 아군의 회복 스킬이 모두 끝난 뒤 파괴되지 않은 건물의 개수를 return하는 solution 함수를 완성해주세요.
 *
 * 1 <= board의 행의 길이 <= 1000
 * 1 <= board의 열의 길이 <= 1000
 * 1 <= board의 원소(내구도) <= 1000
 * 1 <= skill의 행의 길이 <= 250000
 * skill의 열의 길이 = 6
 * 1 <= degree <= 100
 *
 * skill의 각 행은 [type, r1, c1, r2, c2, degree] 형태를 가지고 있습니다.
 * type은 1 (적의 공격) 혹은 2 (아군의 회복 스킬) 입니다.
 * (r1, c1) 부터 (r2, c2) 까지는 skill의 범위입니다.
 * degree는 내구도를 낮추거나 높이는 수치입니다.
 * 건물의 내구도는 1 이상이면 파괴되지 않은 상태입니다.
 */

fun main() {
    val board = arrayOf(
        intArrayOf(5, 5, 5, 5, 5),
        intArrayOf(5, 5, 5, 5, 5),
        intArrayOf(5, 5, 5, 5, 5),
        intArrayOf(5, 5, 5, 5, 5),
    )
    val skill = arrayOf(
        intArrayOf(1, 0, 0, 3, 4, 4),
        intArrayOf(1, 2, 0, 2, 3, 2),
        intArrayOf(2, 1, 0, 3, 1, 2),
        intArrayOf(1, 0, 1, 3, 3, 1)
    )

    val solution = Solution().solution(board, skill)

    println(solution)
}

class Solution {
    fun solution(board: Array<IntArray>, skill: Array<IntArray>): Int {
        val damage = Array(board.size + 1) { IntArray(board[0].size + 1) }

        for (s in skill) {
            val type = s[0]
            val r1 = s[1]
            val c1 = s[2]
            val r2 = s[3]
            val c2 = s[4]
            val degree = if (type == 1) -s[5] else s[5]

            damage[r1][c1] += degree
            damage[r1][c2 + 1] -= degree
            damage[r2 + 1][c1] -= degree
            damage[r2 + 1][c2 + 1] += degree
        }

        for (i in 0 until board.size) {
            for (j in 1 until board[0].size) {
                damage[i][j] += damage[i][j - 1]
            }
        }

        for (j in 0 until board[0].size) {
            for (i in 1 until board.size) {
                damage[i][j] += damage[i - 1][j]
            }
        }

        var count = 0

        for (i in 0 until board.size) {
            for (j in 0 until board[0].size) {
                board[i][j] += damage[i][j]

                if (board[i][j] > 0) {
                    count++
                }
            }
        }

        return count
    }
}
