/**
 * 카드를 모으는 게임을 한다.
 * 카드는 1번부터 N번 카드까지 총 N종의 카드가 있다.
 * 카드 모으기 게임은 M장의 카드들이 순서대로 제공되고, 이 카드를 수집하여 N종의 카드를 모두 모으면 게임에서 승리할 수 있다.
 * 최대한 빨리 게임에서 승리하려고 한다.
 * 게임에서 승리할 수 있는 조건을 충족하기 위해서 최소 몇 장의 카드를 받아야 하는지 구하여라.
 * 만약 M장의 카드를 모두 받아도 모든 종류의 카드를 모을 수 없다면 -1을 출력한다.
 *
 * 첫번째 줄에 N, M 이 공백을 두고 입력
 * 다음 M개 줄에는 제공되는 카드 종류의 번호가 한 줄에 하나씩 입력
 *
 * 1 <= N, M <= 1000000
 */

fun main(args: Array<String>) {
    val (n, m) = readLine()!!.split(" ").map(String::toInt)
    val cards = mutableListOf<Int>()

    repeat(m) {
        val card = readLine()!!.toInt()

        cards.add(card)
    }

    val collectedCards = hashSetOf<Int>()
    var count = 0

    for (card in cards) {
        collectedCards.add(card)
        count++

        if (collectedCards.size == n) {
            println(count)
            return
        }
    }

    println(-1)
}
