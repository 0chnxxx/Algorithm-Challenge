import java.util.*

/**
 * 업무용 소프트웨어를 개발하는 니니즈웍스의 인턴인 앙몬드는 명령어 기반으로 표의 행을 선택, 삭제, 복구하는 프로그램을 작성하는 과제를 맡았습니다.
 *
 * 파란색으로 칠해진 칸은 현재 선택된 행을 나타냅니다.
 * 단, 한 번에 한 행만 선택할 수 있으며, 표의 범위(0행~마지막행)를 벗어날 수 없습니다.
 * 이때, 다음과 같은 명령어를 이용하여 표를 편집합니다.
 * - "U X" : 현재 선택된 행에서 X칸 위에 있는 행을 선택합니다.
 * - "D X" : 현재 선택된 행에서 X칸 아래에 있는 행을 선택합니다.
 * - "C" : 현재 선택된 행을 삭제한 후, 바로 아래 행을 선택합니다. 단, 삭제된 행이 가장 마지막 행인 경우 바로 윗 행을 선택합니다.
 * - "Z" : 가장 최근에 삭제된 행을 원래대로 복구합니다. 단, 현재 선택된 행은 바뀌지 않습니다.
 *
 * 처음 표의 행 개수를 나타내는 정수 n
 * 처음에 선택된 행의 위치를 나타내는 정수 k
 * 수행한 명령어들이 담긴 문자열 배열 cmd
 * 모든 명령어를 수행한 후 표의 상태와 처음 주어진 표의 상태를 비교하여 삭제되지 않은 행은 O, 삭제된 행은 X로 표시하여 문자열 형태로 return하도록 solution함수를 완성해주세요.
 *
 * 5 <= n <= 1000000
 * 0 <= k < n
 * 1 <= cmd의 원소 개수 <= 200000
 * 1 <= X <= 300000
 */

fun main() {
    val n = 8
    val k = 2
    val cmd = arrayOf(
        "D 2",
        "C",
        "U 3",
        "C",
        "D 4",
        "C",
        "U 2",
        "Z",
        "Z"
    )

    val solution = Solution().solution(n, k, cmd)

    println(solution)
}

class Solution {
    fun solution(n: Int, k: Int, cmd: Array<String>): String {
        val result = MutableList(n) { 'O' }
        val stack = Stack<Triple<Int, Int, Int>>()
        val prev = IntArray(n) { it - 1 }
        val next = IntArray(n) { it + 1 }

        next[n - 1] = -1

        var current = k

        for (command in cmd) {
            when {
                command.startsWith("D") -> {
                    val value = command.split(" ")[1].toInt()

                    repeat(value) {
                        current = next[current]
                    }
                }
                command.startsWith("U") -> {
                    val value = command.split(" ")[1].toInt()

                    repeat(value) {
                        current = prev[current]
                    }
                }
                command == "C" -> {
                    result[current] = 'X'

                    stack.push(Triple(current, prev[current], next[current]))

                    if (prev[current] != -1) next[prev[current]] = next[current]
                    if (next[current] != -1) prev[next[current]] = prev[current]

                    current = if (next[current] != -1) next[current] else prev[current]
                }
                command == "Z" -> {
                    val (index, prevIndex, nextIndex) = stack.pop()

                    result[index] = 'O'

                    if (prevIndex != -1) next[prevIndex] = index
                    if (nextIndex != -1) prev[nextIndex] = index
                }
            }
        }

        return result.joinToString("")
    }
}
