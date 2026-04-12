/**
 * You are given an array of n pairs pairs where pairs[i] = [lefti, righti] and lefti < righti.
 * A pair p2 = [c, d] follows a pair p1 = [a, b] if b < c.
 * A chain of pairs can be formed in this fashion.
 * Return the length longest chain which can be formed.
 * You do not need to use up all the given intervals.
 * You can select pairs in any order.
 *
 * Constraints:
 * n == pairs.length
 * 1 <= n <= 1000
 * -1000 <= lefti < righti <= 1000
 */

fun main() {
    val pairs = arrayOf(
        intArrayOf(1, 2),
        intArrayOf(2, 3),
        intArrayOf(3, 4)
    )

    val result = Solution().findLongestChain(pairs)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(n log n)
    // 공간 복잡도 : O(1)
    fun findLongestChain(pairs: Array<IntArray>): Int {
        pairs.sortBy { it[1] }

        var count = 0
        var currentEnd = Int.MIN_VALUE

        for (pair in pairs) {
            if (pair[0] > currentEnd) {
                count++
                currentEnd = pair[1]
            }
        }

        return count
    }
}