/**
 * 1, 5, 10, 20, 40원의 5종류의 동전이 있다.
 * N원을 거슬러 주기 위해 필요한 동전의 최소 개수는 몇개인가?
 *
 * 첫번째 줄에 N 입력
 *
 * 1 <= N <= 10^9
 */

fun main(args: Array<String>) {
    var n = readLine()!!.toInt()
    val coins = listOf(40, 20, 10, 5, 1)

    var count = 0

    for (coin in coins) {
        count += n / coin
        n %= coin
    }

    println(count)
}
