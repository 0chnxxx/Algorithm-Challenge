/**
 * 구름은 코딩 테스트를 처음 준비하는 취업 준비생들을 위한 다양한 서비스와 컨텐츠를 제공하는 서비스이다.
 * 구름에서 제공하는 알고리즘 들을 풀어보려고 계획 중이다.
 * 구름 레벨에는 난이도 1부터 난이도 5까지 문제들을 제공하며, 난이도가 같거나 증가하는 순서대로 문제를 풀 것이다.
 * 문제들의 지문을 보고 살펴본 N개의 문제들을 푸는데 걸릴 예상 시간을 모두 체크해 보았다.
 * 각 문제와 문제 사이에는 휴식 시간이 필요한데 이는 만약 두 문제가 같은 난이도라면 두 문제를 푸는데 걸리는 시간의 차이만큼 필요하고 난이도를 증가시키는 경우 60분의 시간이 필요하다.
 * 즉, 문제를 푸는데 걸리는 시간은 푼 문제의 예상 시간 합과 문제 사이 휴식 시간의 합만큼 걸린다.
 * 각 난이도별로 풀어볼 문제 수를 정하였다.
 * 계획한 문제를 푸는데 필요한 최소 시간을 구하여라.
 *
 * 첫번째 줄에 문제 수 N 입력
 * 두번째 줄에 각 난이도별로 풀어야 하는 문제 수 P1, P2, P3, P4, P5가 공백을 두고 입력
 * 세번째 줄부터 N개 줄에 문제의 난이도 Ki와 푸는데 걸리는 시간 Ti가 공백을 두고 입력
 *
 * 5 <= N <= 1000
 * 1 <= Pi < N (1 <= i <= 5)
 * 1 <= Ki <= 5
 * 1 <= Ti <= 300
 */

import java.util.*

fun main(args: Array<String>) {
    val n = readLine()!!.toInt()
    val counts = readLine()!!.split(" ").map { it.toInt() }
    val tests = mutableListOf<Pair<Int, Int>>()

    repeat(n) {
        val (level, time) = readLine()!!.split(" ").map { it.toInt() }

        tests.add(level to time)
    }

    val sortedTests = tests
        .groupBy { it.first }
        .mapValues { (_, value) ->
            val queue = LinkedList<Int>()

            val sortedValue = value
                .map { it.second }
                .sortedWith(compareBy { it })

            queue.addAll(sortedValue)

            queue
        }

    var result = 0
    var lastTest = 0

    for (i in 1..counts.size) {
        val count = counts[i - 1]

        for (j in 1..count) {
            val tests = sortedTests[i] ?: break
            val test = tests.poll()

            result += test

            if (lastTest != 0) {
                val diff = Math.abs(lastTest - test)

                result += diff
            }

            lastTest = test
        }

        lastTest = 0
        result += 60
    }

    result -= 60

    println(result)
}
