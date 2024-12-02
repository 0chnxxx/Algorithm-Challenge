/**
 * 구름이는 수직선 모양의 N칸으로 이루어진 주차장의 바닥을 빨강, 초록, 파란색으로 색칠하고 있다.
 * 예술적 감각이 뛰어난 구름이는 주차장의 어떤 칸이 인접한 칸과 색이 다르기를 원한다.
 * 주차장의 첫째 칸은 어떤 색상이든 들어올 수 있다고 할 때, 구름이가 주차장을 색칠할 수 있는 경우의 수를 출력하라.
 * 구름이가 주차장을 칠하는 경우의 수를 100000007로 나눈 나머지를 출력하라.
 *
 * 첫번째 줄에 주차장의 크기 N이 주어진다.
 *
 * 1 <= N <= 10000
 */

import java.util.*

fun main(args: Array<String>) {
    val n = readLine()!!.toInt()
    val mod = 100000007

    if (n == 1) {
        println(3)
        return
    }

    var red = 1
    var green = 1
    var blue = 1

    for (i in 2..n) {
        val newRed = (green + blue) % mod
        val newGreen = (red + blue) % mod
        val newBlue = (red + green) % mod

        red = newRed
        green = newGreen
        blue = newBlue
    }

    val result = (red + green + blue) % mod

    println(result)
}
