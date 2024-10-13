/**
 * 7%의 소금물 Ng가 있다.
 * 소금물에 Mg만큼의 물을 넣은 후
 * 소금물의 농도를 소수 2번째 자리까지 출력
 *
 * 100 <= N, M <= 100000
 */

fun main(args: Array<String>) {
    val (saltedWater, pureWater) = readLine()!!.split(" ").map { it.toInt() }

    val salt = saltedWater * 0.07
    var newSaltedWater = ((salt / (saltedWater + pureWater)) * 100).toString()

    if (getDecimalPointLength(newSaltedWater) > 2) {
        newSaltedWater = (Math.floor(newSaltedWater.toDouble() * 100) / 100).toString()
    }

    if (getDecimalPointLength(newSaltedWater) == 1) {
        newSaltedWater += "0"
    }

    println(newSaltedWater)
}

private fun getDecimalPointLength(value: String): Int {
    return value.length - value.indexOf(".") - 1
}
