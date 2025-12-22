/**
 * Given a string s, sort it in decreasing order based on the frequency of the characters.
 * The frequency of a character is the number of times it appears in the string.
 * Return the sorted string.
 * If there are multiple answers, return any of them.
 *
 * Constraints:
 * 1 <= s.length <= 5 * 10^5
 * s consists of uppercase and lowercase English letters and digits.
 */

fun main() {
    val s = "tree"

    val result = Solution().frequencySort(s)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(N)
    fun frequencySort(s: String): String {
        val frequency = mutableMapOf<Char, Int>()

        for (c in s) {
            frequency.put(c, (frequency[c] ?: 0) + 1)
        }

        val sortedFrequency = frequency.entries.sortedByDescending { it.value }

        val builder = StringBuilder()

        for (entry in sortedFrequency) {
            repeat(entry.value) {
                builder.append(entry.key)
            }
        }

        return builder.toString()
    }
}
