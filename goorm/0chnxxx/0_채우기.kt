/**
 * 한 변의 길이가 N인 정수가 적힌 정사각형 격자 판을 보고 있다.
 * 격자 판의 i번째 줄, j번째 칸에 적힌 정수는 M(i, j)이다.
 * 격자판 중간에 0이 단 하나 존재한다는 사실을 발견했다.
 * 0을 0의 위치를 기준으로 가로, 세로에 있는 모든 수를 합한 값으로 채우려고 한다.
 * 0 대신에 적을 숫자를 출력하라.
 *
 * 첫번째 줄에 격자 판의 크기 N
 * 그 다음 N줄에 걸쳐 격자 판의 상태
 * 0은 단 한번만 주어진다.
 *
 * 3 <= N <= 100
 * 0 <= M(i, j) <= 1000
 */

fun main(args: Array<String>) {
    val n = readLine()!!.toInt()
    val array = Array(n) { IntArray(n) }
    var location = Pair(0, 0)
    var result = 0

    for (i in 0 until n) {
        val numbers = readLine()!!.split(" ").map { it.toInt() }

        for (j in 0 until n) {
            array[i][j] = numbers[j]

            if (numbers[j] == 0) {
                location = Pair(i, j)
            }
        }
    }

    for (i in 0 until n) {
        result += array[i][location.second]
    }

    for (j in 0 until n) {
        result += array[location.first][j]
    }

    println(result)
}
