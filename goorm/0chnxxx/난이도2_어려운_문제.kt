import java.math.BigDecimal

/**
 * N이 주어졌을 때 주어진 연산 과정에 따라 N!의 결과를 한 자릿수로 바꾼 결과를 출력
 *
 * 0! = 1
 * N! = N * (N - 1)!
 * 단, 1 <= N 이어야함
 *
 * 연산 과정
 * 1. 팩토리얼 연산을 통해서 만들어진 결과값을 A
 * 2. A가 한자리수면 연산 과정을 종료
 * 3. 그렇지 않다면 A의 모든 자릿수를 더한 새로운 값 B
 * 4. A를 B로 교체하고 2번 과정으로
 *
 * 0 <= N <= 10^4
 */

fun main(args: Array<String>) {
    val number = readLine()!!.toBigDecimal()
    var factorialNumber = factorial(number).toString()

    while (factorialNumber.length > 1) {
        factorialNumber = combineNumber(factorialNumber)
    }

    println(factorialNumber)
}

private fun factorial(number: BigDecimal): BigDecimal {
    return if (number <= BigDecimal(1)) BigDecimal(1) else number.multiply(factorial(number - BigDecimal(1)))
}

private fun combineNumber(numbers: String): String {
    var combinedNumber = 0

    for (number in numbers) {
        combinedNumber += Integer.valueOf(number.toString())
    }

    return combinedNumber.toString()
}
