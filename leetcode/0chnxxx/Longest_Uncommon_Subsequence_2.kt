/**
 * Given an array of strings strs, return the length of the longest uncommon subsequence between them.
 * If the longest uncommon subsequence does not exist, return -1.
 * An uncommon subsequence between an array of strings is a string that is a subsequence of one string but not the others.
 * A subsequence of a string s is a string that can be obtained after deleting any number of characters from s.
 * For example, "abc" is a subsequence of "aebdc" because you can delete the underlined characters in "aebdc" to get "abc".
 * Other subsequences of "aebdc" include "aebdc", "aeb", and "" (empty string).
 *
 * Constraints:
 * 2 <= strs.length <= 50
 * 1 <= strs[i].length <= 10
 * strs[i] consists of lowercase English letters.
 */

fun main() {
    val strs = arrayOf("aba", "cdc", "eae")

    val result = Solution().findLUSlength(strs)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N^2 * L)
    // 공간 복잡도 : O(1)
    fun findLUSlength(strs: Array<String>): Int {
        var answer = -1

        for (i in strs.indices) {
            val s = strs[i]
            var isUncommon = true

            for (j in strs.indices) {
                if (i == j) continue

                if (isSubsequence(s, strs[j])) {
                    isUncommon = false
                    break
                }
            }

            if (isUncommon) {
                answer = maxOf(answer, s.length)
            }
        }

        return answer
    }

    private fun isSubsequence(s: String, t: String): Boolean {
        var i = 0
        var j = 0

        while (i < s.length && j < t.length) {
            if (s[i] == t[j]) {
                i++
            }
            j++
        }

        return i == s.length
    }
}
