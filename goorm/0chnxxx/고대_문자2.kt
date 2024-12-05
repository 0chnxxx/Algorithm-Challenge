/**
 * 구름이는 고고학자로 고대 문자를 연구하는 일을 하고 있다.
 * 어느 날, 두 개의 고대 문자를 발견한 구름이는 이 문자가 중요한 기록임을 깨닫고, 두 문자 사이의 관계를 밝혀내고 싶어 한다.
 * 구름이는 두 문자 사이에 공통된 부분 수열이 있다는 것을 알게 되었고, 이를 분석하기 위해 가장 긴 공통 부분 수열의 크기와 값을 출력하는 프로그램을 작성하려 한다.
 *
 * 첫번째 줄에 문자열 S가 주어진다.
 * 두번째 줄에 문자열 T가 주어진다.
 *
 * 1 <= |S|, |T| <= 500
 *
 * 첫번째 줄에 가장 긴 공통 부분 수열의 크기를 출력한다.
 * 두번째 줄에 가장 긴 공통 부분 수열의 값을 출력한다.
 */

fun main(args: Array<String>) {
    val s = readLine()!!
    val t = readLine()!!
    val dp = Array(s.length + 1) { IntArray(t.length + 1) }

    for (i in 1..s.length) {
        for (j in 1..t.length) {
            dp[i][j] = if (s[i - 1] == t[j - 1]) {
                dp[i - 1][j - 1] + 1
            } else {
                maxOf(dp[i - 1][j], dp[i][j - 1])
            }
        }
    }

    println(dp[s.length][t.length])

    val lcs = StringBuilder()
    var i = s.length
    var j = t.length

    while (i > 0 && j > 0) {
        when {
            s[i - 1] == t[j - 1] -> {
                lcs.append(s[i - 1])
                i--
                j--
            }
            dp[i - 1][j] >= dp[i][j - 1] -> i--
            else -> j--
        }
    }

    println(lcs.reverse().toString())
}
