/**
 * Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.
 * If the fractional part is repeating, enclose the repeating part in parentheses.
 * If multiple answers are possible, return any of them.
 * It is guaranteed that the length of the answer string is less than 104 for all the given inputs.
 *
 * Constraints:
 * -2^31 <= numerator, denominator <= 2^31 - 1
 * denominator != 0
 */

fun main() {
    val numerator = 2
    val denominator = 3

    val result = Solution().fractionToDecimal(numerator, denominator)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(den)
    // 공간 복잡도 : O(den)
    fun fractionToDecimal(numerator: Int, denominator: Int): String {
        if (numerator == 0) return "0"

        val sb = StringBuilder()

        // 부호 처리
        val isNegative = (numerator.toLong() < 0) xor (denominator.toLong() < 0)

        if (isNegative) sb.append("-")

        // 정수 부분
        val num = Math.abs(numerator.toLong())
        val den = Math.abs(denominator.toLong())

        sb.append(num / den)

        var remainder = num % den

        if (remainder == 0L) return sb.toString()

        sb.append(".")

        val map = mutableMapOf<Long, Int>()

        while (remainder != 0L) {
            if (map.containsKey(remainder)) {
                sb.insert(map[remainder]!!, "(")
                sb.append(")")
                break
            }

            map[remainder] = sb.length
            remainder *= 10
            sb.append(remainder / den)
            remainder %= den
        }

        return sb.toString()
    }
}