/**
 * Write an algorithm to determine if a number n is happy.
 *
 * A happy number is a number defined by the following process:
 * Starting with any positive integer, replace the number by the sum of the squares of its digits.
 * Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
 * Those numbers for which this process ends in 1 are happy.
 * Return true if n is a happy number, and false if not.
 *
 * Constraints:
 * 1 <= n <= 2^31 - 1
 */

fun main() {
    val n = 19

    val result = Solution().isHappy(n)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(log N)
    // 공간 복잡도 : O(1)
    fun isHappy(n: Int): Boolean {
        var num = n
        val seen = mutableSetOf<Int>()

        while (num != 1) {
            if (num in seen) return false

            seen.add(num)

            var sum = 0
            var tmp = num

            while (tmp > 0) {
                val digit = tmp % 10

                sum += digit * digit
                tmp /= 10
            }

            num = sum
        }

        return true
    }
}
