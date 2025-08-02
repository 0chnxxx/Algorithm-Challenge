/**
 * Given two strings s and t, determine if they are isomorphic.
 * Two strings s and t are isomorphic if the characters in s can be replaced to get t.
 * All occurrences of a character must be replaced with another character while preserving the order of characters.
 * No two characters may map to the same character, but a character may map to itself.
 *
 * Constraints:
 * 1 <= s.length <= 5 * 10^4
 * t.length == s.length
 * s and t consist of any valid ascii character.
 */

fun main() {
    val s = "egg"
    val t = "add"

    val result = Solution().isIsomorphic(s, t)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun isIsomorphic(s: String, t: String): Boolean {
        // ASCII 코드를 통한 매핑
        val sMap = IntArray(128) { -1 }
        val tMap = IntArray(128) { -1 }

        for (i in s.indices) {
            // 해당 위치의 문자를 ASCII 코드로 변환
            val sChar = s[i].code
            val tChar = t[i].code

            // s -> t 매핑되지 않은 문자인 경우
            if (sMap[sChar] == -1) {
                // 매핑
                sMap[sChar] = tChar
            // 매핑된 문자인데 해당 위치에 다른 문자가 있는 경우
            } else if (sMap[sChar] != tChar) {
                // 얼리 리턴
                return false
            }

            // t -> s 매핑되지 않은 문자인 경우
            if (tMap[tChar] == -1) {
                // 매핑
                tMap[tChar] = sChar
            // 매핑된 문자인데 해당 위치에 다른 문자가 있는 경우
            } else if (tMap[tChar] != sChar) {
                // 얼리 리턴
                return false
            }
        }

        // 모두 통과한 경우 isomorphic
        return true
    }

//    // 시간 복잡도 : O(N)
//    // 공간 복잡도 : O(N)
//    fun isIsomorphic(s: String, t: String): Boolean {
//        val sMap = mutableMapOf<Char, Char>()
//        val tMap = mutableMapOf<Char, Char>()
//
//        for (i in s.indices) {
//            val sChar = s[i]
//            val tChar = t[i]
//
//            // s -> t 로 이미 매핑된 문자인지 확인
//            if (sMap.contains(sChar)) {
//                // 매핑된 문자인데 해당 위치에 다른 문자가 있다면 얼리 리턴
//                if (sMap[sChar] != tChar) return false
//            } else {
//                // 매핑되지 않은 문자면 매핑
//                sMap[sChar] = tChar
//            }
//
//            // t -> s 로 이미 매핑된 문자인지 확인
//            if (tMap.contains(tChar)) {
//                // 매핑된 문자인데 해당 위치에 다른 문자가 있다면 얼리 리턴
//                if (tMap[tChar] != sChar) return false
//            } else {
//                // 매핑되지 않은 문자면 매핑
//                tMap[tChar] = sChar
//            }
//        }
//
//        // 전부 통과할 경우 isomorphic
//        return true
//    }
}
