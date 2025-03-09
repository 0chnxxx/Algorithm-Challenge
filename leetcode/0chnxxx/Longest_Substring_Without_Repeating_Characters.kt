/**
 * Given a string s, find the length of the longest substring without duplicate characters.
 *
 * 0 <= s.length <= 5 * 10^4
 */

fun main() {
    val s = "pwwkew"

    val solution = Solution().lengthOfLongestSubstring(s)

    println(solution)
}

class Solution {
    // O(N) 풀이
    fun lengthOfLongestSubstring(s: String): Int {
        val seen = mutableSetOf<Char>()
        var left = 0
        var maxLength = 0

        // right를 넓혀가며 substring 찾기
        for (right in s.indices) {
            // 중복이 발생하면 중복된 문자를 seen에서 제거 후 left를 증가시켜 윈도우 이동
            // abca -> a, b, c, a
            // seen엔 a, b, c 가 들어있고 마지막 a에서 중복 발생하므로 left에 해당하는 맨 왼쪽을 제거 후 윈도우 이동
            while (s[right] in seen) {
                seen.remove(s[left])
                left++
            }

            seen.add(s[right])
            maxLength = maxOf(maxLength, right - left + 1)
        }

        return maxLength
    }

//    // O(N^3) 풀이
//    fun lengthOfLongestSubstring(s: String): Int {
//        // 주어진 s에서 중복된 문자 없이 가장 긴 서브스트링의 길이를 찾아라
//        if (s.isEmpty()) return 0
//
//        val set = mutableSetOf<String>()
//
//        fun dfs(start: Int, end: Int) {
//            if (end > s.length) return
//
//            val substring = s.substring(start, end)
//
//            if (substring.length == substring.toSet().size) {
//                set.add(substring)
//            } else {
//                return
//            }
//
//            dfs(start, end + 1)
//        }
//
//        for (i in s.indices) {
//            dfs(i, i + 1)
//        }
//
//        return set.maxOf { it.length }
//    }
}
