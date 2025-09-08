/**
* Given a string expression of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators.
* You may return the answer in any order.
* The test cases are generated such that the output values fit in a 32-bit integer and the number of different results does not exceed 10^4.
*
* Constraints:
* 1 <= expression.length <= 20
* expression consists of digits and the operator '+', '-', and '*'.
* All the integer values in the input expression are in the range [0, 99].
* The integer values in the input expression do not have a leading '-' or '+' denoting the sign.
*/

fun main() {
    val expression = "2-1-1"

    val result = Solution().diffWaysToCompute(expression)

    println(result.joinToString(", "))
}

class Solution {
    // 시간 복잡도 : O(4^K / sqrt(K))
    // 공간 복잡도 : O(N)
    fun diffWaysToCompute(expression: String): List<Int> {
        val result = mutableListOf<Int>()

        // 모든 문자를 순회
        for (i in expression.indices) {
            val c = expression[i]

            // 연산자 찾기
            if (c == '+' || c == '-' || c == '*') {
                // 찾은 연산자를 기준으로 좌, 우를 나눔
                val left = diffWaysToCompute(expression.substring(0, i))
                val right = diffWaysToCompute(expression.substring(i + 1))

                // 나눠진 좌, 우를 연산자에 따라 계산
                for (l in left) {
                    for (r in right) {
                        when (c) {
                            '+' -> result.add(l + r)
                            '-' -> result.add(l - r)
                            '*' -> result.add(l * r)
                        }
                    }
                }
            }
        }

        // 연산자가 없는 경우엔 숫자가 하나이므로 그대로 반환
        if (result.isEmpty()) {
            result.add(expression.toInt())
        }

        return result
    }
}
