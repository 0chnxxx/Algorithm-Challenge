/**
 * 지나다니는 길을 'O', 장애물을 'X'로 나타낸 직사각형 격자 모양의 공원에서 로봇 강아지가 산책을 하려 한다.
 * 산책은 로봇 강아지에 미리 입력된 명령에 따라 진행된다.
 * 명령은 ["방향 거리", "방향 거리", ...] 과 같은 형식으로 주어진다.
 *
 * 명령을 수행하기 전에 두 가지를 먼저 확인한다.
 * 1. 주어진 방향으로 이동할 때 공원을 벗어나는지 확인한다.
 * 2. 주어진 방향으로 이동 중 장애물을 만나는지 확인한다.
 * 위 두 가지중 어느 하나라도 해당된다면 로봇 강아지는 해당 명령을 무시하고 다음 명령을 수행한다.
 *
 * 공원의 가로 길이가 W, 세로 길이가 H라고 할 때 공원 좌측 상단의 좌표는 (0, 0), 우측 하단의 좌표는 (H - 1, W - 1)이다.
 *
 * 공원을 나타내는 문자열 배열 park
 * 로봇 강아지가 수행할 명령이 담긴 문자열 배열 routes
 *
 * 로봇 강아지가 모든 명령을 수행 후 놓인 위치를 [세로 방향 좌표, 가로 방향 좌표] 순으로 배열에 담아 return하라.
 *
 * 3 <= park의 길이 <= 50
 * 3 <= park[i]의 길이 <= 50
 * S는 시작 지점, O는 이동 가능한 통로, X는 장애물
 * park는 직사각형 모양이다.
 *
 * 1 <= routes의 길이 <= 50
 * routes[i]는 "op n"과 같은 구조로 이루어져 있다.
 * op는 이동할 방향, n은 이동할 칸의 수를 의미한다.
 * N은 북쪽, S는 남쪽, W는 서쪽, E는 동쪽
 * 1 <= n <= 9
 */

class Solution {
    fun solution(park: Array<String>, routes: Array<String>): IntArray {
        var location = intArrayOf(0, 0)

        for (i in 0 until park.size) {
            for (j in 0 until park[i].length) {
                if (park[i][j] == 'S') {
                    location[0] = i
                    location[1] = j
                    break
                }
            }
        }

        val directions = mapOf(
            "N" to intArrayOf(-1, 0),
            "S" to intArrayOf(1, 0),
            "W" to intArrayOf(0, -1),
            "E" to intArrayOf(0, 1)
        )

        for (route in routes) {
            val (op, nStr) = route.split(" ")
            val n = nStr.toInt()
            val dir = directions[op] ?: continue

            var newRow = location[0]
            var newCol = location[1]
            var validMove = true

            for (step in 1..n) {
                newRow += dir[0]
                newCol += dir[1]

                if (newRow !in park.indices || newCol !in park[0].indices || park[newRow][newCol] == 'X') {
                    validMove = false
                    break
                }
            }

            if (validMove) {
                location[0] = newRow
                location[1] = newCol
            }
        }

        return location
    }
}

fun main(args: Array<String>) {
    println(Solution().solution(
        arrayOf("SOO","OOO","OOO"),
        arrayOf("E 2","S 2","W 1")
    ).joinToString(", "))
    println(Solution().solution(
        arrayOf("SOO","OXX","OOO"),
        arrayOf("E 2","S 2","W 1")
    ).joinToString(", "))
    println(Solution().solution(
        arrayOf("OSO","OOO","OXO","OOO"),
        arrayOf("E 2","S 3","W 1")
    ).joinToString(", "))
}
