/**
 * 어떤 숫자에서 k개의 수를 제거했을 때 얻을 수 있는 가장 큰 숫자를 구하려 합니다.
 * 예를 들어, 숫자 1924에서 수 두 개를 제거하면 [19, 12, 14, 92, 94, 24]를 만들 수 있습니다.
 * 이 중 가장 큰 숫자는 94입니다.
 * 문자열 형식으로 숫자 number와 제거할 수의 개수 k가 solution 함수의 매개변수로 주어집니다.
 * number에서 k개의 수를 제거했을 때 만들 수 있는 수 중 가장 큰 숫자를 문자열 형태로 return 하도록 solution 함수를 완성하세요.
 *
 * 2 <= number의 자릿수 <= 1000000
 * 1 <= k < number의 자릿수
 */

fun main() {
    val number = "1924"
    val k = 2

    val result = Solution().solution(number, k)

    println(result)
}

class Solution {
    fun solution(number: String, k: Int): String {
        val stack = mutableListOf<Char>()
        var count = k

        for (digit in number) {
            while (stack.isNotEmpty() && count > 0 && stack.last() < digit) {
                stack.removeAt(stack.size - 1)
                count--
            }

            stack.add(digit)
        }

        while (count > 0) {
            stack.removeAt(stack.size - 1)
            count--
        }

        return stack.joinToString("")
    }
}
