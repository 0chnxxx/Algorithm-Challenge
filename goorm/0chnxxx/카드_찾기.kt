/**
 * 정수가 적힌 N장의 카드가 흩어져 있다.
 * 구름이는 N장의 카드에서 숫자 M이 적힌 카드가 있는지 알고 싶다.
 * 카드들에 어떤 숫자가 적혀 있는지 주어지고, 그 중에서 구름이가 원하는 숫자 M이 포함되어 있는지 확인하는 프로그램을 작성한다.
 *
 * 첫번째 줄에 자연수 N이 입력
 * 두번째 줄에 카드에 적힌 숫자가 공백을 두고 입력
 * 세번째 줄에 확인하고 싶은 숫자 M이 입력
 *
 * 1 <= N <= 10^9
 * -10^9 <= M <= 10^9
 *
 * M이 카드에 있으면 1을 출력하고 없으면 0을 출력한다.
 */

fun main(args: Array<String>) {
    val n = readLine()!!.toInt()
    val cards = readLine()!!.split(" ").map { it.toInt() }
    val m = readLine()!!.toInt()

    if (cards.contains(m)) {
        println(1)
    } else {
        println(0)
    }
}
