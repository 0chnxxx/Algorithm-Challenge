/**
 * Given an integer num, repeatedly add all its digits until the result has only one digit, and return it.
 *
 * Constraints:
 * 0 <= num <= 2^31 - 1
 *
 * Follow up:
 * Could you do it without any loop/recursion in O(1) runtime?
 */

fun main() {
    val num = 38

    val result = Solution().addDigits(num)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(1)
    // 공간 복잡도 : O(1)
    fun addDigits(num: Int): Int {
        return if (num == 0) 0 else 1 + (num - 1) % 9
    }

//    // 시간 복잡도 : O(log 10 (num))
//    // 공간 복잡도 : O(1)
//    fun addDigits(num: Int): Int {
//        var n = num
//
//        while (n >= 10) {
//            var sum = 0
//
//            while (n > 0) {
//                sum += n % 10
//                n /= 10
//            }
//
//            n = sum
//        }
//
//        return n
//    }
}