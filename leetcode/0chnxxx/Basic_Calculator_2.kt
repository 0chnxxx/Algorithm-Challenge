import java.util.Stack

/**
 * Given a string s which represents an expression, evaluate this expression and return its value.
 * The integer division should truncate toward zero.
 * You may assume that the given expression is always valid.
 * All intermediate results will be in the range of [-2^31, 2^31 - 1].
 *
 * Note:
 * You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().
 *
 * Constraints:
 * 1 <= s.length <= 3 * 10^5
 * s consists of integers and operators ('+', '-', '*', '/') separated by some number of spaces.
 * s represents a valid expression.
 * All the integers in the expression are non-negative integers in the range [0, 2^31 - 1].
 * The answer is guaranteed to fit in a 32-bit integer.
 */

fun main() {
    val s = "3+2*2"

    val result = Solution().calculate(s)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(N)
    fun calculate(s: String): Int {
        val stack = Stack<Int>()
        var num = 0
        var operator = '+'

        val chars = s.toCharArray()

        for (i in chars.indices) {
            val c = chars[i]

            if (c.isDigit()) {
                num = num * 10 + (c - '0')
            }

            if ((!c.isDigit() && c != ' ') || i == chars.lastIndex) {
                when (operator) {
                    '+' -> stack.push(num)
                    '-' -> stack.push(-num)
                    '*' -> stack.push(stack.pop() * num)
                    '/' -> stack.push(stack.pop() / num)
                }

                operator = c
                num = 0
            }
        }

        return stack.sum()
    }
}