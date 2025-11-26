/**
 * Given two non-negative integers, num1 and num2 represented as string, return the sum of num1 and num2 as a string.
 * You must solve the problem without using any built-in library for handling large integers (such as BigInteger).
 * You must also not convert the inputs to integers directly.
 *
 * Constraints:
 * 1 <= num1.length, num2.length <= 10^4
 * num1 and num2 consist of only digits.
 * num1 and num2 don't have any leading zeros except for the zero itself.
 */

fun main() {
    val num1 = "11"
    val num2 = "123"

    val result = Solution().addStrings(num1, num2)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(max(n, m))
    // 공간 복잡도 : O(max(n, m))
    fun addStrings(num1: String, num2: String): String {
        var i = num1.length - 1
        var j = num2.length - 1
        var carry = 0
        val result = StringBuilder()

        while (i >= 0 || j >= 0 || carry > 0) {
            val n1 = if (i >= 0) num1[i] - '0' else 0
            val n2 = if (j >= 0) num2[j] - '0' else 0

            val sum = n1 + n2 + carry

            result.append(sum % 10)
            carry = sum / 10

            i--
            j--
        }

        return result.reverse().toString()
    }
}