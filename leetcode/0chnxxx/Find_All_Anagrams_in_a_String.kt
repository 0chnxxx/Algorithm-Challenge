/**
 * Given two strings s and p, return an array of all the start indices of p's anagrams in s.
 * You may return the answer in any order.
 *
 * Constraints:
 * 1 <= s.length, p.length <= 3 * 10^4
 * s and p consist of lowercase English letters.
 */

fun main() {
    val s = "cbaebabacd"
    val p = "abc"

    val result = Solution().findAnagrams(s, p)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun findAnagrams(s: String, p: String): List<Int> {
        val result = mutableListOf<Int>()

        if (s.length < p.length) return result

        val pCount = IntArray(26)
        val sCount = IntArray(26)

        for (ch in p) {
            pCount[ch - 'a']++
        }

        val window = p.length

        for (i in 0 until window) {
            sCount[s[i] - 'a']++
        }

        // 0번 인덱스 윈도우 검사
        if (sCount contentEquals pCount) result.add(0)

        // 슬라이딩 윈도우 검사
        for (i in window until s.length) {
            sCount[s[i] - 'a']++
            sCount[s[i - window] - 'a']--

            if (sCount contentEquals pCount) result.add(i - window + 1)
        }

        return result
    }
}
