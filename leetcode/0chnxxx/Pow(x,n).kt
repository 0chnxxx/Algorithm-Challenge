/**
 * Implement pow(x, n), which calculates x raised to the power n (i.e., xn).
 *
 * -100.0 < x < 100.0
 * -23^1 <= n <= 2^31-1
 * n is an integer.
 * Either x is not zero or n > 0.
 * -10^4 <= x^n <= 10^4
 */

fun main() {
    val x = 2.00000
    val n = -2147483648

    val solution = Solution().myPow(x, n)

    println(solution)
}

class Solution {
    // 시간 복잡도 : O(log N)
    // 공간 복잡도 : O(1)
    fun myPow(x: Double, n: Int): Double {
        var base = x
        var exponent = n.toLong() // Int.MIN_VALUE의 overflow 방지

        // n이 음수인 경우
        // x^-n -> 1 / x^n 로 변경
        if (exponent < 0) {
            base = 1 / base
            exponent = -exponent
        }

        var result = 1.0

        // 지수 법칙을 통한 분할 정복
        // 짝수: x^n = (x^(n/2))^2
        // 홀수: x^n = x * (x^((n-1)/2))^2
        // 3^13 = 3^8 * 3^4 * 3^1 = (3^4 * 3^4) * (3^2 * 3^2) * 3^1
        while (exponent > 0) {
            // 홀수인 경우 result에 반영
            if (exponent % 2 == 1L) {
                result *= base
            }

            base *= base // 값의 절반을 미리 계산
            exponent /= 2 // N 을 log N 으로 압축
        }

        return result
    }
}
