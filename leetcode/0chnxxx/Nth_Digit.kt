/**
 * Given an integer n, return the nth digit of the infinite integer sequence [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...].
 *
 * Constraints:
 * 1 <= n <= 2^31 - 1
 */

fun main() {
    val n = 3

    val result = Solution().findNthDigit(n)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(log 10 N)
    // 공간 복잡도 : O(1)
    fun findNthDigit(n: Int): Int {
        var num = n.toLong()
        var digit = 1L
        var start = 1L
        var count = 9L

        // n 이 어느 digit 그룹에 속하는지 계산
        while (num > digit * count) {
            num -= digit * count
            digit++
            count *= 10
            start *= 10
        }

        // 실제 숫자 계산
        val number = start + (num - 1) / digit

        // 해당 숫자의 자릿수 계산
        val index = ((num - 1) % digit).toInt()

        return number.toString()[index] - '0'
    }
}
