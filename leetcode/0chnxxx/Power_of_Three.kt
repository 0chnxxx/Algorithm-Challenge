/**
 * Given an integer n, return true if it is a power of three.
 * Otherwise, return false.
 * An integer n is a power of three, if there exists an integer x such that n == 3x.
 *
 * Constraints:
 * -2^31 <= n <= 2^31 - 1
 *
 * Follow up:
 * Could you solve it without loops/recursion?
 */

fun main() {
    val n = 27

    val result = Solution().isPowerOfThree(n)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(1)
    // 공간 복잡도 : O(1)
    fun isPowerOfThree(n: Int): Boolean {
        // Int 범위에서 3의 거듭제곱 중 최댓값은 3^19 라서
        // n 이 3^x 라면 3^19 % n == 0 을 만족해야함
        return n > 0 && 1162261467 % n == 0
    }

//    // 시간 복잡도 : O(N)
//    // 공간 복잡도 : O(1)
//    fun isPowerOfThree(n: Int): Boolean {
//        if (n <= 0) return false
//
//        var num = n
//
//        while (num % 3 == 0) {
//            num /= 3
//        }
//
//        return num == 1
//    }
}