/**
 * Given an integer x, return true if x is a palindrome, and false otherwise.
 *
 * -2^31 <= x <= 2^31 - 1
 */

fun main() {
    val x = 121

    val solution = Solution().isPalindrome(x)

    println(solution)
}

class Solution {
    // O(log N) 풀이
    // toString 은 한 자리씩 처리하기 때문에 O(N)
    fun isPalindrome(x: Int): Boolean {
        if (x < 0 || (x % 10 == 0 && x != 0)) return false

        var left = x
        var right = 0

        while (left > right) {
            right = right * 10 + left % 10
            left /= 10
        }

        return left == right || left == right / 10
    }

//    // O(N) 풀이
//    fun isPalindrome(x: Int): Boolean {
//        val string = x.toString()
//
//        var left = 0
//        var right = string.length - 1
//
//        var isPalindrome = true
//
//        while (left < right) {
//            val leftString = string[left]
//            val rightString = string[right]
//
//            if (leftString != rightString) {
//                isPalindrome = false
//                break
//            }
//
//            left++
//            right--
//        }
//
//        return isPalindrome
//    }
}
