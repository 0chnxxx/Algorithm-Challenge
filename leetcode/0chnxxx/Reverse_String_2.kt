/**
 * Given a string s and an integer k, reverse the first k characters for every 2k characters counting from the start of the string.
 * If there are fewer than k characters left, reverse all of them.
 * If there are less than 2k but greater than or equal to k characters, then reverse the first k characters and leave the other as original.
 *
 * Constraints:
 * 1 <= s.length <= 10^4
 * s consists of only lowercase English letters.
 * 1 <= k <= 10^4
 */

fun main() {
    val s = "abcdefg"
    val k = 2

    val result = Solution().reverseStr(s, k)

    println(result)
}

class Solution {
    fun reverseStr(s: String, k: Int): String {
        val arr = s.toCharArray()

        var i = 0

        while (i < arr.size) {
            var left = i
            var right = minOf(i + k - 1, arr.lastIndex)

            while (left < right) {
                val tmp = arr[left]
                arr[left] = arr[right]
                arr[right] = tmp
                left++
                right--
            }

            i += 2 * k
        }

        return String(arr)
    }
}