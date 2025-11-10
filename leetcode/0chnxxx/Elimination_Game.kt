/**
 * You have a list arr of all integers in the range [1, n] sorted in a strictly increasing order. Apply the following algorithm on arr:
 * Starting from left to right, remove the first number and every other number afterward until you reach the end of the list.
 * Repeat the previous step again, but this time from right to left, remove the rightmost number and every other number from the remaining numbers.
 * Keep repeating the steps again, alternating left to right and right to left, until a single number remains.
 * Given the integer n, return the last number that remains in arr.
 *
 * Constraints:
 * 1 <= n <= 10^9
 */

fun main() {
    val n = 9

    val result = Solution().lastRemaining(n)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(log N)
    // 공간 복잡도 : O(1)
    fun lastRemaining(n: Int): Int {
        var head = 1
        var step = 1
        var remaining = n
        var leftToRight = true

        while (remaining > 1) {
            if (leftToRight || remaining % 2 == 1) {
                head += step
            }

            remaining /= 2
            step *= 2
            leftToRight = !leftToRight
        }

        return head
    }
}