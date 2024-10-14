/**
 * 수확한 귤 중 k개를 골라 상자 하나에 담아 판매하려고 한다.
 * 귤을 크기별로 분류했을 때 서로 다른 종류의 수를 최소화하고 싶다.
 *
 * 1 <= k <= tangerine.length() <= 100000
 * 1 <= tangerine[i] <= 10000000
 */

class Solution {
    fun solution(k: Int, tangerine: IntArray): Int {
        val map = tangerine.groupBy { it }
        val array = map.entries.flatMap { listOf(it.value.size) }

        var group = 0
        var sum = 0

        for (i in array.sortedDescending()) {
            group += 1
            sum += i

            if (sum >= k) {
                break
            }
        }

        return group
    }
}

fun main(args: Array<String>) {
    val k = 4
    val tangerine = intArrayOf(1, 3, 2, 5, 4, 5, 2, 3)

    val result = Solution().solution(k, tangerine)

    println(result)
}
