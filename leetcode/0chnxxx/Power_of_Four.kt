/**
 * Given an integer n, return true if it is a power of four.
 * Otherwise, return false.
 * An integer n is a power of four, if there exists an integer x such that n == 4x.
 *
 * Constraints:
 * -2^31 <= n <= 2^31 - 1
 *
 * Follow up:
 * Could you solve it without loops/recursion?
 */

fun main() {
    val n = 16

    val result = Solution().isPowerOfFour(n)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun isPowerOfFour(n: Int): Boolean {
        if (n <= 0) return false

        var num = n

        while (num % 4 == 0) {
            num /= 4
        }

        return num == 1
    }
}
