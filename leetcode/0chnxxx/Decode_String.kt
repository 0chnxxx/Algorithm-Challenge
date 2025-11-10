import java.util.Stack

/**
 * Given an encoded string, return its decoded string.
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times.
 * Note that k is guaranteed to be a positive integer.
 * You may assume that the input string is always valid; there are no extra white spaces, square brackets are well-formed, etc.
 * Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k.
 * For example, there will not be input like 3a or 2[4].
 * The test cases are generated so that the length of the output will never exceed 105.
 *
 * Constraints:
 * 1 <= s.length <= 30
 * s consists of lowercase English letters, digits, and square brackets '[]'.
 * s is guaranteed to be a valid input.
 * All the integers in s are in the range [1, 300].
 */

fun main() {
    val s = "3[a]2[bc]"

    val result = Solution().decodeString(s)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(N)
    fun decodeString(s: String): String {
        val numStack = Stack<Int>()
        val charStack = Stack<String>()

        var current = ""
        var num = 0

        for (c in s) {
            when {
                c.isDigit() -> {
                    num = num * 10 + (c - '0')
                }
                c == '[' -> {
                    numStack.push(num)
                    charStack.push(current)
                    num = 0
                    current = ""
                }
                c == ']' -> {
                    val repeat = numStack.pop()
                    val prevChar = charStack.pop()

                    current = prevChar + current.repeat(repeat)
                }
                else -> {
                    current += c
                }
            }
        }

        return current
    }
}
