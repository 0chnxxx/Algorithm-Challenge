/**
 * Given an integer n, return the number of trailing zeroes in n!.
 * Note that n! = n * (n - 1) * (n - 2) * ... * 3 * 2 * 1.
 *
 * Constraints:
 * 0 <= n <= 104
 *
 * Follow up:
 * Could you write a solution that works in logarithmic time complexity?
 */

fun main() {
    val n = 30

    val result = Solution().trailingZeroes(n)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(log N)
    // 공간 복잡도 : O(1)
    fun trailingZeroes(n: Int): Int {
        var count = 0
        var i = 5

        while (n / i >= 1) {
            count += n / i
            i *= 5
        }

        return count
    }
}
