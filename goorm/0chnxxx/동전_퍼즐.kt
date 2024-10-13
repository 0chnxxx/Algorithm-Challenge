import kotlin.math.min

/**
 * 무한 격자 평면 위에 몇몇 격자점에 동전이 놓여져 있다.
 * 퍼즐의 목표는 최소 개수의 동전을 옮겨서 새로운 모양을 만드는 것이다.
 * 동전을 옮길 때는 한 번에 한 개씩 동전을 들어서 빈 격자점에 놓으면 된다.
 * 회전된 모양이나 대칭된 모양을 만드는 것은 인정되지 않는다.
 *
 * 현재 동전의 배치와 만들어야 하는 동전의 배치가 주어질 때 옮겨야 하는 동전의 개수는 최소 몇 개인가?
 *
 * 첫번째 줄에 H1, W1 입력
 * 다음 H1개의 줄에는 . 은 빈칸 0 은 동전이 있는 칸으로 현재 동전의 배치인 길이 W1의 문자열이 주어진다.
 * 그 다음 줄에 H2, W2 입력
 * 다음 H2개의 줄에는 . 은 빈칸 0은 동전이 있는 칸으로 만들어야 하는 동전의 배치인 길이 W2의 문자열이 주어진다.
 *
 * 1 <= H1, W1 <= 10
 * 1 <= H2, W2 <= 10
 * 두 배치의 동전 개수는 동일하다.
 */

fun main(args: Array<String>) {
    val (h1, w1) = readLine()!!.split(" ").map { it.toInt() }
    val beforeCoins = setCoins(h1, w1)

    val (h2, w2) = readLine()!!.split(" ").map { it.toInt() }
    val afterCoins = setCoins(h2, w2)

    val result = calculateMinCoinsToMove(beforeCoins, afterCoins)

    println(result)
}

fun setCoins(h: Int, w: Int): List<Pair<Int, Int>> {
    val positions = mutableListOf<Pair<Int, Int>>()

    for (i in 0 until h) {
        val coins = readLine()!!.toCharArray()

        for (j in 0 until w) {
            if (coins[j] == 'O') {
                positions.add(Pair(i, j))
            }
        }
    }

    return positions
}

fun calculateMinCoinsToMove(before: List<Pair<Int, Int>>, after: List<Pair<Int, Int>>): Int {
    var minMoves = before.size

    for (i in -10..10) {
        for (j in -10..10) {
            val movedBefore = before.map { (x, y) -> Pair(x + i, y + j) }
            val neededMoves = (movedBefore.toSet() - after.toSet()).size

            minMoves = min(minMoves, neededMoves)
        }
    }

    return minMoves
}
