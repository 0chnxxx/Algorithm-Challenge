/**
 * Given an array of characters chars, compress it using the following algorithm:
 * Begin with an empty string s.
 *
 * For each group of consecutive repeating characters in chars:
 * If the group's length is 1, append the character to s.
 * Otherwise, append the character followed by the group's length.
 *
 * The compressed string s should not be returned separately, but instead, be stored in the input character array chars.
 * Note that group lengths that are 10 or longer will be split into multiple characters in chars.
 * After you are done modifying the input array, return the new length of the array.
 * You must write an algorithm that uses only constant extra space.
 *
 * Note:
 * The characters in the array beyond the returned length do not matter and should be ignored.
 *
 * Constraints:
 * 1 <= chars.length <= 2000
 * chars[i] is a lowercase English letter, uppercase English letter, digit, or symbol.
 */

fun main() {
    val chars = charArrayOf('a', 'a', 'b', 'b', 'c', 'c', 'c')

    val result = Solution().compress(chars)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun compress(chars: CharArray): Int {
        var write = 0
        var read = 0

        while (read < chars.size) {
            val current = chars[read]
            val start = read

            while (read < chars.size && chars[read] == current) {
                read++
            }

            val count = read - start

            chars[write++] = current

            if (count > 1) {
                for (c in count.toString()) {
                    chars[write++] = c
                }
            }
        }

        return write
    }
}
