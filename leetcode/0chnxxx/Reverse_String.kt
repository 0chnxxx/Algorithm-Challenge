/**
 * Write a function that reverses a string.
 * The input string is given as an array of characters s.
 * You must do this by modifying the input array in-place with O(1) extra memory.
 *
 * Constraints:
 * 1 <= s.length <= 10^5
 * s[i] is a printable ascii character.
 */

fun main() {
    val s = charArrayOf('h', 'e', 'l', 'l', 'o')

    val result = Solution().reverseString(s)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun reverseString(s: CharArray): Unit {
        var left = 0
        var right = s.size - 1

        while (left < right) {
            val temp = s[left]

            s[left] = s[right]
            s[right] = temp

            left++
            right--
        }
    }
}
