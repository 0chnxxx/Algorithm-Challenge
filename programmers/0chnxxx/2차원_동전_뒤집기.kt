/**
 * 한수는 직사각형 모양의 공간에 놓인 동전들을 뒤집는 놀이를 하고 있습니다.
 * 모든 동전들은 앞과 뒤가 구분되어 있으며, 동전을 뒤집기 위해서는 같은 줄에 있는 모든 동전을 뒤집어야 합니다.
 * 동전들의 초기 상태와 목표 상태가 주어졌을 때, 초기 상태에서 최소 몇 번의 동전을 뒤집어야 목표 상태가 되는지 알아봅시다.
 *
 * 직사각형 모양의 공간에 놓인 동전들의 초기 상태를 나타내는 2차원 정수 배열 beginning
 * 목표 상태를 나타내는 target
 *
 * 초기 상태에서 목표 상태로 만들기 위해 필요한 동전 뒤집기 횟수의 최솟값을 return하는 solution 함수를 완성하세요.
 * 단, 목표 상태를 만들지 못하는 경우에는 -1을 return 합니다.
 *
 * 1 <= beginning의 길이 = target의 길이 <= 10
 * 1 <= beginning[i]의 길이 = target[i]의 길이 <= 10
 * beginning[i][j]와 target[i][j]는 0 또는 1의 값으로 주어집니다.
 * 0은 동전의 앞면을 1은 동전의 뒷면을 의미합니다.
 */

fun main() {
    val beginning = arrayOf(
        intArrayOf(0, 1, 0, 0, 0),
        intArrayOf(1, 0, 1, 0, 1),
        intArrayOf(0, 1, 1, 1, 0),
        intArrayOf(1, 0, 1, 1, 0),
        intArrayOf(0, 1, 0, 1, 0)
    )
    val target = arrayOf(
        intArrayOf(0, 0, 0, 1, 1),
        intArrayOf(0, 0, 0, 0, 1),
        intArrayOf(0, 0, 1, 0, 1),
        intArrayOf(0, 0, 0, 1, 0),
        intArrayOf(0, 0, 0, 0, 1)
    )

    val solution = Solution().solution(beginning, target)

    println(solution)
}

class Solution {
    fun solution(beginning: Array<IntArray>, target: Array<IntArray>): Int {
        val n = beginning.size
        val m = beginning[0].size

        fun countFlips(isFlipFirstRow: Boolean): Int {
            var count = 0
            val board = Array(n) { i -> beginning[i].copyOf() }

            // 첫번째 행을 뒤집는 경우
            if (isFlipFirstRow) {
                count++

                for (j in 0 until m) {
                    board[0][j] = 1 - board[0][j]
                }
            }

            // 첫번째 행을 기준으로 각 열을 뒤집기
            for (j in 0 until m) {
                if (board[0][j] != target[0][j]) {
                    count++

                    for (i in 0 until n) {
                        board[i][j] = 1 - board[i][j]
                    }
                }
            }

            // 나머지 행 확인해서 뒤집기
            for (i in 1 until n) {
                if (board[i][0] != target[i][0]) {
                    count++

                    for (j in 0 until m) {
                        board[i][j] = 1 - board[i][j]
                    }
                }
            }

            return if (board.contentDeepEquals(target)) count else Int.MAX_VALUE
        }

        val minFlipCount = listOf(countFlips(false), countFlips(true)).minOrNull() ?: Int.MAX_VALUE

        return if (minFlipCount == Int.MAX_VALUE) -1 else minFlipCount
    }
}
