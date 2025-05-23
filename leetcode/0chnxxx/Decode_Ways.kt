/**
 * You have intercepted a secret message encoded as a string of numbers.
 * The message is decoded via the following mapping:
 *
 * "1" -> 'A'
 * "2" -> 'B'
 * ...
 * "25" -> 'Y'
 * "26" -> 'Z'
 *
 * However, while decoding the message, you realize that there are many different ways you can decode the message because some codes are contained in other codes ("2" and "5" vs "25").
 *
 * For example, "11106" can be decoded into:
 *
 * - "AAJF" with the grouping (1, 1, 10, 6)
 * - "KJF" with the grouping (11, 10, 6)
 * - The grouping (1, 11, 06) is invalid because "06" is not a valid code (only "6" is valid).
 *
 * Note: there may be strings that are impossible to decode.
 *
 * Given a string s containing only digits, return the number of ways to decode it.
 * If the entire string cannot be decoded in any valid way, return 0.
 *
 * The test cases are generated so that the answer fits in a 32-bit integer.
 *
 * Constraints:
 * 1 <= s.length <= 100
 * s contains only digits and may contain leading zero(s).
 */

fun main() {
    val s = "12"

    val solution = Solution().numDecodings(s)

    println(solution)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(N)
    fun numDecodings(s: String): Int {
        // s 가 빈 문자열이거나 0으로 시작하는 경우는 유효하지 않음
        if (s.isEmpty() || s[0] == '0') return 0

        val n = s.length
        val dp = IntArray(n + 1)

        // 초기 상태
        dp[0] = 1
        // 첫번째 숫자인 s[0]은 디코딩 방식이 1가지뿐 (1~9)
        dp[1] = 1

        // 이후 두번째 숫자부터 끝까지 순회
        for (i in 2..n) {
            // 현재 위치에서 이전 한자리 숫자
            val one = s.substring(i - 1, i).toInt() // i - 1
            // 현재 위치에서 이전 두자리 숫자
            val two = s.substring(i - 2, i).toInt() // i - 2 ~ i - 1

            // 이전 한자리 숫자가 1 ~ 9 에 포함되는 경우
            if (one in 1..9) {
                // 디코딩이 가능하므로 가짓 수 추가
                dp[i] += dp[i - 1]
            }

            // 이전 두자리 숫자가 10 ~ 26 에 포함되는 경우
            if (two in 10..26) {
                // 디코딩이 가능하므로 가짓 수 추가
                dp[i] += dp[i - 2]
            }
        }

        // 최종적으로 누적된 가짓 수 반환
        return dp[n]
    }
}
