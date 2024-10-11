/**
 * F(n)이 어떤 수열의 n번째 값이라고 하면 다음과 같은 공식이 성립한다.
 *
 * F(1) = 0, F(2) = 1
 * F(n) = F(n-1)+F(n-2), (n > 2)
 *
 * K가 주어지면 F(K) 값을 출력하라.
 * 출력할 땐 F(K)를 1000000007로 나눈 나머지를 출력
 *
 * 첫번째 줄에 K 입력
 * 1 <= K <= 100000
 */

val div = 1000000007

fun main(args: Array<String>) {
    val k = readLine()!!.toInt()

    if (k == 1) {
        println(0)
    } else if (k == 2) {
        println(1)
    } else {
        println(calculate(k))
    }
}

fun calculate(k: Int): Long {
    val dp = LongArray(k + 1)
    dp[1] = 0
    dp[2] = 1

    for (i in 3..k) {
        dp[i] = (dp[i - 1] + dp[i - 2]) % div
    }

    return dp[k]
}
