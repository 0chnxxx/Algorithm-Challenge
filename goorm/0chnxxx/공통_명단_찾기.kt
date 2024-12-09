/**
 * 구름이는 구름 행사에 입장 명단과 대기 명단을 정리하고 있다.
 * 입장 명단과 대기 명단에 모두 포함된 사람들은 우선적으로 입장시킬 예정이다.
 *
 * 첫번째 줄에 입장 명단의 수 N, 대기 명단의 수 M이 공백을 두고 입력
 * 두번째 줄부터 N개의 줄에는 입장 명단에 포함된 사람의 이름이 주어진다.
 * N + 2 번째 줄부터 M개의 줄에는 대기 명단에 포함된 사람의 이름이 주어진다.
 *
 * 1 <= N, M <= 100000
 * 이름은 알파벳 소문자로만 이루어지며, 그 길이는 10 이하이다.
 *
 * 첫번째 줄에 입장 명단과 대기 명단에 모두 포함된 사람의 수를 출력
 * 두번째 줄부터 입장 명단과 대기 명단에 모두 포함된 사람의 이름을 사전 순으로 한 줄에 하나씩 출력
 */

fun main(args: Array<String>) {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val entrances = List(n) { readLine()!! }
    val waitings = List(m) { readLine()!! }

    val result = entrances
        .intersect(waitings)
        .sorted()

    println(result.size)
    result.forEach { println(it) }
}
