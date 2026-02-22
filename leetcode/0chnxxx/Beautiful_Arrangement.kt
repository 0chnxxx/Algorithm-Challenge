/**
 * Suppose you have n integers labeled 1 through n.
 * A permutation of those n integers perm (1-indexed) is considered a beautiful arrangement if for every i (1 <= i <= n), either of the following is true:
 * perm[i] is divisible by i.
 * i is divisible by perm[i].
 * Given an integer n, return the number of the beautiful arrangements that you can construct.
 *
 * Constraints:
 * 1 <= n <= 15
 */

fun main() {
    val n = 2

    val result = Solution().countArrangement(n)

    println(result)
}

class Solution {
    private var count = 0

    // 시간 복잡도 : O(N!)
    // 공간 복잡도 : O(N)
    fun countArrangement(n: Int): Int {
        val visited = BooleanArray(n + 1)

        backtrack(1, n, visited)

        return count
    }

    private fun backtrack(pos: Int, n: Int, visited: BooleanArray) {
        if (pos > n) {
            count++
            return
        }

        for (num in 1..n) {
            if (!visited[num] && (num % pos == 0 || pos % num == 0)) {
                visited[num] = true
                backtrack(pos + 1, n, visited)
                visited[num] = false
            }
        }
    }
}