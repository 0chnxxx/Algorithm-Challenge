/**
 * 플레이어는 GameJam에 참가했다.
 * GameJam은 현장에서 즉석으로 팀을 꾸려 게임의 규칙을 만든 뒤, 생각한 규칙을 코드로 옮겨서 게임을 만들어내는 대회이다.
 * 플레이어가 속한 팀은 보드로 즐길 수 있는 간단한 보드게임을 만들었다.
 *
 * 게임의 진행 방법과 규칙은 다음과 같다.
 * 1. 게임은 한 변의 길이가 N인 격자 보드 위에서 진행한다.
 * 보드는 한 변의 길이가 1인 N^2개의 칸으로 나누어져 있다.
 * 2. 각 칸에는 <<command>>에 해당하는 방향으로 <count>칸만큼 한 칸씩 이동하라는 지시가 적혀있다.
 * 3. 플레이하는 사람은 청므에 보드의 칸 중 하나에 말을 올려놓는다.
 * 4. 각 칸에 적힌 지시대로 말을 이동한다.
 * 만약 이동 중에 보드 밖으로 나가게 된다면 반대쪽의 첫 번째 칸으로 이동한다.
 * 이동 거리가 남아 있다면 계속 이동한다.
 * 5. 만약 이동하다가 자신의 말이 이미 한 번이라도 방문한 칸을 다시 지나야 할 경우에는 게임이 종료된다.
 * 그 외의 경우에는 게임이 종료될 때까지 위의 4번 단계를 반복한다.
 * 6. 게임의 점수는 시작한 칸을 포함하여, 게임이 종료되기 전까지 말이 방문한 서로 다른 칸의 개수이다.
 *
 * 플레이어는 GameJam의 최종 발표 때 게임을 시연해 보기 위해, 구름이와 미리 연습 게임을 진행해 보려고 한다.
 * 플레이어와 구름이가 각각의 게임에서 처음에 말을 올려둔 칸이 주어졌을 때, 두 사람 중 누가 더 높은 점수를 획득했는지를 구해보자.
 * 단 플레이어와 구름이는 별개의 게임이다.
 *
 * 구름이가 게임에서 승리하면 goorm 과 구름이가 얻은 점수를 공백을 두고 출력
 * 플레이어가 승리하면 player 와 플레이어가 얻은 점수를 공백을 두고 출력
 * 비기는 테스트 케이스는 주어지지 않는다.
 *
 * 첫번째 줄에 격자 보드의 크기 N 입력
 * 두번째 줄에 구름이가 말을 올려둔 칸의 좌표 Rg, Cg가 공백을 두고 입력
 * 세번째 줄에 플레이어가 말을 올려둔 칸의 좌표 Rp, Cp가 공백을 두고 입력
 * 다음 N개 줄에 보드의 각 칸에 적혀있는 지시 사항이 주어진다.
 * 각 줄마다 N개의 지시 사항이 <count> <command> 형식으로 공백을 두고 입력
 * i번째 줄에서 j번째로 주어지는 지시 사항은 보드의 r번째 행, c번째 열의 정보를 의미한다.
 * <count>는 이동 횟수, <command>는 이동 방향을 말한다.
 *
 * 3 <= N <= 200
 * 1 <= Rg, Cg, Rp, Cp <= N
 * 1 <= <count> <= N
 * <command>는 U, D, R, L 중 하나이다.
 */

import java.util.*

fun main(args: Array<String>) {
    val n = readLine()!!.toInt()
    val (x1, y1) = readLine()!!.split(" ").map { it.toInt() }
    val (x2, y2) = readLine()!!.split(" ").map { it.toInt() }
    val order = Array(n) { Array(n) { 0 to ' ' } }

    for (i in 0 until n) {
        val orders = readLine()!!.split(" ")

        for (j in 0 until n) {
            val count = orders[j].dropLast(1).toInt()
            val command = orders[j].last()

            order[i][j] = count to command
        }
    }

    val directionX = mapOf(
        'U' to -1,
        'D' to 1,
        'L' to 0,
        'R' to 0
    )

    val directionY = mapOf(
        'U' to 0,
        'D' to 0,
        'L' to -1,
        'R' to 1
    )

    fun play(startX: Int, startY: Int): Int {
        val visited = Array(n) { BooleanArray(n) }
        var x = startX
        var y = startY
        var score = 1

        visited[x - 1][y - 1] = true

        while (true) {
            var (count, command) = order[x - 1][y - 1]

            while (count-- > 0) {
                x += directionX[command]!!
                y += directionY[command]!!

                if (x <= 0) {
                    x = n
                }

                if (y <= 0) {
                    y = n
                }

                if (x > n) {
                    x = 1
                }

                if (y > n) {
                    y = 1
                }

                if (visited[x - 1][y - 1]) {
                    return score
                }

                visited[x - 1][y - 1] = true
                score++
            }
        }
    }

    val goorm = play(x1, y1)
    val player = play(x2, y2)

    if (goorm > player) {
        println("goorm $goorm")
    } else {
        println("player $player")
    }
}
