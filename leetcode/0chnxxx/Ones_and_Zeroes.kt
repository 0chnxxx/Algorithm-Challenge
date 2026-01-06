/**
 * You are given an array of binary strings strs and two integers m and n.
 * Return the size of the largest subset of strs such that there are at most m 0's and n 1's in the subset.
 * A set x is a subset of a set y if all elements of x are also elements of y.
 *
 * Constraints:
 * 1 <= strs.length <= 600
 * 1 <= strs[i].length <= 100
 * strs[i] consists only of digits '0' and '1'.
 * 1 <= m, n <= 100
 */

fun main() {
    val strs = arrayOf(
        "10",
        "0001",
        "111001",
        "1",
        "0"
    )
    val m = 5
    val n = 3

    val result = Solution().findMaxForm(strs, n, m)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(len(strs) * M * N)
    // 공간 복잡도 : O(M * N)
    fun findMaxForm(strs: Array<String>, m: Int, n: Int): Int {
        val dp = Array(m + 1) { IntArray(n + 1) }

        // 모든 문자열을 순회
        for (s in strs) {
            var zeros = 0
            var ones = 0

            // 0과 1의 갯수를 카운트
            for (c in s) {
                if (c == '0') {
                    zeros++
                } else {
                    ones++
                }
            }

            // 0을 m개 이하, 1을 n개 이하로 사용 했을 때의 모든 최선의 경우를 dp로 구함
            for (i in m downTo zeros) {
                for (j in n downTo ones) {
                    dp[i][j] = maxOf(dp[i][j], dp[i - zeros][j - ones] + 1)
                }
            }
        }

        return dp[m][n]
    }
}