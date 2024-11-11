/**
 * N개의 요소를 가지고 있는 정수 배열 A가 있다.
 * 배열의 i번째 수를 Ai라고 표현한다.
 * 정수 배열 A를 M배 배열로 만드려고 한다.
 * M배 배열은 정수 배열의 모든 요소가 M으로 나누어 떨어지면 M배 배열이라고 한다.
 * 배열의 요소 중에 M으로 나누어 지지 않는 정수는 M을 곱하고, 나누어지는 정수는 그대로 둬서 M배 배열을 만들었다.
 * M배 배열이 된 정수 배열 A를 순서대로 출력하라.
 *
 * 1 <= N <= 100000
 * 1 <= M <= 1000
 * 1 <= Ai <= 100000
 */

fun main(args: Array<String>) {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val array = readLine()!!.split(" ").map { it.toInt() }

    val resultArray = array.map { if (it % m == 0) it else it * m }

    println(resultArray.joinToString(" "))
}
