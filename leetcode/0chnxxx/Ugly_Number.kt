/**
 * An ugly number is a positive integer which does not have a prime factor other than 2, 3, and 5.
 * Given an integer n, return true if n is an ugly number.
 *
 * Constraints:
 * -2^31 <= n <= 2^31 - 1
 */

fun main() {
    val n = 6

    val result = Solution().isUgly(n)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(log N)
    // 공간 복잡도 : O(1)
    fun isUgly(n: Int): Boolean {
        if (n <= 0) return false

        var num = n
        val primes = listOf(2, 3, 5)

        for (prime in primes) {
            while (num % prime == 0) {
                num /= prime
            }
        }

        return num == 1
    }
}