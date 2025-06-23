/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return all possible palindrome partitioning of s.
 *
 * Constraints:
 * 1 <= s.length <= 16
 * s contains only lowercase English letters.
 */

fun main() {
    val s = "aab"

    val solution = Solution().partition(s)

    println(solution)
}

class Solution {
    // 시간 복잡도 : O(N^2 + 2^N)
    // 공간 복잡도 : O(N^2 + N * 2^N)
    fun partition(s: String): List<List<String>> {
        val n = s.length
        val result = mutableListOf<List<String>>()
        val isPalindrome = Array(n) { BooleanArray(n) }

        // 팰린드롬 여부 DP
        for (end in 0 until n) {
            for (start in 0..end) {
                if (s[start] == s[end] && (end - start <= 2 || isPalindrome[start + 1][end - 1])) {
                    isPalindrome[start][end] = true
                }
            }
        }

        // 파티셔닝
        fun backtrack(start: Int, parts: MutableList<String>) {
            // 모든 문자를 파티셔닝하면 저장
            if (start == n) {
                result.add(parts.toList())
                return
            }

            // 백트래킹으로 파티셔닝
            for (end in start until n) {
                if (isPalindrome[start][end]) {
                    parts.add(s.substring(start, end + 1))
                    backtrack(end + 1, parts)
                    parts.removeAt(parts.size - 1)
                }
            }
        }

        backtrack(0, mutableListOf())

        return result
    }

//    // 시간 복잡도 : O(N * 2^N)
//    // 공간 복잡도 : O(N * 2^N)
//    fun partition(s: String): List<List<String>> {
//        val result = mutableListOf<List<String>>()
//
//        fun isPalindrome(part: String): Boolean {
//            return part == part.reversed()
//        }
//
//        fun backtrack(start: Int, parts: MutableList<String>) {
//            if (start == s.length) {
//                result.add(parts.toList())
//                return
//            }
//
//            for (end in start + 1..s.length) {
//                val part = s.substring(start, end)
//
//                if (isPalindrome(part)) {
//                    parts.add(part)
//                    backtrack(end, parts)
//                    parts.removeAt(parts.size - 1)
//                }
//            }
//        }
//
//        backtrack(0, mutableListOf())
//
//        return result
//    }
}
