/**
 * Given a string s, reverse only all the vowels in the string and return it.
 * The vowels are 'a', 'e', 'i', 'o', and 'u', and they can appear in both lower and upper cases, more than once.
 *
 * Constraints:
 * 1 <= s.length <= 3 * 10^5
 * s consist of printable ASCII characters.
 */

fun main() {
    val s = "IceCreAm"

    val result = Solution().reverseVowels(s)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun reverseVowels(s: String): String {
        val vowels = setOf('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U')
        val chars = s.toCharArray()
        var left = 0
        var right = chars.size - 1

        while (left < right) {
            while (left < right && chars[left] !in vowels) {
                left++
            }

            while (left < right && chars[right] !in vowels) {
                right--
            }

            if (left < right) {
                val temp = chars[left]

                chars[left] = chars[right]
                chars[right] = temp

                left++
                right--
            }
        }

        return String(chars)
    }
}
