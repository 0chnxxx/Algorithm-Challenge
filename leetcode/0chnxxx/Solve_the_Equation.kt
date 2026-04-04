/**
 * Solve a given equation and return the value of 'x' in the form of a string "x=#value".
 * The equation contains only '+', '-' operation, the variable 'x' and its coefficient.
 * You should return "No solution" if there is no solution for the equation, or "Infinite solutions" if there are infinite solutions for the equation.
 * If there is exactly one solution for the equation, we ensure that the value of 'x' is an integer.
 *
 * Constraints:
 * 3 <= equation.length <= 1000
 * equation has exactly one '='.
 * equation consists of integers with an absolute value in the range [0, 100] without any leading zeros, and the variable 'x'.
 * The input is generated that if there is a single solution, it will be an integer.
 */

fun main() {
    val equation = "x+5-3+x=6+x-2"

    val result = Solution().solveEquation(equation)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun solveEquation(equation: String): String {
        val (left, right) = equation.split("=")

        val (leftX, leftNum) = parse(left)
        val (rightX, rightNum) = parse(right)

        val xCoeff = leftX - rightX
        val num = rightNum - leftNum

        return when {
            xCoeff == 0 && num == 0 -> "Infinite solutions"
            xCoeff == 0 -> "No solution"
            else -> "x=${num / xCoeff}"
        }
    }

    private fun parse(exp: String): Pair<Int, Int> {
        var xCoeff = 0
        var num = 0

        var i = 0
        var sign = 1

        while (i < exp.length) {
            when (exp[i]) {
                '+' -> {
                    sign = 1
                    i++
                }
                '-' -> {
                    sign = -1
                    i++
                }
                else -> {
                    var value = 0
                    var hasNum = false

                    while (i < exp.length && exp[i].isDigit()) {
                        value = value * 10 + (exp[i] - '0')
                        i++
                        hasNum = true
                    }

                    if (i < exp.length && exp[i] == 'x') {
                        // x인 경우
                        xCoeff += sign * if (hasNum) value else 1
                        i++
                    } else {
                        // 숫자인 경우
                        num += sign * value
                    }
                }
            }
        }

        return Pair(xCoeff, num)
    }
}
