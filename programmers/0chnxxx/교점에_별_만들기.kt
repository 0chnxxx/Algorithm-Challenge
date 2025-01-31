/**
 * Ax + By + C = 0 으로 표현할 수 있는 n 개의 직선이 주어질 때, 이 직선의 교점 중 정수 좌표에 별을 그리려 합니다.
 * 정수로 표현되는 교점에 별을 그리고 문자열로 나타내면 별이 그려진 부분은 *, 빈 공간(격자선이 교차하는 지점)은 .으로 표현할 수 있습니다.
 * 격자판은 무한히 넓으니 모든 별을 포함하는 최소한의 크기만 나타냅니다.
 * 직선 A, B, C에 대한 정보가 담긴 배열 line이 매개변수로 주어질 때, 모든 별을 포함하는 최소 사각형을 return 하도록 solution 함수를 완성해주세요.
 *
 * 2 <= line의 세로 길이 <= 1000
 * line의 가로 길이 = 3
 * line의 원소는 [A, B, C] 형태입니다.
 * -100000 <= A, B, C <= 100000
 * 정답은 1000 * 1000 크기 이내에서 표현됩니다.
 */

fun main() {
    val line = arrayOf(
        intArrayOf(2, -1, 4),
        intArrayOf(-2, -1, 4),
        intArrayOf(0, -1, 1),
        intArrayOf(5, -8, -12),
        intArrayOf(5, 8, 12)
    )

    val solution = Solution().solution(line)

    println(solution.joinToString(", "))
}

class Solution {
    fun solution(line: Array<IntArray>): Array<String> {
        val intersects = mutableSetOf<Pair<Int, Int>>()
        val n = line.size

        for (i in 0 until n - 1) {
            for (j in i + 1 until n) {
                val (a1, b1, c1) = line[i]
                val (a2, b2, c2) = line[j]

                // 행렬식의 분모 계산
                val deter = a1.toLong() * b2 - a2.toLong() * b1

                // 행렬식의 분모가 0이면 두 직선이 평행하거나 일치함
                if (deter == 0L) {
                    continue
                }

                // 행렬식의 분자 계산
                val xNumerator = b1.toLong() * c2 - b2.toLong() * c1
                val yNumerator = a2.toLong() * c1 - a1.toLong() * c2

                // 정수 교점인 경우만
                if (xNumerator % deter == 0L && yNumerator % deter == 0L) {
                    val x = (xNumerator / deter).toInt()
                    val y = (yNumerator / deter).toInt()

                    intersects.add(x to y)
                }
            }
        }

        // 교점이 없는 경우 빈 배열 반환
        if (intersects.isEmpty()) {
            return arrayOf("*")
        }

        // 최대, 최소 좌표 구하기
        val minX = intersects.minOf { it.first }
        val maxX = intersects.maxOf { it.first }
        val minY = intersects.minOf { it.second }
        val maxY = intersects.maxOf { it.second }

        // 딱 맞는 격자 생성
        val width = maxX - minX + 1
        val height = maxY - minY + 1

        val array = Array(height) { CharArray(width) { '.' } }

        // 교점 배치
        for ((x, y) in intersects) {
            // 좌표 변환
            val row = maxY - y // 일반 좌표계의 음수 좌표를 배열 인덱스 기준으로 0부터 시작하게끔
            val col = x - minX // 일반 좌표계는 아래에서 위로 증가하기 때문에 배열 인덱스 기준으로 뒤집어줌

            array[row][col] = '*'
        }

        return array.map { String(it) }.toTypedArray()
    }
}
