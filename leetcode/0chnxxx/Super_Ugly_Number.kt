/**
 * A super ugly number is a positive integer whose prime factors are in the array primes.
 * Given an integer n and an array of integers primes, return the nth super ugly number.
 * The nth super ugly number is guaranteed to fit in a 32-bit signed integer.
 *
 * Constraints:
 * 1 <= n <= 10^5
 * 1 <= primes.length <= 100
 * 2 <= primes[i] <= 1000
 * primes[i] is guaranteed to be a prime number.
 * All the values of primes are unique and sorted in ascending order.
 */

fun main() {
    val n = 12
    val primes = intArrayOf(2, 7, 13, 19)

    val result = Solution().nthSuperUglyNumber(n, primes)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N * K)
    // 공간 복잡도 : O(N + K)
    fun nthSuperUglyNumber(n: Int, primes: IntArray): Int {
        val ugly = LongArray(n)

        ugly[0] = 1L

        val k = primes.size
        val index = IntArray(k) { 0 }
        val next = LongArray(k) { primes[it].toLong() }

        for (i in 1 until n) {
            val min = next.minOrNull()!!

            ugly[i] = min

            for (j in 0 until k) {
                if (next[j] == min) {
                    index[j]++
                    next[j] = ugly[index[j]] * primes[j]
                }
            }
        }

        return ugly[n - 1].toInt()
    }
}