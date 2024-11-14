/**
 * 한 변의 길이가 N인 격자 모양의 미로 M이 있다.
 * 이 미로의 r번째 행, c번째 열에 해당하는 칸을 M(r, c)라 한다.
 * 추가로 미로의 각 칸에는 0 이상 3 이하의 정수가 쓰여 있다.
 * 1. 0이면 통과가 가능한 칸이다.
 * 2. 1이면 출발 위치이다.
 * 3. 2이면 탈출 위치이다.
 * 4. 3이면 통과가 불가능한 벽이다.
 * 미로의 초기 위치는 1이 쓰여 있는 칸이다. 그리고 탈출 위치에 도달하는 즉시 미로에서 탈출할 수 있따.
 * 이 미로는 특이하게도 처음에 어떻게 이동할 것인지를 미리 입력해야 한다.
 * 할 수 있는 이동 명령은 U, D, L, R 의 네 가지 종류가 있다.
 * 하지만 미로의 상태에 따라서 주어진 이동 명령대로 움직일 수 없는 경우도 있다.
 * 명령대로 움직일 수 없는 경우 현재 이동 명령을 무시하고 다음 이동 명령부터 다시 순서대로 시행한다.
 * 주어지는 순서대로 이동 명령을 수행하면서 탈출 위치에 도착하면 미로 탈출에 성공한 것이다.
 * 모든 명령을 수행해도 탈출 위치에 방문한 적이 없다면 미로 탈출에 실패한 것으로 본다.
 * 미리 입력한 k개의 이동 명령이 주어졌을 때 미로 탈출에 성공할 수 있는지, 성공할 수 있다면 실제로 몇 번 이동 명령에 따라 움직여야 하는지를 구하여라.
 *
 * 미로 탈출에 성공하면 SUCCESS 와 실제 이동 횟수를 공백을 두고 출력하고, 실패하면 FAIL을 출력하라.
 *
 * 첫번째 줄에 N, K 가 공백을 두고 입력
 * 두번째 줄에 K개의 이동 명령이 공백을 두고 입력
 * 다음 N개 줄에는 각 줄마다 보드 게임의 상태를 나타내는 N개의 정수가 공백을 두고 입력
 *
 * 3 <= N <= 200
 * 1 <= K <= 10000
 */

fun main(args: Array<String>) {
    val (n, k) = readLine()!!.split(" ").map { it.toInt() }
    val commands = readLine()!!.split("")
    val map = Array(n) { IntArray(n) }
    var start = Pair(0, 0)

    for (i in 0 until n) {
        val status = readLine()!!.split(" ").map { it.toInt() }

        for (j in 0 until n) {
            map[i][j] = status[j]

            if (status[j] == 1) {
                start = Pair(i, j)
            }
        }
    }

    var isEscaped = false
    var current = start
    var move = 0

    for (command in commands) {
        if (command == "U" && current.first - 1 in 0 until n && map[current.first - 1][current.second] != 3) {
            current = Pair(current.first - 1, current.second)
            move = if (!isEscaped) move + 1 else move
        }

        if (command == "D" && current.first + 1 in 0 until n && map[current.first + 1][current.second] != 3) {
            current = Pair(current.first + 1, current.second)
            move = if (!isEscaped) move + 1 else move
        }

        if (command == "L" && current.second - 1 in 0 until n && map[current.first][current.second - 1] != 3) {
            current = Pair(current.first, current.second - 1)
            move = if (!isEscaped) move + 1 else move
        }

        if (command == "R" && current.second + 1 in 0 until n && map[current.first][current.second + 1] != 3) {
            current = Pair(current.first, current.second + 1)
            move = if (!isEscaped) move + 1 else move
        }

        if (map[current.first][current.second] == 2) {
            isEscaped = true
        }
    }

    if (isEscaped) {
        println("SUCCESS $move")
    } else {
        println("FAIL")
    }
}
