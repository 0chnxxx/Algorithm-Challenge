/**
 * Given a string s, remove duplicate letters so that every letter appears once and only once.
 * You must make sure your result is the smallest in lexicographical order among all possible results.
 *
 * Constraints:
 * 1 <= s.length <= 10^4
 * s consists of lowercase English letters.
 *
 *
 * Note:
 * This question is the same as 1081:
 * https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/
 */

fun main() {
    val s = "bcabc"

    val result = Solution().removeDuplicateLetters(s)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun removeDuplicateLetters(s: String): String {
        // 문자의 등장 횟수를 셈
        val count = IntArray(26)

        for (c in s) {
            val index = c - 'a'

            count[index]++
        }

        val visited = BooleanArray(26)
        val stack = ArrayDeque<Char>()

        for (c in s) {
            val index = c - 'a'

            count[index]--

            // 이미 방문한 문자라면 패스
            if (visited[index]) continue

            // 스택이 비어있지 않고
            // 스택의 top이 c보다 사전순으로 뒤에 있고
            // 스택의 top이 아직 남았다면
            while (stack.isNotEmpty() && stack.last() > c && count[stack.last() - 'a'] > 0) {
                // 방문 처리를 하지 않고 pop한채로 넘어감
                visited[stack.removeLast() - 'a'] = false
            }

            // push 및 방문처리
            stack.addLast(c)
            visited[index] = true
        }

        return stack.joinToString("")
    }
}
