/**
 * 마트에서 팔고 있는 과일은 N 종류가 한 개씩 있다.
 * 각 과일의 가격은 Pi, 과일을 먹었을 때 포만감은 Ci이다.
 * 과일을 조각 단위로 구매하는 것이 가능하다.
 * 가격이 p인 과일을 조각 단위로 구매하고자 할 경우, 과일을 p개의 조각으로 자른 뒤 몇 개의 조각만 구매할 수 있다.
 * 모든 조각의 가격은 1, 포만감은 Ci/Pi로 동일하다.
 *
 * K만큼의 돈을 가지고 있을 때, 주어진 금액 이내에서 구매한 과일들의 포만감 합이 가장 크게 되도록 과일을 구매하려 한다.
 * 구매한 과일들의 최대 포만감 합을 구하여라.
 *
 * 첫번째 줄에 과일의 개수 N, 돈 K가 공백을 두고 입력
 * 다음 N개의 줄에는 각 과일의 가격 Pi, 포만감 Ci가 공백을 두고 입력
 *
 * 1 <= N <= 1000
 * 1 <= K <= 10^9
 * 1 <= Pi <= 10^9
 * 1 <= Ci <= 10^9
 * Ci는 항상 Pi의 배수이다.
 */

fun main(args: Array<String>) {
    val (n, k) = readLine()!!.split(" ").map { it.toInt() }
    val fruits = mutableListOf<Pair<Int, Int>>()

    for (i in 0 until n) {
        val (p, c) = readLine()!!.split(" ").map { it.toInt() }
        fruits.add(Pair(p, c))
    }

    fruits.sortByDescending { it.second.toDouble() / it.first }

    var remainingMoney = k
    var maxSatisfaction = 0.0

    for ((price, satisfaction) in fruits) {
        if (remainingMoney == 0) {
            break
        }

        if (remainingMoney >= price) {
            maxSatisfaction += satisfaction
            remainingMoney -= price
        } else {
            maxSatisfaction += (remainingMoney.toDouble() / price) * satisfaction
            remainingMoney = 0
        }
    }

    println(maxSatisfaction.toLong())
}
