/**
 * There is only one character 'A' on the screen of a notepad.
 * You can perform one of two operations on this notepad for each step:
 * Copy All: You can copy all the characters present on the screen (a partial copy is not allowed).
 * Paste: You can paste the characters which are copied last time.
 * Given an integer n, return the minimum number of operations to get the character 'A' exactly n times on the screen.
 *
 * Constraints:
 * 1 <= n <= 1000
 */

fun main() {
    val n = 3

    val result = Solution().minSteps(n)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(root N)
    // 공간 복잡도 : O(1)
    fun minSteps(n: Int): Int {
        var num = n
        var result = 0
        var d = 2

        while (num > 1) {
            while (num % d == 0) {
                result += d
                num /= d
            }

            d++
        }

        return result
    }
}
