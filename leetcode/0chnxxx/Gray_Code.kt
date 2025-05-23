/**
 * An n-bit gray code sequence is a sequence of 2^n integers where:
 * - Every integer is in the inclusive range [0, 2^n - 1],
 * - The first integer is 0,
 * - An integer appears no more than once in the sequence,
 * - The binary representation of every pair of adjacent integers differs by exactly one bit, and
 * - The binary representation of the first and last integers differs by exactly one bit.
 *
 * Given an integer n, return any valid n-bit gray code sequence.
 *
 * Constraints:
 * 1 <= n <= 16
 */

fun main() {
    val n = 2

    val solution = Solution().grayCode(n)

    println(solution.joinToString(", "))
}

class Solution {
    // 시간 복잡도 : O(2^N)
    // 공간 복잡도 : O(2^N)
    fun grayCode(n: Int): List<Int> {
        val result = mutableListOf<Int>()

        // 1 을 왼쪽으로 n 비트 이동
        val size = 1 shl n // 2^n

        //
        for (i in 0 until size) {
            result.add(i xor (i shr 1))
        }

        return result
    }

//    // 시간 복잡도 : O(2^N)
//    // 공간 복잡도 : O(2^N)
//    fun grayCode(n: Int): List<Int> {
//        var bit = listOf("0", "1")
//
//        for (i in 2..n) {
//            val prefix0 = bit.map { "0$it" }
//            val prefix1 = bit.reversed().map { "1$it" }
//
//            bit = prefix0 + prefix1
//        }
//
//        return bit.map { it.toInt(2) }
//    }
}
