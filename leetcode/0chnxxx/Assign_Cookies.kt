/**
 * Assume you are an awesome parent and want to give your children some cookies.
 * But, you should give each child at most one cookie.
 * Each child i has a greed factor g[i], which is the minimum size of a cookie that the child will be content with; and each cookie j has a size s[j].
 * If s[j] >= g[i], we can assign the cookie j to the child i, and the child i will be content.
 * Your goal is to maximize the number of your content children and output the maximum number.
 *
 * Constraints:
 * 1 <= g.length <= 3 * 10^4
 * 0 <= s.length <= 3 * 10^4
 * 1 <= g[i], s[j] <= 2^31 - 1
 *
 * Note:
 * This question is the same as 2410: Maximum Matching of Players With Trainers.
 */

fun main() {
    val g = intArrayOf(1, 2, 3)
    val s = intArrayOf(1, 1)

    val result = Solution().findContentChildren(g, s)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N log N + M log M)
    // 공간 복잡도 : O(1)
    fun findContentChildren(g: IntArray, s: IntArray): Int {
        g.sort()
        s.sort()

        var i = 0
        var j = 0
        var count = 0

        while (i < g.size && j < s.size) {
            if (s[j] >= g[i]) {
                count++
                i++
                j++
            } else {
                j++
            }
        }

        return count
    }
}