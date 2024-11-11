/**
 * 통증이라는 시스템이 있다.
 * 통증 수치가 높다면 아이템을 적절히 사용해 통증 수치를 0으로 유지해야 한다.
 * 통증 수치를 감소시켜 주는 아이템이 2 종류가 있다.
 * 아이템의 이름은 bandage, medicine이고 각 아이템을 사용 시 A, B만큼 통증 수치를 감소시켜 준다.
 * 각 아이템은 원하는 만큼 획득할 수 있따.
 *
 * 플레이어는 현재 N의 통증 수치를 갖고 있다.
 * 플레이어가 통증 수치를 0으로 줄이기 위해 필요한 아이템의 최소 개수를 구해보자.
 * 단, 사용했을 때 통증 수치가 0보다 작아지는 아이템은 사용할 수 없음에 유의하라.
 * 통증 수치를 0으로 만들 수 없는 경우에는 -1을 출력한다.
 *
 * 첫번째 줄에 통증 수치 N 입력
 * 두번째 줄에 각 아이템의 효과 A, B 공백을 두고 입력
 *
 * 2 <= N <= 10^6
 * 2 <= A <= B <= 13
 * B는 A로 나누어 떨어지지 않는다.
 */

import java.util.*

fun main(args: Array<String>) {
    val n = readLine()!!.toInt()
    val (bandage, medicine) = readLine()!!.split(" ").map { it.toInt() }
    val dp = IntArray(n + 1) { Int.MAX_VALUE }

    dp[0] = 0

    for (i in 1..n) {
        if (i >= bandage && dp[i - bandage] != Int.MAX_VALUE) {
            dp[i] = Math.min(dp[i], dp[i - bandage] + 1)
        }

        if (i >= medicine && dp[i - medicine] != Int.MAX_VALUE) {
            dp[i] = Math.min(dp[i], dp[i - medicine] + 1)
        }
    }

    if (dp[n] == Int.MAX_VALUE) {
        println(-1)
    } else {
        println(dp[n])
    }
}

