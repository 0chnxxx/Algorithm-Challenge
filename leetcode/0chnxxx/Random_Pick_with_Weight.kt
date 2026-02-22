import kotlin.random.Random

/**
 * You are given a 0-indexed array of positive integers w where w[i] describes the weight of the ith index.
 * You need to implement the function pickIndex(), which randomly picks an index in the range [0, w.length - 1] (inclusive) and returns it.
 * The probability of picking an index i is w[i] / sum(w).
 * For example, if w = [1, 3], the probability of picking index 0 is 1 / (1 + 3) = 0.25 (i.e., 25%), and the probability of picking index 1 is 3 / (1 + 3) = 0.75 (i.e., 75%).
 *
 * Constraints:
 * 1 <= w.length <= 10^4
 * 1 <= w[i] <= 10^5
 * pickIndex will be called at most 104 times.
 */

fun main() {
    val input = intArrayOf(1, 3)

    val solution = Solution(input)

    println(solution.pickIndex()) // return 1. It is returning the second element (index = 1) that has a probability of 3/4.
    println(solution.pickIndex()) // return 1
    println(solution.pickIndex()) // return 1
    println(solution.pickIndex()) // return 1
    println(solution.pickIndex()) // return 0. It is returning the first element (index = 0) that has a probability of 1/4.
}

class Solution(w: IntArray) {
    private val prefix = IntArray(w.size)
    private val totalSum: Int

    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(N)
    init {
        var sum = 0

        for (i in w.indices) {
            sum += w[i]
            prefix[i] = sum
        }

        totalSum = sum
    }

    // 시간 복잡도 : O(log N)
    // 공간 복잡도 : O(1)
    fun pickIndex(): Int {
        val target = Random.nextInt(1, totalSum + 1)

        var left = 0
        var right = prefix.lastIndex

        while (left < right) {
            val mid = (left + right) / 2

            if (prefix[mid] < target) {
                left = mid + 1
            } else {
                right = mid
            }
        }

        return left
    }
}