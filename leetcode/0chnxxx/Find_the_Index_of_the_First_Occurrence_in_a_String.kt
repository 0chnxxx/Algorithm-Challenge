/**
 * Given two strings needle and haystack, return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 *
 * 1 <= haystack.length, needle.length <= 104
 * haystack and needle consist of only lowercase English characters.
 */

fun main() {
    val haystack = "sadbutsad"
    val needle = "sad"

    val solution = Solution().strStr(haystack, needle)

    println(solution)
}

class Solution {
    // 시간 복잡도 : O(N + M)
    // 공간 복잡도 : O(1)
    fun strStr(haystack: String, needle: String): Int {
        if (needle.isEmpty()) return 0

        // needle의 각 위치에서 일치하는 문자의 최대 길이를 저장
        // 이를 건너뛰는데 사용
        val lps = IntArray(needle.length)
        var length = 0
        var index = 1

        // needle 전체를 순회
        while (index < needle.length) {
            if (needle[index] == needle[length]) { // 문자가 일치하는 경우
                length++ // 길이를 증가시켜 셈
                lps[index] = length // 길이 변경
                index++ // 다음 문자 탐색
            } else { // 문자가 불일치하는 경우
                if (length != 0) { // 도중에 불일치하는 경우
                    length = lps[length - 1] // 다음 지점으로 점프
                } else { // 처음부터 불일치하는 경우
                    lps[index] = 0 // 길이 변경
                    index++ // 다음 문자 탐색
                }
            }
        }

        var i = 0
        var j = 0

        // haystack 전체 순회
        while (i < haystack.length) {
            if (haystack[i] == needle[j]) { // 문자가 일치하는 경우
                i++ // haystack 문자 이동
                j++ // needle 문자 이동
                if (j == needle.length) return i - j // needle 끝까지 탐색한 경우엔 패턴 매칭 성공
            } else { // 문자가 불일치하는 경우
                if (j != 0) { // 도중에 불일치하는 경우
                    j = lps[j - 1] // 다음 지점으로 점프
                } else { // 처음부터 불일치하는 경우
                    i++ // 다음 문자 탐색
                }
            }
        }

        return -1
    }

//    // 시간 복잡도 : O(NM)
//    // 공간 복잡도 : O(1)
//    fun strStr(haystack: String, needle: String): Int {
//        return haystack.indexOf(needle)
//    }
}
