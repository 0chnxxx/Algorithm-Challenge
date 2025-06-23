import java.util.Locale.getDefault

/**
 * A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward.
 * Alphanumeric characters include letters and numbers.
 *
 * Given a string s, return true if it is a palindrome, or false otherwise.
 *
 * Constraints:
 * 1 <= s.length <= 2 * 105
 * s consists only of printable ASCII characters.
 */

fun main() {
    val s = "A man, a plan, a canal: Panama"

    val solution = Solution().isPalindrome(s)

    println(solution)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun isPalindrome(s: String): Boolean {
        var left = 0
        var right = s.length - 1

        // 앞, 뒤로 모든 문자열을 검사
        while (left < right) {
            // 앞, 뒤로 non-alphanumeric characters 라면 계속 포인터를 이동시키면서 무시
            while (left < right && !s[left].isLetterOrDigit()) left++
            while (left < right && !s[right].isLetterOrDigit()) right--

            // 앞, 뒤가 다른 문자라면 false를 얼리 리턴
            if (left < right && s[left].lowercaseChar() != s[right].lowercaseChar()) {
                return false
            }

            // 포인터 이동
            left++
            right--
        }

        // 모든 문자가 조건을 만족했을 때 true 반환
        return true
    }

//    // 시간 복잡도 : O(N)
//    // 공간 복잡도 : O(N)
//    fun isPalindrome(s: String): Boolean {
//        val lowercase = s.lowercase(getDefault())
//        val replace = lowercase.replace(Regex("[^A-Za-z0-9]"), "")
//
//        return replace == replace.reversed()
//    }
}