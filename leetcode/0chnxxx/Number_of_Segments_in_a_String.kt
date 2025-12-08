/**
 * Given a string s, return the number of segments in the string.
 * A segment is defined to be a contiguous sequence of non-space characters.
 *
 * Constraints:
 * 0 <= s.length <= 300
 * s consists of lowercase and uppercase English letters, digits, or one of the following characters "!@#$%^&*()_+-=',.:".
 * The only space character in s is ' '.
 */

fun main() {
    val s = "Hello, my name is John"

    val result = Solution().countSegments(s)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(N)
    fun countSegments(s: String): Int {
        val string = s.trim()

        if (string.isEmpty()) return 0

        return string.split(Regex("\\s+")).filter { it.isNotEmpty() }.size
    }
}
