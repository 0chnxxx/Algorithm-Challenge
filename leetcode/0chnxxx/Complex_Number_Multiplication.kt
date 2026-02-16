/**
 * A complex number can be represented as a string on the form "real+imaginaryi" where:
 * real is the real part and is an integer in the range [-100, 100].
 * imaginary is the imaginary part and is an integer in the range [-100, 100].
 * i2 == -1.
 * Given two complex numbers num1 and num2 as strings, return a string of the complex number that represents their multiplications.
 *
 * Constraints:
 * num1 and num2 are valid complex numbers.
 */

fun main() {
    val num1 = "1+1i"
    val num2 = "1+1i"

    val result = Solution().complexNumberMultiply(num1, num2)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun complexNumberMultiply(num1: String, num2: String): String {
        fun parse(num: String): Pair<Int, Int> {
            val plusIndex = num.indexOf('+')
            val real = num.substring(0, plusIndex).toInt()
            val imaginary = num.substring(plusIndex + 1, num.length - 1).toInt()
            return Pair(real, imaginary)
        }

        val (a, b) = parse(num1)
        val (c, d) = parse(num2)

        val real = a * c - b * d
        val imaginary = a * d + b * c

        return "${real}+${imaginary}i"
    }
}
