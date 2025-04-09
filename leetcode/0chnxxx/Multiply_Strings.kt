/**
 * Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.
 *
 * Note: You must not use any built-in BigInteger library or convert the inputs to integer directly.
 *
 * 1 <= num1.length, num2.length <= 200
 * num1 and num2 consist of digits only.
 * Both num1 and num2 do not contain any leading zero, except the number 0 itself.
 */

fun main() {
    val num1 = "123"
    val num2 = "456"

    val solution = Solution().multiply(num1, num2)

    println(solution)
}

class Solution {
    // 시간 복잡도 : O(N * M)
    // 공간 복잡도 : O(N + M)
    fun multiply(num1: String, num2: String): String {
        val result = IntArray(num1.length + num2.length)

        for (i in num1.indices.reversed()) {
            for (j in num2.indices.reversed()) {
                val carry = i + j
                val current = i + j + 1

                val multiply = num1[i].digitToInt() * num2[j].digitToInt()
                val sum = multiply + result[current]

                result[current] = sum % 10
                result[carry] += sum / 10
            }
        }

        val builder = StringBuilder()

        for (num in result) {
            if (builder.isEmpty() && num == 0) continue

            builder.append(num)
        }

        return if (builder.isEmpty()) "0" else builder.toString()
    }
}
