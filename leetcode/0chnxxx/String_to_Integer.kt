/**
 * Implement the myAtoi(string s) function, which converts a string to a 32-bit signed integer.
 *
 * The algorithm for myAtoi(string s) is as follows:
 *
 * 1. Whitespace: Ignore any leading whitespace (" ").
 * 2. Signedness: Determine the sign by checking if the next character is '-' or '+', assuming positivity if neither present.
 * 3. Conversion: Read the integer by skipping leading zeros until a non-digit character is encountered or the end of the string is reached. If no digits were read, then the result is 0.
 * 4. Rounding: If the integer is out of the 32-bit signed integer range [-2^31, 2^31 - 1], then round the integer to remain in the range. Specifically, integers less than -2^31 should be rounded to -2^31, and integers greater than 2^31 - 1 should be rounded to 2^31 - 1.
 *
 * Return the integer as the final result.
 *
 * 0 <= s.length <= 200
 * s consists of English letters (lower-case and upper-case), digits (0-9), ' ', '+', '-', and '.'.
 */

fun main() {
    val s = ""

    val solution = Solution().myAtoi(s)

    println(solution)
}

class Solution {
    // 최적화 풀이
    fun myAtoi(s: String): Int {
        // 공백 제거
        val trimmed = s.trim()

        // "", " " 와 같은 빈 문자열 입력 얼리 리턴
        if (trimmed.isEmpty()) return 0

        var index = 0
        var sign = 1

        // 부호 찾기 (맨 앞에 부호가 없으면 양수로 간주하고 그 뒤 부호는 문자로 인지)
        if (trimmed[index] == '+' || trimmed[index] == '-') {
            if (trimmed[index] == '-') sign = -1
            index++
        }

        var result = 0L

        // 문자열을 왼쪽부터 차례대로 순회
        // isDigit() 을 통해 숫자가 아니라면 종료
        while (index < trimmed.length && trimmed[index].isDigit()) {
            // 기존 숫자에 10 씩 곱해서 자릿수를 밀어낸 후 더함
            result = result * 10 + (trimmed[index].digitToInt())

            // 오버플로우, 언더플로우 예외 처리
            if (result * sign <= Int.MIN_VALUE) return Int.MIN_VALUE
            if (result * sign >= Int.MAX_VALUE) return Int.MAX_VALUE

            index++
        }

        return (result * sign).toInt()
    }

//    // O(N) 풀이
//    fun myAtoi(s: String): Int {
//        // 공백 제거
//        val trimmedString = s.trim()
//        var isPositive = true
//        var isNumberAtFirst = true
//
//        if (trimmedString.isEmpty()) return 0
//
//        // 문자로 시작하는 경우는 변환 중지
//        if ("[a-zA-Z\\.]".toRegex().matches(trimmedString[0].toString())) {
//            return 0
//        }
//
//        // 부호 찾기
//        if ("[\\-+]".toRegex().matches(trimmedString[0].toString())) {
//            isNumberAtFirst = false
//
//            when (trimmedString[0]) {
//                '+' -> isPositive = true
//                '-' -> isPositive = false
//            }
//        }
//
//        // 숫자 변환
//        val builder = StringBuilder()
//        val index = if (isNumberAtFirst) 0 else 1
//
//        for (i in index until trimmedString.length) {
//            val char = trimmedString[i]
//
//            // 도중에 문자 나오면 변환 종료
//            if ("[a-zA-Z\\-+.\\s]".toRegex().matches(char.toString())) break
//
//            builder.append(char)
//        }
//
//        var string = if (isPositive) builder.toString() else "-$builder"
//
//        string = if (string.isEmpty() || (string.length == 1 && string.contains('-'))) "0" else string
//
//        var number = 0
//
//        // Int 범위 벗어나면 라운딩
//        try {
//            number = string.toInt()
//        } catch (e: NumberFormatException) {
//            number = if (e.message?.contains('-') == true) -2147483648 else 2147483647
//        }
//
//        return number
//    }
}
