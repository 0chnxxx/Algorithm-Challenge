/**
 * 빙하가 깨지면서 스노우타운에 떠내려 온 죠르디는 인생 2막을 위해 주택 건축사업에 뛰어들기로 결심하였습니다.
 * 죠르디는 기둥과 보를 이용하여 벽면 구조물을 자동으로 세우는 로봇을 개발할 계획인데, 그에 앞서 로봇의 동작을 시뮬레이션 할 수 있는 프로그램을 만들고 있습니다.
 *
 * 프로그램은 2차원 가상 벽면에 기둥과 보를 이용한 구조물을 설치할 수 있는데, 기둥과 보는 길이가 1인 선분으로 표현되며 다음과 같은 규칙을 가지고 있습니다.
 * - 기둥은 바닥 위에 있거나 보의 한쪽 끝 부분 위에 있거나, 또는 기둥 위에 있어야 합니다.
 * - 보는 한쪽 끝 부분이 기둥 위에 있거나, 또는 양쪽 끝 부분이 다른 보와 동시에 연결되어 있어야 합니다.
 *
 * 단, 바닥은 벽면의 맨 아래 지면을 말합니다.
 * 2차원 벽면은 n * n 크기 정사각 격자 형태이며, 각 격자는 1 * 1 크기입니다.
 * 맨 처음 벽면은 비어있는 상태입니다.
 *
 * 기둥과 보는 격자선의 교차점에 걸치지 않고, 격자 칸의 각 변에 정확히 일치하도록 설치할 수 있습니다.
 * 기둥과 보를 삭제하는 기능도 있는데 기둥과 보를 삭제한 후에 남은 기둥과 보들 또한 위 규칙을 만족해야 합니다.
 * 만약, 작업을 수행한 결과가 조건을 만족하지 않는다면 해당 작업은 무시됩니다.
 *
 * 벽면의 크기 n
 * 기둥과 보를 설치하거나 삭제하는 작업이 순서대로 담긴 2차원 배열 build_frame
 *
 * 모든 명령어를 수행한 후 구조물의 상태를 return 하도록 solution 함수를 완성해주세요.
 *
 * 5 <= n <= 100
 *
 * 1 <= build_frame의 행 길이 <= 1000
 * build_frame의 열 길이 = 4
 *
 * build_frame의 원소는 [x, y, a, b] 형태입니다.
 * x, y는 기둥, 보를 설치 또는 삭제할 교차점의 좌표이며, [가로, 세로] 형태입니다.
 * a는 설치 또는 삭제할 구조물의 종류를 나타내며 0은 기둥, 1은 보를 나타냅니다.
 * b는 구조물을 설치할 지 혹은 삭제할 지를 나타내며 0은 삭제, 1은 설치를 나타냅니다.
 *
 * 구조물은 교차점 좌표를 기준으로 보는 오른쪽 기둥은 위쪽 방향으로 설치 또는 삭제합니다.
 *
 * return하는 배열은 가로 길이가 3인 2차원 배열로, 각 구조물의 좌표를 담고 있어야 합니다.
 * return하는 배열의 원소는 [x, y, a] 형식입니다.
 * x, y는 기둥, 보의 교차점 좌표이며 [가로, 세로] 형태입니다.
 * a는 구조물의 종류를 나타내며 0은 기둥, 1은 보를 나타냅니다.
 * x 좌표 기준으로 오름차순 정렬하며, x 좌표가 같을 경우 y 좌표 기준으로 오름차순 정렬해주세요.
 * x, y 좌표가 모두 같은 경우 기둥이 보보다 앞에 오면 됩니다.
 */

fun main() {
    val n = 5
    val buildFrame = arrayOf(
        intArrayOf(0, 0, 0, 1),
        intArrayOf(2, 0, 0, 1),
        intArrayOf(4, 0, 0, 1),
        intArrayOf(0, 1, 1, 1),
        intArrayOf(1, 1, 1, 1),
        intArrayOf(2, 1, 1, 1),
        intArrayOf(3, 1, 1, 1),
        intArrayOf(2, 0, 0, 0),
        intArrayOf(1, 1, 1, 0),
        intArrayOf(2, 2, 0, 1)
    )

    val solution = Solution().solution(n, buildFrame)

    println(solution.map { it.joinToString(", ") }.joinToString("\n"))
}

class Solution {
    fun solution(n: Int, build_frame: Array<IntArray>): Array<IntArray> {
        val structures = mutableSetOf<Triple<Int, Int, Int>>()

        fun isValid(): Boolean {
            for ((x, y, a) in structures) {
                if (a == 0) { // 기둥인 경우
                    if (y == 0 || // 바닥에 있는지
                        structures.contains(Triple(x, y - 1, 0)) || // 기둥 위에 있는지
                        structures.contains(Triple(x - 1, y, 1)) || // 보의 오른쪽 끝 부분에 있는지
                        structures.contains(Triple(x, y, 1)) // 보의 왼쪽 끝 부분에 있는지
                    ) continue
                    return false
                } else if (a == 1) { // 보인 경우
                    if (structures.contains(Triple(x, y - 1, 0)) || // 보의 왼쪽 부분이 기둥 위에 있는지
                        structures.contains(Triple(x + 1, y - 1, 0)) || // 보의 오른쪽 부분이 기둥 위에 있는지
                        structures.contains(Triple(x - 1, y, 1)) && structures.contains(Triple(x + 1, y, 1)) // 보와 연결되는지
                    ) continue
                    return false
                }
            }

            return true
        }

        for ((x, y, a, b) in build_frame) {
            val structure = Triple(x, y, a)

            // 추가 혹은 삭제
            if (b == 1) {
                structures.add(structure)

                // 유효하지 않으면 롤백
                if (!isValid()) {
                    structures.remove(structure)
                }
            } else {
                structures.remove(structure)

                if (!isValid()) {
                    structures.add(structure)
                }
            }
        }

        // 문제 요구사항대로 정렬
        return structures
            .map { listOf(it.first, it.second, it.third).toIntArray() }
            .sortedWith(compareBy({ it[0] }, { it[1] }, { it[2] }))
            .toTypedArray()
    }
}
