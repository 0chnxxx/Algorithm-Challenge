/**
 * 램의 용량은 모두 2의 제곱수를 따른다.
 * 사용하기 위해 꽂았던 임의의 램이 2의 제곱수를 따르지 않는 경우가 있다.
 * 램의 일부분이 파손되어 메모리가 제대로 표현되지 않는 경우이다.
 *
 * 첫번째 줄에 파손된 램의 개수를 출력하고 두번째 줄에 파손된 램의 번호를 공백을 두고 출력하라.
 * 만약 파손된 램이 없다면 첫번째 줄에만 0을 출력하라.
 *
 * 첫번째 줄에 램의 개수 N 입력
 * 두번째 줄에 i번째 램의 용량 Mi가 공백을 두고 N개 입력
 *
 * 1 <= N <= 100
 * 1 <= i <= N, 100 <= Mi <= 2^31 - 1
 */

fun main(args: Array<String>) {
    val n = readLine()!!.toInt()
    val rams = readLine()!!.split(" ").map { it.toInt() }
    val positions = mutableListOf<Int>()

    for (i in 0 until n) {
        val ram = rams[i]

        if ((ram and (ram - 1)) != 0) {
            positions.add(i + 1)
        }
    }

    println(positions.size)
    println(positions.joinToString(" "))
}
