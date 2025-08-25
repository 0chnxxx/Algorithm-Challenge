/**
 * Given an integer n, return true if it is a power of two.
 * Otherwise, return false.
 *
 * An integer n is a power of two, if there exists an integer x such that n == 2^x.
 *
 * Constraints:
 * -2^31 <= n <= 2^31 - 1
 *
 *
 * Follow up:
 * Could you solve it without loops/recursion?
 */

fun main() {
    val n = 1

    val result = Solution().isPowerOfTwo(n)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(1)
    // 공간 복잡도 : O(1)
    fun isPowerOfTwo(n: Int): Boolean {
        // 2의 거듭제곱 수는 이진수로 표현하면 항상 단 하나의 비트만 1이고 나머지는 0
        // 어떤 수에서 1을 빼면 모든 비트가 반전이 된다.
        // 그래서 and 비트 연산을 했을 때 0이면 거듭제곱 수가 맞다.
        return n > 0 && (n and (n - 1)) == 0
    }

//    // 시간 복잡도 : O(N)
//    // 공간 복잡도 : O(1)
//    fun isPowerOfTwo(n: Int): Boolean {
//        var result = false
//
//        for (i in 0 until n) {
//            val pow = Math.pow(2.0, i.toDouble())
//
//            if (pow > n) {
//                result = false
//                break
//            }
//
//            if (pow.toInt() == n) {
//                result = true
//                break
//            }
//        }
//
//        return result
//    }
}
