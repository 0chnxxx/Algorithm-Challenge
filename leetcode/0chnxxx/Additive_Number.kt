import java.math.BigInteger

/**
 * An additive number is a string whose digits can form an additive sequence.
 * A valid additive sequence should contain at least three numbers. Except for the first two numbers, each subsequent number in the sequence must be the sum of the preceding two.
 * Given a string containing only digits, return true if it is an additive number or false otherwise.
 *
 * Note:
 * Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid.
 *
 * Constraints:
 * 1 <= num.length <= 35
 * num consists only of digits.
 *
 * Follow up:
 * How would you handle overflow for very large input integers?
 */

fun main() {
    val input = "112358"

    val result = Solution().isAdditiveNumber(input)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N^4)
    // 공간 복잡도 : O(N)
    fun isAdditiveNumber(num: String): Boolean {
        val n = num.length

        fun isValid(first: String, second: String, remaining: String): Boolean {
            var a = BigInteger(first)
            var b = BigInteger(second)
            var rest = remaining

            while (rest.isNotEmpty()) {
                val sum = a + b
                val sumStr = sum.toString()

                if (!rest.startsWith(sumStr)) {
                    return false
                }

                rest = rest.substring(sumStr.length)

                a = b
                b = sum
            }

            return true
        }

        for (i in 1 until n) {
            for (j in i + 1 until n) {
                val first = num.substring(0, i)
                val second = num.substring(i, j)

                if ((first.length > 1 && first[0] == '0') || (second.length > 1 && second[0] == '0')) {
                    continue
                }

                if (isValid(first, second, num.substring(j))) {
                    return true
                }
            }
        }

        return false
    }
}