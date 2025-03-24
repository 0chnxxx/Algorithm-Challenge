import java.util.*

/**
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 *
 * An input string is valid if:
 *
 * 1. Open brackets must be closed by the same type of brackets.
 * 2. Open brackets must be closed in the correct order.
 * 3. Every close bracket has a corresponding open bracket of the same type.
 *
 * 1 <= s.length <= 104
 * s consists of parentheses only '()[]{}'.
 */

fun main() {
    val s = "([)]"

    val solution = Solution().isValid(s)

    println(solution)
}

class Solution {
    // 시간 복잡도 O(N)
    // 공간 복잡도 O(N)
    fun isValid(s: String): Boolean {
        if (s.length == 1) return false

        val brackets = mapOf(
            ')' to '(',
            '}' to '{',
            ']' to '['
        )

        val stack = Stack<Char>()

        for (c in s) {
            when (c) {
                '(', '{', '[' -> stack.push(c)
                ')', '}', ']' -> {
                    if (stack.isEmpty() || stack.pop() != brackets[c]) {
                        return false
                    }
                }
            }
        }

        return stack.isEmpty()
    }
}
