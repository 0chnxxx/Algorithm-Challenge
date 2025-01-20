/**
 * 고고학자인 튜브는 고대 유적지에서 보물과 유적이 가득할 것으로 추정되는 비밀의 문을 발견하였습니다.
 * 그런데 문을 열려고 살펴보니 특이한 형태의 자물쇠로 잠겨 있었고 문 앞에는 특이한 형태의 열쇠와 함께 자물쇠를 푸는 방법에 대해 다음과 같이 설명해 주는 종이가 발견되었습니다.
 *
 * 잠겨있는 자물쇠는 격자 한 칸의 크기가 1 * 1인 N * N 크기의 정사각 격자 형태이고 특이한 모양의 열쇠는 N * M 크기인 정사각 격자 형태로 되어 있습니다.
 *
 * 자물쇠에는 홈이 파여 있고 열쇠 또한 홈과 돌기 부분이 있습니다.
 * 열쇠는 회전과 이동이 가능하며 열쇠의 돌기 부분을 자물쇠의 홈 부분에 딱 맞게 채우면 자물쇠가 열리게 되는 구조입니다.
 * 자물쇠 영역을 벗어난 부분에 있는 열쇠의 홈과 돌기는 자물쇠를 여는 데 영향을 주지 않지만, 자물쇠 영역 내에서는 열쇠의 돌기 부분과 자물쇠의 홈 부분이 정확히 일치해야 하며 열쇠의 돌기와 자물쇠의 돌기가 만나서는 안됩니다.
 * 또한 자물쇠의 모든 홈을 채워 비어있는 곳이 없어야 자물쇠를 열 수 있습니다.
 *
 * 열쇠를 나타내는 2차원 배열 key와 자물쇠를 나타내는 2차원 배열 lock이 매개변수로 주어질 때
 * 열쇠로 자물쇠를 열 수 있으면 true를 열 수 없으면 false를 return하는 solution 함수를 완성해주세요.
 *
 * 3 <= key의 크기 <= 20
 * 3 <= lock의 크기 <= 20
 * key의 크기 <= lock의 크기
 * 0은 홈 부분, 1은 돌기 부분을 나타냅니다.
 */

fun main() {
    val key = arrayOf(
        intArrayOf(0, 0, 0),
        intArrayOf(1, 0, 0),
        intArrayOf(0, 1, 1)
    )
    val lock = arrayOf(
        intArrayOf(1, 1, 1),
        intArrayOf(1, 1, 0),
        intArrayOf(1, 0, 1)
    )

    val solution = Solution().solution(key, lock)

    println(solution)
}

class Solution {
    fun solution(key: Array<IntArray>, lock: Array<IntArray>): Boolean {
        var rotatedKey = key

        repeat(4) {
            if (unlock(rotatedKey, lock)) {
                return true
            }

            rotatedKey = rotate(rotatedKey)
        }

        return false
    }

    // key를 시계방향으로 90도 회전시키는 함수
    fun rotate(key: Array<IntArray>): Array<IntArray> {
        val rotated = Array(key.size) { IntArray(key.size) }

        for (i in 0 until key.size) {
            for (j in 0 until key.size) {
                rotated[j][key.size - 1 - i] = key[i][j]
            }
        }

        return rotated
    }

    // 자물쇠 열기를 시도하는 함수
    // key의 가장 오른쪽 아래 부분을 lock의 가장 왼쪽 위부터 이동시켜가며 결합시키는 로직
    // 왼쪽 -> 오른쪽으로 먼저 이동하고 위 -> 아래 방향으로 이동
    fun unlock(key: Array<IntArray>, lock: Array<IntArray>): Boolean {
        for (offsetX in -(key.size - 1) until lock.size) {
            for (offsetY in -(key.size - 1) until lock.size) {
                if (check(key, lock, offsetX, offsetY)) {
                    return true
                }
            }
        }

        return false
    }

    // 자물쇠가 열렸는지 확인하는 함수
    fun check(key: Array<IntArray>, lock: Array<IntArray>, offsetX: Int, offsetY: Int): Boolean {
        val copy = lock.map { it.copyOf() }.toTypedArray()

        for (i in 0 until key.size) {
            for (j in 0 until key.size) {
                val x = i + offsetX
                val y = j + offsetY

                if (x in 0 until lock.size && y in 0 until lock.size) {
                    copy[x][y] += key[i][j]

                    // 돌기끼리 맞닿으면 열 수 없음
                    if (copy[x][y] > 1) {
                        return false
                    }
                }
            }
        }

        // 돌기끼리 맞닿지 않으며 모든 홈이 채워지면 열림
        return copy.all { row -> row.all { it == 1 } }
    }
}
