/**
 * 구름이는 구름 사이트의 UXUI 디자이너로 일하게 되었다.
 * 구름이의 첫 업무는 구름 사이트에서 사용자들이 자주 실행하는 이벤트를 정리하는 일이다.
 * 이때 이벤트란 사용자가 웹사이트에서 실행하거나, 클릭한 것을 의미한다.
 * 구름이는 이를 위해서 사용자들이 취할 수 있는 이벤트를 N개로 규정하고, 각각의 이벤트에 1번부터 N번까지 번호를 붙였다.
 * 구름이는 이어서 M명의 사용자가 구름 사이트에서 이벤트를 실행한 내역을 추출하였다.
 *
 * 추출한 정보를 바탕으로 구름이는 사용자들이 가장 자주 실행하는 이벤트들을 알아내고자 한다.
 * 단, 가장 많이 실행한 이벤트가 여러 개라면 각 이벤트를 공백으로 구분하여, 이벤트 번호가 큰 순서에서 작은 순서로 출력하라.
 * 한 사람이 같은 이벤트를 여러 번 실행한 경우에도 중복으로 세어준다.
 *
 * 첫번째 줄에 이벤트의 개수 N, 사용자의 수 M이 공백을 두고 입력
 * 다음 M개 줄에 i번 사용자가 실행한 이벤트의 개수 k가 주어지고, 이어서 실행한 이벤트의 번호 Ei, ..., Ek가 공백을 두고 입력
 *
 * 1 <= N <= 100000
 * 1 <= M <= 1000
 * 1 <= k <= 100
 * 1 <= Ei <= N
 */

fun main(args: Array<String>) {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val count = IntArray(n + 1) { 0 }

    for (i in 0 until m) {
        val events = readLine()!!.split(" ").map { it.toInt() }

        for (j in 0 until events[0]) {
            val event = events[j + 1]

            count[event]++
        }
    }

    val sortedCount = count
        .mapIndexed { index, i -> index to i }
        .sortedWith(compareBy<Pair<Int, Int>> { it.second }.thenByDescending { it.first })

    val max = sortedCount.maxOf { it.second }

    val result = sortedCount
        .filter { it.second == max }
        .map { it.first }
        .joinToString(" ")

    println(result)
}
