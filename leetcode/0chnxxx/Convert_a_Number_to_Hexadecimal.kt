/**
 * Given a 32-bit integer num, return a string representing its hexadecimal representation.
 * For negative integers, two’s complement method is used.
 * All the letters in the answer string should be lowercase characters, and there should not be any leading zeros in the answer except for the zero itself.
 * Note: You are not allowed to use any built-in library method to directly solve this problem.
 *
 * Constraints:
 * -2^31 <= num <= 2^31 - 1
 */

fun main() {
    val num = 26

    val result = Solution().toHex(num)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(1)
    // 공간 복잡도 : O(1)
    fun toHex(num: Int): String {
        if (num == 0) return "0"

        var n = num
        val sb = StringBuilder()
        val hexChars = "0123456789abcdef"

        // int는 32비트이기 때문에 최대 8자리 hex를 구함
        repeat(8) {
            // 오른쪽 4비트 추출
            val digit = n and 0xf

            sb.append(hexChars[digit])

            // 2의 보수 처리
            n = n ushr 4

            if (n == 0) return sb.reverse().toString()
        }

        return sb.reverse().toString()
    }
}