/**
 * 블록 게임은 2차원 평면 위에 규칙에 따라 블록을 올리는 게임
 *
 * 규칙
 * 1. 가장 처음에는 평면의 (0, 0) 위치에 점수가 1인 블록을 놓는다.
 * 2. 그 다음 블록은 가장 마지막에 놓은 블록의 상하좌우 중 한 곳에 놓는다.
 * 3. 모든 블록의 크기는 1*1로 동일하고 항상 이전 블록에 딱 붙어 있도록 놓는다.
 * 4. i번째로 놓게되는 블록의 점수는 Si이다.
 * 5. 블록을 놓아야 하는 자리에 블록이 겹치는 경우 놓으려는 위치에 블록이 없을 때까지 최근에 놓은 순서대로 블록을 제거하고 놓는다.
 *
 * 게임이 모두 끝난 후 남은 블록들의 점수를 합한 값을 출력
 *
 * 첫번째 줄에 블록을 올려놓은 횟수 N 입력
 * 두번째 줄에 블록을 놓은 방향을 의미하는 길이 N의 문자열 D 입력
 * 세번째 줄에 블록의 점수 입력
 *
 * 1 <= N <= 1000
 * D 는 L R U D 네 개의 문제로 구성
 * 1 <= Si <= 1000
 */

fun main(args: Array<String>) {
    val round = readLine()!!.toInt()
    val directions = readLine()!!.split("").subList(1, round + 1)
    val scores = readLine()!!.split(" ").map { it.toInt() }

    var location = Triple(0, 0, 1)
    val histories = mutableListOf<Triple<Int, Int, Int>>()

    histories.add(location)

    for (i in 0 until round) {
        val score = scores[i]
        val direction = findDirection(location, directions[i], score)

        if (isAlready(histories, direction)) {
            removeBlock(histories, direction)
        }

        histories.add(direction)
        location = direction
    }

    val result = histories.map { it.third }.sum()

    println(result)
}

private fun findDirection(location: Triple<Int, Int, Int>, direction: String, score: Int): Triple<Int, Int, Int> {
    if (direction == "R") {
        return Triple(location.first + 1, location.second, score)
    } else if (direction == "L") {
        return Triple(location.first - 1, location.second, score)
    } else if (direction == "U") {
        return Triple(location.first, location.second - 1, score)
    } else {
        return Triple(location.first, location.second + 1, score)
    }
}

private fun isAlready(histories: MutableList<Triple<Int, Int, Int>>, direction: Triple<Int, Int, Int>): Boolean {
    return histories.any { it.first == direction.first && it.second == direction.second }
}

private fun removeBlock(histories: MutableList<Triple<Int, Int, Int>>, direction: Triple<Int, Int, Int>) {
    for (i in histories.size - 1 downTo 0) {
        val (x, y, _) = histories[i]

        if (direction.first != x || direction.second != y) {
            histories.removeLast()
        } else {
            break
        }
    }

    histories.removeLast()
}
