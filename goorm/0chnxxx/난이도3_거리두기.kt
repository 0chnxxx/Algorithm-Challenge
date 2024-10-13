/**
 * 카페에 테이블이 N*3 격자 형태로 배열
 * 한 줄에 3개의 테이블이 있고 총 N줄 있음
 *
 * 테이블 간 거리두기를 시행
 * 어떤 사람이 앉아있는 자리에서 앞뒤와 양옆으로 인접한 테이블에는 동시에 사람이 앉을 수 없다.
 * 앉을 수 없는 자리에 스티커를 붙이기로 했다.
 * 스티커 개수의 제한은 없으며 스티커를 안 붙일 수도 있다.
 *
 * 경우의 수를 100000007로 나눈 나머지를 출력
 *
 * 첫번째 줄에 테이블 줄 수 N 입력
 *
 * 1 <= N <= 100000
 */

const val MOD = 100000007

fun main(args: Array<String>) {
    val length = readLine()!!.toInt()
    val states = 5
    val dp = Array(length + 1) { LongArray(states) }

    dp[1][0] = 1
    dp[1][1] = 1
    dp[1][2] = 1
    dp[1][3] = 1
    dp[1][4] = 1

    for (i in 2..length) {
        dp[i][0] = (dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2] + dp[i - 1][3] + dp[i - 1][4]) % MOD
        dp[i][1] = (dp[i - 1][0] + dp[i - 1][2] + dp[i - 1][3]) % MOD
        dp[i][2] = (dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][3] + dp[i - 1][4]) % MOD
        dp[i][3] = (dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2]) % MOD
        dp[i][4] = (dp[i - 1][0] + dp[i - 1][2]) % MOD
    }

    val result = (dp[length][0] + dp[length][1] + dp[length][2] + dp[length][3] + dp[length][4]) % MOD

    println(result)
}
