/**
 * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
 *
 * 1 <= strs.length <= 104
 * 0 <= strs[i].length <= 100
 * strs[i] consists of lowercase English letters.
 */

fun main() {
    val strs = arrayOf("eat", "tea", "tan", "ate", "nat", "bat")

    val solution = Solution().groupAnagrams(strs)

    println(solution)
}

class Solution {
    // N = strs.size
    // K = 해시 생성
    // 시간 복잡도 : O(N * K)
    // 공간 복잡도 : O(N * K)
    fun groupAnagrams(strs: Array<String>): List<List<String>> {
        val map = mutableMapOf<String, MutableList<String>>()

        for (str in strs) {
            val count = IntArray(26)

            for (c in str) {
                count[c - 'a']++
            }

            val key = count.joinToString("#")

            map.getOrPut(key) { mutableListOf() }.add(str)
        }

        return map.values.toList()
    }

//    // N = strs.size
//    // K log K = sorted()
//    // 시간 복잡도 : O(N * K log K)
//    // 공간 복잡도 : O(N * K)
//    fun groupAnagrams(strs: Array<String>): List<List<String>> {
//        val map = mutableMapOf<String, MutableList<String>>()
//
//        for (i in strs.indices) {
//            val key = strs[i].toCharArray().sorted().joinToString("")
//
//            map.getOrPut(key) { mutableListOf() }.add(strs[i])
//        }
//
//        return map.values.toList()
//    }
}
