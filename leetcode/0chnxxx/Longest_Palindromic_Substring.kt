/**
 * Given a string s, return the longest palindromic substring in s.
 *
 * 1 <= s.length <= 1000
 */

fun main() {
    val s = "babad"

    val solution = Solution().longestPalindrome(s)

    println(solution)
}

class Solution {
    // O(N^2) 풀이
    fun longestPalindrome(s: String): String {
        val n = s.length

        // 길이가 1인 문자는 그 자체가 팰린드롬
        if (n <= 1) return s

        var start = 0
        var maxLength = 1
        val dp = Array(n) { BooleanArray(n) }

        // 모든 단일 문자는 팰린드롬
        for (i in 0 until n) dp[i][i] = true

        // 길이 2 부터 모든 길이 검사
        for (length in 2..n) {
            // length 단위만큼 슬라이딩 윈도우를 만들어서 검사
            for (left in 0..n - length) {
                val right = left + length - 1

                // 외곽의 문자가 팰린드롬인지 확인
                if (s[left] == s[right]) {
                    // 외곽 문자가 팰린드롬일 때 내부 문자도 팰린드롬이면 전체는 팰린드롬
                    dp[left][right] = if (length == 2) true else dp[left + 1][right - 1]

                    // 팰린드롬 문자면서 길이가 더 길다면 갱신
                    if (dp[left][right] && length > maxLength) {
                        start = left
                        maxLength = length
                    }
                }
            }
        }

        return s.substring(start, start + maxLength)
    }

//    // O(N^3) 풀이
//    fun longestPalindrome(s: String): String {
//        var longestSubstring = ""
//
//        for (left in s.indices) {
//            for (right in left + 1 until s.length + 1) {
//                val substring = s.substring(left, right)
//
//                if (substring != substring.reversed()) continue
//
//                if (longestSubstring.length < substring.length) {
//                    longestSubstring = substring
//                }
//            }
//        }
//
//        return longestSubstring
//    }
}
