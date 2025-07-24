/**
 * Reverse bits of a given 32 bits unsigned integer.
 *
 * Note:
 * Note that in some languages, such as Java, there is no unsigned integer type.
 * In this case, both input and output will be given as a signed integer type.
 * They should not affect your implementation, as the integer's internal binary representation is the same, whether it is signed or unsigned.
 * In Java, the compiler represents the signed integers using 2's complement notation.
 * Therefore, in Example 2 below, the input represents the signed integer -3 and the output represents the signed integer -1073741825.
 *
 * Constraints:
 * 0 <= n <= 2^31 - 2
 * n is even.
 *
 * Follow up:
 * If this function is called many times, how would you optimize it?
 */

fun main() {
    val n = 43261596

    val result = Solution().reverseBits(n)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(1)
    // 공간 복잡도 : O(1)
    fun reverseBits(n: Int): Int {
        // 32비트를 맞추기 위한 padStart를 붙임
        val binary = Integer.toBinaryString(n).padStart(32, '0')

        return Integer.parseInt(binary.reversed(), 2)
    }
}