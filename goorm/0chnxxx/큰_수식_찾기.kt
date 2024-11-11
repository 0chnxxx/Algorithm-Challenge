/**
 * 정수와 더하기, 빼기, 곱하기 기호로 이루어진 두 개의 수식 A, B가 주어진다.
 * 주어지는 수식은 모두 올바른 수식이며, 다음 조건들을 만족한다.
 * 1. 수식의 첫 문자와 마지막 문자는 항상 숫자이다.
 * 2. 수의 맨 첫 문자가 0인 경우는 없다.
 * 3. 연산자가 최소 하나 이상 포함되어 있다.
 * 4. 연산자가 붙어서 등장하는 경우는 없다.
 * 5. 수식에 포함된 정수와 수식의 계산 결과는 모두 절댓값으로 10^14 이하이다.
 *
 * 각 수식을 연산자 우선순위에 따라 계산했을 때, 두 수식의 계산 결과 중 더 큰 값을 출력하라.
 * 두 수식의 계산 결과는 항상 다르다.
 *
 * 첫번째 줄에 수식 A, B가 공백을 두고 입력
 * 수식은 숫자와 +, *, - 기호로만 이루어져 있다.
 *
 * 3 <= 수식의 길이 <= 20
 */

import java.util.*

fun main(args: Array<String>) {
    val (a, b) = readLine()!!.split(" ")

    fun calculate(expression: String): Long {
        val numbers = expression.split("+", "-", "*").map { it.toLong() }.toMutableList()
        val operators = expression.filter { it == '+' || it == '-' || it == '*' }.toMutableList()

        var index = 0

        while (index < operators.size) {
            if (operators[index] == '*') {
                numbers[index] = numbers[index] * numbers[index + 1]
                numbers.removeAt(index + 1)
                operators.removeAt(index)
            } else {
                index++
            }
        }

        return numbers.reduceIndexed { index, accumulator, number ->
            when (operators.getOrNull(index - 1)) {
                '+' -> accumulator + number
                '-' -> accumulator - number
                else -> accumulator
            }
        }
    }

    val result = Math.max(calculate(a), calculate(b))

    println(result)
}
