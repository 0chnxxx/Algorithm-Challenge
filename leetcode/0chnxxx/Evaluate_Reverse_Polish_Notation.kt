import java.util.Stack

/**
 * You are given an array of strings tokens that represents an arithmetic expression in a Reverse Polish Notation.
 * Evaluate the expression.
 * Return an integer that represents the value of the expression.
 *
 * Note that:
 * The valid operators are '+', '-', '*', and '/'.
 * Each operand may be an integer or another expression.
 * The division between two integers always truncates toward zero.
 * There will not be any division by zero.
 * The input represents a valid arithmetic expression in a reverse polish notation.
 * The answer and all the intermediate calculations can be represented in a 32-bit integer.
 *
 * Constraints:
 * 1 <= tokens.length <= 10^4
 * tokens[i] is either an operator: "+", "-", "*", or "/", or an integer in the range [-200, 200].
 */

fun main() {
    val tokens = arrayOf("4", "13", "5", "/", "+")

    val result = Solution().evalRPN(tokens)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(N)
    // Stack 대신 Array와 Pointer를 통한 최적화
    fun evalRPN(tokens: Array<String>): Int {
        val stack = IntArray(tokens.size)
        var top = -1

        for (token in tokens) {
            when (token) {
                "+" -> {
                    val b = stack[top--]
                    val a = stack[top]
                    stack[top] = a + b
                }
                "-" -> {
                    val b = stack[top--]
                    val a = stack[top]
                    stack[top] = a - b
                }
                "*" -> {
                    val b = stack[top--]
                    val a = stack[top]
                    stack[top] = a * b
                }
                "/" -> {
                    val b = stack[top--]
                    val a = stack[top]
                    stack[top] = a / b
                }
                else -> stack[++top] = token.toInt()
            }
        }

        return stack[0]
    }

//    // 시간 복잡도 : O(N)
//    // 공간 복잡도 : O(N)
//    // 불필요한 오버헤드 제거로 최적화
//    fun evalRPN(tokens: Array<String>): Int {
//        val stack = Stack<Int>()
//
//        for (token in tokens) {
//            when (token) {
//                "+" -> stack.push(stack.pop().let { b -> stack.pop() + b })
//                "-" -> stack.push(stack.pop().let { b -> stack.pop() - b })
//                "*" -> stack.push(stack.pop().let { b -> stack.pop() * b })
//                "/" -> stack.push(stack.pop().let { b -> stack.pop() / b })
//                else -> stack.push(token.toInt())
//            }
//        }
//
//        return stack.pop()
//    }

//    // 시간 복잡도 : O(N)
//    // 공간 복잡도 : O(N)
//    fun evalRPN(tokens: Array<String>): Int {
//        val operators = arrayOf("+", "-", "*", "/")
//        val stack = Stack<String>()
//
//        for (token in tokens) {
//            if (operators.contains(token)) {
//                val secondValue = stack.pop()
//                val firstValue = stack.pop()
//
//                when (token) {
//                    "+" -> stack.push((firstValue.toInt() + secondValue.toInt()).toString())
//                    "-" -> stack.push((firstValue.toInt() - secondValue.toInt()).toString())
//                    "*" -> stack.push((firstValue.toInt() * secondValue.toInt()).toString())
//                    "/" -> stack.push((firstValue.toDouble() / secondValue.toDouble()).toInt().toString())
//                }
//
//                continue
//            }
//
//            stack.push(token)
//        }
//
//        return stack.pop().toInt()
//    }
}
