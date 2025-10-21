/**
 * Given two integers a and b, return the sum of the two integers without using the operators + and -.
 *
 * Constraints:
 * -1000 <= a, b <= 1000
 */

fun main() {
    val a = 1
    val b = 2

    val result = Solution().getSum(a, b)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(1)
    // 공간 복잡도 : O(1)
    fun getSum(a: Int, b: Int): Int {
        var x = a
        var y = b

        while (y != 0) {
            val carry = (x and y) shl 1

            x = x xor y
            y = carry
        }

        return x
    }
}