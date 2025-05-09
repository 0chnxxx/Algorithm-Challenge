/**
 * 0과 1로 이루어진 어떤 문자열 x에 대해서, 당신은 다음과 같은 행동을 통해 x를 최대한 사전 순으로 앞에 오도록 만들고자 합니다.
 * - x에 있는 "110"을 뽑아서, 임의의 위치에 다시 삽입합니다.
 * 예를 들어, x = "11100" 일 때, 여기서 중앙에 있는 "110"을 뽑으면 x = "10"이 됩니다.
 * 뽑았던 "110"을 x의 맨 앞에 다시 삽입하면 x = "11010"이 됩니다.
 *
 * 변형시킬 문자열 x가 여러 개 들어있는 문자열 배열 s가 주어졌을 때
 * 각 문자열에 대해서 위의 행동으로 변형해서 만들 수 있는 문자열 중 사전 순으로 가장 앞에 오는 문자열을 배열에 담아 return 하도록 solution 함수를 완성해주세요.
 *
 * 1 <= s의 길이 <= 1000000
 * 1 <= s의 각 원소 길이 <= 1000000
 * 1 <= s의 모든 원소의 길이의 합 <= 1000000
 */

fun main() {
    val s = arrayOf("1110", "100111100", "0111111010")

    val solution = Solution().solution(s)

    println(solution.joinToString(", "))
}

class Solution {
    fun solution(s: Array<String>): Array<String> {
        return s.map { transform(it) }.toTypedArray()
    }

    private fun transform(str: String): String {
        val stack = StringBuilder()
        var count = 0

        for (c in str) {
            stack.append(c)

            if (stack.length >= 3 && stack[stack.length - 3] == '1' && stack[stack.length - 2] == '1' && stack[stack.length - 1] == '0') {
                stack.setLength(stack.length - 3)
                count++
            }
        }

        val base = stack.toString()
        val insertPosition = base.lastIndexOf("0") + 1

        val result = StringBuilder(base)

        repeat(count) {
            result.insert(insertPosition, "110")
        }

        return result.toString()
    }
}
