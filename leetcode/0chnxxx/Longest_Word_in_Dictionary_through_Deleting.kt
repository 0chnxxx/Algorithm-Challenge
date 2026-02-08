/**
 * Given a string s and a string array dictionary, return the longest string in the dictionary that can be formed by deleting some of the given string characters.
 * If there is more than one possible result, return the longest word with the smallest lexicographical order.
 * If there is no possible result, return the empty string.
 *
 * Constraints:
 * 1 <= s.length <= 1000
 * 1 <= dictionary.length <= 1000
 * 1 <= dictionary[i].length <= 1000
 * s and dictionary[i] consist of lowercase English letters.
 */

fun main() {
    val s = "abpcplea"
    val dictionary = listOf(
        "ale",
        "apple",
        "monkey",
        "plea"
    )

    val result = Solution().findLongestWord(s, dictionary)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(dictionary.length * s.length)
    // 공간 복잡도 : O(1)
    fun findLongestWord(s: String, dictionary: List<String>): String {
        var answer = ""

        for (word in dictionary) {
            if (isSubsequence(s, word)) {
                if (
                    word.length > answer.length ||
                    (word.length == answer.length && word < answer)
                ) {
                    answer = word
                }
            }
        }

        return answer
    }

    private fun isSubsequence(s: String, word: String): Boolean {
        var i = 0
        var j = 0

        while (i < s.length && j < word.length) {
            if (s[i] == word[j]) {
                j++
            }
            i++
        }

        return j == word.length
    }
}
