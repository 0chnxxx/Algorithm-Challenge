/**
 * Find all valid combinations of k numbers that sum up to n such that the following conditions are true:
 * Only numbers 1 through 9 are used.
 * Each number is used at most once.
 * Return a list of all possible valid combinations.
 * The list must not contain the same combination twice, and the combinations may be returned in any order.
 *
 * Constraints:
 * 2 <= k <= 9
 * 1 <= n <= 60
 */

fun main() {
    val k = 3
    val n = 7

    val result = Solution().combinationSum3(k, n)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(C(9, k) * k)
    // 공간 복잡도 : O(C(9, k) * k)
    fun combinationSum3(k: Int, n: Int): List<List<Int>> {
        val result = mutableListOf<List<Int>>()

        fun dfs(start: Int, list: MutableList<Int>, sum: Int) {
            if (list.size == k && sum == n) {
                result.add(list.toList())
                return
            }

            if (list.size >= k || sum > n) return

            for (i in start..9) {
                list.add(i)
                dfs(i + 1, list, sum + i)
                list.removeAt(list.lastIndex)
            }
        }

        dfs(1, mutableListOf(), 0)

        return result
    }
}