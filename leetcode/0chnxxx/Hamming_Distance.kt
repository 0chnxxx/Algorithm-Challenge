/**
 * The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
 * Given two integers x and y, return the Hamming distance between them.
 *
 * Constraints:
 * 0 <= x, y <= 2^31 - 1
 *
 * Note:
 * This question is the same as 2220: Minimum Bit Flips to Convert Number.
 */

fun main() {
    val x = 1
    val y = 4

    val result = Solution().hammingDistance(x, y)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(1)
    // 공간 복잡도 : O(1)
    fun hammingDistance(x: Int, y: Int): Int {
        return Integer.bitCount(x xor y)
    }
}
