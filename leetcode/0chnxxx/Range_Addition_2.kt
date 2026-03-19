/**
 * You are given an m x n matrix M initialized with all 0's and an array of operations ops, where ops[i] = [ai, bi] means M[x][y] should be incremented by one for all 0 <= x < ai and 0 <= y < bi.
 * Count and return the number of maximum integers in the matrix after performing all the operations.
 *
 * Constraints:
 * 1 <= m, n <= 4 * 10^4
 * 0 <= ops.length <= 10^4
 * ops[i].length == 2
 * 1 <= ai <= m
 * 1 <= bi <= n
 */

fun main() {
    val m = 3
    val n =3
    val ops = arrayOf(
        intArrayOf(2, 2),
        intArrayOf(3, 3)
    )

    val result = Solution().maxCount(m, n, ops)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(ops.length)
    // 공간 복잡도 : O(1)
    fun maxCount(m: Int, n: Int, ops: Array<IntArray>): Int {
        if (ops.isEmpty()) return m * n

        var minA = m
        var minB = n

        for (op in ops) {
            minA = minOf(minA, op[0])
            minB = minOf(minB, op[1])
        }

        return minA * minB
    }
}