/**
 * Given a positive integer n, you can apply one of the following operations:
 * If n is even, replace n with n / 2.
 * If n is odd, replace n with either n + 1 or n - 1.
 * Return the minimum number of operations needed for n to become 1.
 *
 * Constraints:
 * 1 <= n <= 2^31 - 1
 */

fun main() {
    val n = 8

    val result = Solution().integerReplacement(n)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(log N)
    // 공간 복잡도 : O(1)
    fun integerReplacement(n: Int): Int {
        var number = n.toLong()
        var count = 0

        while (number != 1L) {
            if (number % 2 == 0L) {
                number /= 2
            } else {
                // 3 일 땐 무조건 -1을 하는게 최적의 연산
                // 하위 2번째 자리 비트가 1이면 -1을 하는게 최적의 연산
                if (number == 3L || (number and 2L) == 0L) {
                    number -= 1
                } else {
                    number += 1
                }
            }

            count++
        }

        return count
    }
}