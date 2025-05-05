/**
 * Given two binary strings a and b, return their sum as a binary string.
 *
 * 1 <= a.length, b.length <= 104
 * a and b consist only of '0' or '1' characters.
 * Each string does not contain leading zeros except for the zero itself.
 */

fun main() {
    val a = "1111"
    val b = "1111"

    val solution = Solution().addBinary(a, b)

    println(solution)
}

class Solution {
    // N = maxOf(a.length, b.length)
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun addBinary(a: String, b: String): String {
        val result = StringBuilder()
        var i = a.length - 1
        var j = b.length - 1
        var carry = 0

        while (i >= 0 || j >= 0 || carry == 1) {
            val numA = if (i >= 0) a[i] - '0' else 0
            val numB = if (j >= 0) b[j] - '0' else 0

            val sum = numA + numB + carry

            result.append(sum % 2)
            carry = sum / 2

            i--
            j--
        }

        return result.reverse().toString()
    }

//    // N = maxOf(a.length, b.length)
//    // 시간 복잡도 : O(N)
//    // 공간 복잡도 : O(N)
//    fun addBinary(a: String, b: String): String {
//        val result = StringBuilder()
//        val maxLength = maxOf(a.length, b.length)
//
//        val numA = "0".repeat(maxLength - a.length) + a
//        val numB = "0".repeat(maxLength - b.length) + b
//        var carry = 0
//
//        for (i in maxLength - 1 downTo 0) {
//            if (numA[i] == '1' && numB[i] == '1' && carry == 1) {
//                result.append(1)
//                carry = 1
//            } else if (numA[i] == '1' && numB[i] == '1') {
//                result.append(0)
//                carry = 1
//            } else if ((numA[i] == '1' || numB[i] == '1') && carry == 1) {
//                result.append(0)
//                carry = 1
//            } else if (carry == 1 || numA[i] == '1' || numB[i] == '1') {
//                result.append(1)
//                carry = 0
//            } else if (numA[i] == '0' && numB[i] == '0') {
//                result.append(0)
//                carry = 0
//            }
//        }
//
//        if (carry == 1) {
//            result.append(1)
//        }
//
//        return result.reverse().toString()
//    }
}
