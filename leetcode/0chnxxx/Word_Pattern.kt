/**
 * Given a pattern and a string s, find if s follows the same pattern.
 *
 * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in s. Specifically:
 * Each letter in pattern maps to exactly one unique word in s.
 * Each unique word in s maps to exactly one letter in pattern.
 * No two letters map to the same word, and no two words map to the same letter.
 *
 * Constraints:
 * 1 <= pattern.length <= 300
 * pattern contains only lower-case English letters.
 * 1 <= s.length <= 3000
 * s contains only lowercase English letters and spaces ' '.
 * s does not contain any leading or trailing spaces.
 * All the words in s are separated by a single space.
 */

fun main() {
    val pattern = "abba"
    val s = "dog dog dog dog"

    val result = Solution().wordPattern(pattern, s)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(N)
    fun wordPattern(pattern: String, s: String): Boolean {
        val words = s.split(" ")

        // 사이즈 자체가 안맞으면 false
        if (pattern.length != words.size) return false

        // 양방향 매핑
        val charToWord = mutableMapOf<Char, String>()
        val wordToChar = mutableMapOf<String, Char>()

        for (i in pattern.indices) {
            val c = pattern[i]
            val w = words[i]

            // 이미 해당 키를 포함한다면
            if (charToWord.containsKey(c)) {
                // 해당 키의 값이 넣으려는 값과 다르다면 false
                if (charToWord[c] != w) return false
            } else {
                // 해당 키를 포함하지 않을 때 추가
                charToWord[c] = w
            }

            // 이미 해당 키를 포함한다면
            if (wordToChar.containsKey(w)) {
                // 해당 키의 값이 넣으려는 값과 다르다면 false
                if (wordToChar[w] != c) return false
            } else {
                // 해당 키를 포함하지 않을 때 추가
                wordToChar[w] = c
            }
        }

        // 모두 통과했다면 true
        return true
    }
}