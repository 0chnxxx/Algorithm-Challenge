/**
 * Given two strings a and b, return the length of the longest uncommon subsequence between a and b.
 * If no such uncommon subsequence exists, return -1.
 * An uncommon subsequence between two strings is a string that is a subsequence of exactly one of them.
 *
 * Constraints:
 * 1 <= a.length, b.length <= 100
 * a and b consist of lower-case English letters.
 */

fun main() {
    val a = "aba"
    val b = "cdc"

    val result = Solution().findLUSlength(a, b)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun findLUSlength(a: String, b: String): Int {
        if (a == b) return -1

        return maxOf(a.length, b.length)
    }
}