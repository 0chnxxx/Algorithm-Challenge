/**
 * The complement of an integer is the integer you get when you flip all the 0's to 1's and all the 1's to 0's in its binary representation.
 * For example, The integer 5 is "101" in binary and its complement is "010" which is the integer 2.
 * Given an integer num, return its complement.
 *
 * Constraints:
 * 1 <= num < 2^31
 *
 * Note:
 * This question is the same as 1009: https://leetcode.com/problems/complement-of-base-10-integer/
 */

fun main() {
    val num = 5

    val result = Solution().findComplement(num)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(log N)
    // 공간 복잡도 : O(1)
    fun findComplement(num: Int): Int {
        var mask = 1L
        val n = num.toLong()

        while (mask <= n) {
            mask = mask shl 1
        }

        mask -= 1

        return (n xor mask).toInt()
    }
}