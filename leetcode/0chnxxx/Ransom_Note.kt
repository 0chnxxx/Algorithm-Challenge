/**
 * Given two strings ransomNote and magazine, return true if ransomNote can be constructed by using the letters from magazine and false otherwise.
 * Each letter in magazine can only be used once in ransomNote.
 *
 * Constraints:
 * 1 <= ransomNote.length, magazine.length <= 10^5
 * ransomNote and magazine consist of lowercase English letters.
 */

fun main() {
    val ransomNote = "a"
    val magazine = "b"

    val result = Solution().canConstruct(ransomNote, magazine)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N + M)
    // 공간 복잡도 : O(1)
    fun canConstruct(ransomNote: String, magazine: String): Boolean {
        val array = IntArray(26)

        for (c in magazine) {
            array[c - 'a']++
        }

        for (c in ransomNote) {
            array[c - 'a']--

            if (array[c - 'a'] < 0) return false
        }

        return true
    }
}