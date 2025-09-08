/**
 * Given an array of integers citations where citations[i] is the number of citations a researcher received for their ith paper, return the researcher's h-index.
 *
 * According to the definition of h-index on Wikipedia:
 * The h-index is defined as the maximum value of h such that the given researcher has published at least h papers that have each been cited at least h times.
 *
 * Constraints:
 * n == citations.length
 * 1 <= n <= 5000
 * 0 <= citations[i] <= 1000
 */

fun main() {
    val citations = intArrayOf(3, 0, 6, 1, 5)

    val result = Solution().hIndex(citations)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(N)
    fun hIndex(citations: IntArray): Int {
        val n = citations.size
        val count = IntArray(n + 1)

        for (c in citations) {
            if (c >= n) {
                count[n]++
            } else {
                count[c]++
            }
        }

        var papers = 0

        for (i in n downTo 0) {
            papers += count[i]

            if (papers >= i) {
                return i
            }
        }

        return 0
    }

//    // 시간 복잡도 : O(N log N)
//    // 공간 복잡도 : O(N)
//    fun hIndex(citations: IntArray): Int {
//        val sorted = citations.sortedDescending()
//        var h = 0
//
//        for (i in sorted.indices) {
//            if (sorted[i] >= i + 1) {
//                h = i + 1
//            } else {
//                break
//            }
//        }
//
//        return h
//    }
}