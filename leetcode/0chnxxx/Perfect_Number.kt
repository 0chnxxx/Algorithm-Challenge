/**
 * A perfect number is a positive integer that is equal to the sum of its positive divisors, excluding the number itself.
 * A divisor of an integer x is an integer that can divide x evenly.
 * Given an integer n, return true if n is a perfect number, otherwise return false.
 *
 * Constraints:
 * 1 <= num <= 10^8
 */

fun main() {
    val num = 28

    val result = Solution().checkPerfectNumber(num)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(root(N))
    // 공간 복잡도 : O(1)
    fun checkPerfectNumber(num: Int): Boolean {
        if (num <= 1) return false

        var sum = 1
        var i = 2

        while (i * i <= num) {
            if (num % i == 0) {
                sum += i

                if (i != num / i) {
                    sum += num / i
                }
            }

            i++
        }

        return sum == num
    }
}