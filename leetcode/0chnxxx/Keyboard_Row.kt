/**
 * Given an array of strings words, return the words that can be typed using letters of the alphabet on only one row of American keyboard like the image below.
 * Note that the strings are case-insensitive, both lowercased and uppercased of the same letter are treated as if they are at the same row.
 *
 * In the American keyboard:
 * the first row consists of the characters "qwertyuiop",
 * the second row consists of the characters "asdfghjkl", and
 * the third row consists of the characters "zxcvbnm".
 *
 * Constraints:
 * 1 <= words.length <= 20
 * 1 <= words[i].length <= 100
 * words[i] consists of English letters (both lowercase and uppercase).
 */

fun main() {
    val words = arrayOf(
        "Hello",
        "Alaska",
        "Dad",
        "Peace"
    )

    val result = Solution().findWords(words)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N * L)
    // 공간 복잡도 : O(1)
    fun findWords(words: Array<String>): Array<String> {
        val row1 = setOf('q','w','e','r','t','y','u','i','o','p')
        val row2 = setOf('a','s','d','f','g','h','j','k','l')
        val row3 = setOf('z','x','c','v','b','n','m')

        val result = mutableListOf<String>()

        for (word in words) {
            val lower = word.lowercase()
            val firstChar = lower[0]

            val row = when {
                row1.contains(firstChar) -> row1
                row2.contains(firstChar) -> row2
                else -> row3
            }

            if (lower.all { row.contains(it) }) {
                result.add(word)
            }
        }

        return result.toTypedArray()
    }
}