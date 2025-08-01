/**
 * Given an integer n, return the number of prime numbers that are strictly less than n.
 *
 * Constraints:
 * 0 <= n <= 5 * 10^6
 */

fun main() {
    val n = 3

    val result = Solution().countPrimes(n)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N log log N)
    // 공간 복잡도 : O(N)
    fun countPrimes(n: Int): Int {
        if (n <= 2) return 0

        val isPrime = BooleanArray(n) { true }

        isPrime[0] = false
        isPrime[1] = false

        for (i in 2..Math.sqrt(n.toDouble()).toInt()) {
            if (isPrime[i]) {
                for (j in i * i until n step i) {
                    isPrime[j] = false
                }
            }
        }

        return isPrime.count { it }
    }
}