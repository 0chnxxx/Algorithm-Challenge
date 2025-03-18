/**
 * Write a function to find the longest common prefix string amongst an array of strings.
 *
 * If there is no common prefix, return an empty string "".
 *
 * 1 <= strs.length <= 200
 * 0 <= strs[i].length <= 200
 * strs[i] consists of only lowercase English letters if it is non-empty.
 */

fun main() {
    val strs = arrayOf("flower", "flow", "flight")

    val solution = Solution().longestCommonPrefix(strs)

    println(solution)
}

class Solution {
    // O(N) 풀이
    fun longestCommonPrefix(strs: Array<String>): String {
        if (strs.isEmpty()) return ""

        var prefix = strs[0]

        for (i in 1 until strs.size) {
            while (!strs[i].startsWith(prefix)) {
                prefix = prefix.substring(0, prefix.length - 1)

                if (prefix.isEmpty()) return ""
            }
        }

        return prefix
    }

//    // O(N * log minLength) 풀이
//    fun longestCommonPrefix(strs: Array<String>): String {
//        if (strs.isEmpty()) return ""
//
//        var left = 0
//        var right = strs.minOf { it.length }
//
//        while (left <= right) {
//            val mid = (left + right) / 2
//            val prefix = strs[0].substring(0, mid)
//
//            if (strs.all { it.startsWith(prefix) }) {
//                left = mid + 1
//            } else {
//                right = mid - 1
//            }
//        }
//
//        return strs[0].substring(0, (left + right) / 2)
//    }

//    // O(minLength * N) 풀이
//    fun longestCommonPrefix(strs: Array<String>): String {
//        val builder = StringBuilder()
//        val minLength = strs.minOf { it.length }
//
//        for (i in 0 until minLength) {
//            if (strs.map { it[i] }.toSet().size == 1) {
//                builder.append(strs[0][i])
//            } else {
//                break
//            }
//        }
//
//        return builder.toString()
//    }
}
