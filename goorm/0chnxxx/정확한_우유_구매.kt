/**
 * 구름이는 우유 2팩을 사오라는 부모님의 심부름을 받아서, 돈 K원을 받아 마트에 왔다.
 * 마트에는 N팩의 우유가 있으며, 우유마다 1번부터 N번까지 차례대로 번호가 부여되어 있다.
 * 이때, i번 우유의 가격은 Pi이며, 모든 우유의 가격은 서로 다르다.
 * 구름이는 거스름돈이 생기는 것을 꺼려하기 때문에, 가격의 합이 K원이 되게끔 서로 다른 우유 2팩을 고르려고 한다.
 * 우유의 가격이 주어질 때, 가격의 합이 K원이 되게끔 서로 다른 우유 2팩을 고르는 경우의 수를 알아보자.
 *
 * 첫번째 줄에 우유의 종류 수 N, 받은 돈 K가 공백을 두고 입력
 * 두번째 줄에 P1, P2, ..., Pn이 공백을 두고 입력
 * Pi는 i번 우유의 가격을 의미한다.
 *
 * 2 <= N <= 100000
 * 2 <= K <= 2 * 10^8
 * 1 <= Pi <= 10^8
 */

fun main(args: Array<String>) {
    val (n, k) = readLine()!!.split(" ").map { it.toInt() }
    val prices = readLine()!!.split(" ").map { it.toInt() }.sorted()
    var count = 0
    var left = 0
    var right = n - 1

    while (left < right) {
        val sum = prices[left] + prices[right]

        when {
            sum == k -> {
                count++
                left++
                right--
            }
            sum < k -> {
                left++
            }
            else -> {
                right --
            }
        }
    }

    println(count)
}
