import kotlin.math.exp

/**
 * Your task is to calculate ab mod 1337 where a is a positive integer and b is an extremely large positive integer given in the form of an array.
 *
 * Constraints:
 * 1 <= a <= 2^31 - 1
 * 1 <= b.length <= 2000
 * 0 <= b[i] <= 9
 * b does not contain leading zeros.
 */

fun main() {
    val a = 2
    val b = intArrayOf(3)

    val result = Solution().superPow(a, b)

    println(result)
}

class Solution {
    private val MOD = 1337

    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun superPow(a: Int, b: IntArray): Int {
        var result = 1
        var base = a % MOD

        fun modPow(a: Int, k: Int): Int {
            var result = 1
            var base = a % MOD
            var exponent = k

            while (exponent > 0) {
                if (exponent % 2 == 1) {
                    result = (result * base) % MOD
                }
                base = (base * base) % MOD
                exponent /= 2
            }

            return result
        }

        for (digit in b) {
            result = (modPow(result, 10) * modPow(base, digit)) % MOD
        }

        return result
    }
}
