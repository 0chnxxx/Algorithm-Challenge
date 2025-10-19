/**
 * Given an integer n, return the count of all numbers with unique digits, x, where 0 <= x < 10n.
 *
 * Constraints:
 * 0 <= n <= 8
 */

fun main() {
    val n = 2

    val result = Solution().countNumbersWithUniqueDigits(n)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(1)
    // 공간 복잡도 : O(1)
    fun countNumbersWithUniqueDigits(n: Int): Int {
        if (n == 0) return 1

        var count = 10
        var uniqueDigits = 9
        var availableNumber = 9

        for (i in 2..n) {
            uniqueDigits *= availableNumber
            count += uniqueDigits
            availableNumber--

            if (availableNumber == 0) break
        }

        return count
    }
}