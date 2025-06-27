/**
 * Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.
 * Note that the same word in the dictionary may be reused multiple times in the segmentation.
 *
 * Constraints:
 * 1 <= s.length <= 300
 * 1 <= wordDict.length <= 1000
 * 1 <= wordDict[i].length <= 20
 * s and wordDict[i] consist of only lowercase English letters.
 * All the strings of wordDict are unique.
 */

fun main() {
    val s = "leetcode"
    val wordDict = listOf("leet", "code")

    val result = Solution().wordBreak(s, wordDict)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N^2)
    // 공간 복잡도 : O(N + D)
    fun wordBreak(s: String, wordDict: List<String>): Boolean {
        val wordSet = wordDict.toSet()
        val dp = BooleanArray(s.length + 1) { false }

        dp[0] = true

        for (i in 1..s.length) {
            for (j in 0 until i) {
                if (dp[j] && s.substring(j, i) in wordSet) {
                    dp[i] = true
                    break
                }
            }
        }

        return dp[s.length]
    }
}
