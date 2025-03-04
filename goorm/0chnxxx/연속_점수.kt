/**
 * 구름이는 N개의 문제를 순서대로 풀고 있다.
 * 문제는 1번부터 N번 문제까지 있고, 문제를 맞추면 얻을 수 있는 점수가 있다.
 * i번 문제를 맞추고 얻을 수 있는 점수를 Si라고 한다.
 *
 * 이때 문제를 풀어서 점수를 얻을 수 있는 방법은 2가지가 있다.
 * - i번 문제를 해결하고 Si만큼의 점수를 받지만, 한 문제만 풀 수 있다.
 * - 푼 문제의 번호도 연속하고 푼 문제로 얻을 수 있는 점수도 연속적으로 1씩 증가할 때, 푼 문제의 점수의 합한 값을 점수로 받지만, 연속되지 않은 문제를 풀면 점수를 얻을 수 없다.
 * - 2개의 방법 중 더 높은 점수를 얻는다.
 *
 * 구름이는 문제를 풀어서 최고 점수를 얻고 싶어 한다.
 * 하지만, 모든 문제를 풀 시간은 없기 때문에 최소한의 문제를 풀어서 최고 점수를 얻고자 한다.
 * 구름이가 얻을 수 있는 최고 점수를 출력하시오.
 *
 * 1 <= N <= 10000
 * 1 <= Si <= 20000
 */

fun main(args: Array<String>) {
    val n = readLine()!!.toInt()
    val s = readLine()!!.split(" ").map { it.toInt() }

    var max = s[0]
    var currentScore = s[0]

    for (i in 1 until n) {
        if (s[i] == s[i - 1] + 1) {
            currentScore += s[i]
        } else {
            currentScore = s[i]
        }

        max = maxOf(max, currentScore)
    }

    println(max)
}
