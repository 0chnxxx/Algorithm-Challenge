/**
 * Given a positive integer n, find the smallest integer which has exactly the same digits existing in the integer n and is greater in value than n. If no such positive integer exists, return -1.
 * Note that the returned integer should fit in 32-bit integer, if there is a valid answer but it does not fit in 32-bit integer, return -1.
 *
 * Constraints:
 * 1 <= n <= 2^31 - 1
 */

fun main() {
    val n = 12

    val result = Solution().nextGreaterElement(n)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(1)
    // 공간 복잡도 : O(1)
    fun nextGreaterElement(n: Int): Int {
        val digits = n.toString().toCharArray()

        var i = digits.size - 2

        while (i >= 0 && digits[i] >= digits[i + 1]) {
            i--
        }

        if (i < 0) return -1

        var j = digits.size - 1

        while (digits[j] <= digits[i]) {
            j--
        }

        val temp = digits[i]
        digits[i] = digits[j]
        digits[j] = temp

        digits.reverse(i + 1, digits.size)

        return try {
            val result = String(digits).toLong()

            if (result > Int.MAX_VALUE) - 1 else result.toInt()
        } catch (e : Exception) {
            -1
        }
    }
}