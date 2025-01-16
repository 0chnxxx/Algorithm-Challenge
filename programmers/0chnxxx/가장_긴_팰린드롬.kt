/**
 * 앞뒤를 뒤집어도 똑같은 문자열을 팰린드롬이라고 합니다.
 * 문자열 s가 주어질 때, s의 부분 문자열 중 가장 긴 팰린드롬의 길이를 return 하는 solution 함수를 완성해 주세요.
 *
 * 예를들면, 문자열 s가 "abcdcba"이면 7일 return하고 "abacde"이면 3을 return합니다.
 *
 * 1 <= s의 길이 <= 2500
 */

fun main() {
    val s = "abcdcba"

    val solution = Solution().solution(s)

    println(solution)
}

class Solution {
    fun solution(s: String): Int {
        var result = 0

        fun search(left: Int, right: Int): Int {
            var l = left
            var r = right

            while (l >= 0 && r < s.length && s[l] == s[r]) {
                l--
                r++
            }

            return r - l - 1
        }

        for (i in s.indices) {
            result = maxOf(result, search(i, i))
            result = maxOf(result, search(i, i + 1))
        }

        return result
    }
}
