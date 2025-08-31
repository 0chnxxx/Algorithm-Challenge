/**
 * Given two strings s and t, return true if t is an anagram of s, and false otherwise.
 *
 * Constraints:
 * 1 <= s.length, t.length <= 5 * 10^4
 * s and t consist of lowercase English letters.
 *
 * Follow up:
 * What if the inputs contain Unicode characters? How would you adapt your solution to such a case?
 */

fun main() {
    val s = "anagram"
    val t = "nagaram"

    val result = Solution().isAnagram(s, t)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun isAnagram(s: String, t: String): Boolean {
        if (s.length != t.length) return false

        val counts = IntArray(26)

        for (i in s.indices) {
            counts[s[i] - 'a']++
            counts[t[i] - 'a']--
        }

        return counts.all { it == 0 }
    }

//    // 시간 복잡도 : O(n log n)
//    // 공간 복잡도 : O(N)
//    fun isAnagram(s: String, t: String): Boolean {
//        if (s.length != t.length) return false
//
//        val sortedS = s.split("").sorted()
//        val sortedT = t.split("").sorted()
//
//        if (sortedS != sortedT) return false
//
//        return true
//    }
}
