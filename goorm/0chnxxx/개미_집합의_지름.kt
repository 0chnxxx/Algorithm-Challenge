/**
 * 수직선 위에 개미 N마리가 있다.
 * 개미 집합의 지름이란 개미 집합에 들어있는 임의의 두 개미 사이의 거리 중 가장 긴 거리를 뜻한다.
 * 예를 들어, [1, 3, 2, 1] 이라고 하면 이 네 마리가 이루는 개미 집합의 지름은 2가 된다.
 *
 * 개미 일부를 최소한으로 제거하여 개미 집합의 지름을 D 이하로 만드려고 한다.
 * 제거해야 하는 개미의 최소 수를 구하여라.
 *
 * 첫번째 줄에 N, D 입력
 * 두번째 줄에 각 개미의 좌표 P1, ..., Pn이 입력
 *
 * 1 <= N <= 10^5
 * 0 <= D <= 10^6
 * 1 <= Pi <= 10^6
 */

fun main(args: Array<String>) {
    val (n, d) = readLine()!!.split(" ").map { it.toInt() }
    val p = readLine()!!.split(" ").map { it.toInt() }.sorted()

    var left = 0
    var max = 0

    for (right in 0 until p.size) {
        while (p[right] - p[left] > d) {
            left++
        }

        max = maxOf(max, right - left + 1)
    }

    val kill = n - max

    println(kill)
}
