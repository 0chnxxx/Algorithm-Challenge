/**
 * 구름스퀘어에 N개의 행사가 예정되어 있다.
 * i번째 행사는 시작 시간 Si와 종료시간 Ei까지 진행하려고 한다.
 * 행사끼리 진행하는 시간이 서로 겹치지 않게 가장 많은 행사를 여는 것이 목표이다.
 *
 * 행사를 한 번 시작하면 중간에 종료할 수 없다.
 * 행사가 종료된 후 바로 다음 행사를 진행할 수 없고 최소 1의 시간이 지난 뒤에 다른 행사가 시작할 수 있다.
 * 행사의 시작 시간과 종료 시간이 동일한 경우도 있으며 이는 시작하자마자 종료된 행사라고 할 수 있다.
 *
 * 구름스퀘어에서 열릴 수 있는 행사의 최대 개수를 구하여라.
 *
 * 첫번째 줄에 행사의 갯수 N 입력
 * 다음 N개 줄에는 행사의 시작 시간과 종료 시간을 나타내는 S1, Ei가 공백을 두고 입력
 *
 * 1 <= N <= 200000
 * 1 <= Si <= Ei <= 10^9
 */

fun main(args: Array<String>) {
    val n = readLine()!!.toInt()
    val schedules = mutableListOf<Pair<Int, Int>>()

    for (i in 0 until n) {
        val (s, e) = readLine()!!.split(" ").map(String::toInt)
        schedules.add(Pair(s, e))
    }

    schedules.sortBy { it.second }

    var count = 0
    var lastEnd = -1

    for ((s, e) in schedules) {
        if (s >= lastEnd + 1) {
            count++
            lastEnd = e
        }
    }

    println(count)
}
