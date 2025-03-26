/**
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 *
 * 1 <= n <= 8
 */

fun main() {
    val n = 3

    val solution = Solution().generateParenthesis(n)

    println(solution)
}

class Solution {
    // 시간 복잡도 : O(4^N / sqrt(N))
    // 공간 복잡도 : O(4^N / sqrt(N * N))
    fun generateParenthesis(n: Int): List<String> {
        val result = mutableListOf<String>()

        fun backtrack(current: String, open: Int, close: Int) {
            if (current.length == n * 2) {
                result.add(current)
                return
            }

            if (open < n) backtrack(current + "(", open + 1, close)
            if (close < open) backtrack(current + ")", open, close + 1)
        }

        backtrack("", 0, 0)

        return result
    }
}
