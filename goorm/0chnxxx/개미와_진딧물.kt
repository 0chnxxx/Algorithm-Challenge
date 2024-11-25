/**
 * 한 변의 길이가 N인 정사각형 모양의 땅에 개미와 진딧물이 있다.
 * 개미는 거리 M 이하에 위치한 진딧물에게서 수액을 채취할 수 있다.
 * 이 수액이 없으면 굶주린 개미는 다른 곳으로 먹이를 찾아 떠나게 된다.
 * 이때 거리는 맨해튼 거리이다.
 * 현재 땅에 있는 개미와 진딧물의 위치가 주어졌을 때, 진딧물로부터 수액을 얻을 수 있는 개미의 수를 구해보자.
 *
 * 첫번째 줄에 정사각형의 크기 N과 거리 M이 공백을 두고 입력
 * 다음 N개 줄에는 N개의 숫자가 공백을 두고 입력
 * 숫자는 N * N 땅을 구성하는 순서대로 주어지며, 주어지는 숫자는 0, 1, 2 중 하나이다.
 * 0은 비어있는 위치, 1은 개미가 있는 위치, 2는 진딧물이 있는 위치
 *
 * 1 <= N <= 100
 * 1 <= M <= 10
 */

import kotlin.math.abs

fun main(args: Array<String>) {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val lands = Array(n) { IntArray(n) }
    val feeds = mutableListOf<Pair<Int, Int>>()

    for (i in 0 until n) {
        val status = readLine()!!.split(" ").map { it.toInt() }

        for (j in 0 until n) {
            lands[i][j] = status[j]

            if (lands[i][j] == 2) {
                feeds.add(i to j)
            }
        }
    }

    var count = 0

    for (i in 0 until n) {
        for (j in 0 until n) {
            if (lands[i][j] != 1) {
                continue
            }

            val hasFeed = feeds.any { (x, y) -> abs(i - x) + abs(j - y) <= m }

            if (hasFeed) {
                count++
            }
        }
    }

    println(count)
}
