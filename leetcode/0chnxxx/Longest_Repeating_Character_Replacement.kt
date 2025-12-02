/**
 * You are given a string s and an integer k. You can choose any character of the string and change it to any other uppercase English character.
 * You can perform this operation at most k times.
 * Return the length of the longest substring containing the same letter you can get after performing the above operations.
 *
 * Constraints:
 * 1 <= s.length <= 10^5
 * s consists of only uppercase English letters.
 * 0 <= k <= s.length
 */

fun main() {
    val s = "ABAB"
    val k = 2

    val result = Solution().characterReplacement(s, k)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun characterReplacement(s: String, k: Int): Int {
        val frequency = IntArray(26)
        var left = 0
        var maxFrequency = 0
        var result = 0

        for (right in s.indices) {
            val index = s[right] - 'A'
            frequency[index]++
            maxFrequency = maxOf(maxFrequency, frequency[index])

            while ((right - left + 1) - maxFrequency > k) {
                frequency[s[left] - 'A']--
                left++
            }

            result = maxOf(result, right - left + 1)
        }

        return result
    }
}