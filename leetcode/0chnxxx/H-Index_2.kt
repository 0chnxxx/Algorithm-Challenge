/**
 * Given an array of integers citations where citations[i] is the number of citations a researcher received for their ith paper and citations is sorted in non-descending order, return the researcher's h-index.
 *
 * According to the definition of h-index on Wikipedia:
 * The h-index is defined as the maximum value of h such that the given researcher has published at least h papers that have each been cited at least h times.
 *
 * You must write an algorithm that runs in logarithmic time.
 *
 * Constraints:
 * n == citations.length
 * 1 <= n <= 10^5
 * 0 <= citations[i] <= 1000
 * citations is sorted in ascending order.
 */

fun main() {
    val citations = intArrayOf(0, 1, 3, 5, 6)

    val result = Solution().hIndex(citations)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(log N)
    // 공간 복잡도 : O(1)
    fun hIndex(citations: IntArray): Int {
        val n = citations.size
        var left = 0
        var right = n - 1
        var h = 0

        // 이진 탐색으로 좁혀감
        while (left <= right) {
            // 중간 인덱스를 찾음
            val mid = (left + right) / 2
            // 남은 논문 수
            val papers = n - mid

            // 인용 수가 남은 논문 수와 같거나 큰 경우 H-Index 조건을 만족
            if (citations[mid] >= papers) {
                h = papers
                right = mid - 1
            } else {
                left = mid + 1
            }
        }

        return h
    }
}