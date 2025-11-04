/**
 * Given a string s, find the first non-repeating character in it and return its index. If it does not exist, return -1.
 *
 * Constraints:
 * 1 <= s.length <= 10^5
 * s consists of only lowercase English letters.
 */

fun main() {
    val s = "leetcode"

    val result = Solution().firstUniqChar(s)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun firstUniqChar(s: String): Int {
        val count = IntArray(26)

        for (ch in s) {
            count[ch - 'a']++
        }

        for ((index, ch) in s.withIndex()) {
            if (count[ch - 'a'] == 1) {
                return index
            }
        }

        return -1
    }
}