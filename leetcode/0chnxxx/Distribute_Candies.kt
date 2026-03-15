/**
 * Alice has n candies, where the ith candy is of type candyType[i].
 * Alice noticed that she started to gain weight, so she visited a doctor.
 * The doctor advised Alice to only eat n / 2 of the candies she has (n is always even).
 * Alice likes her candies very much, and she wants to eat the maximum number of different types of candies while still following the doctor's advice.
 * Given the integer array candyType of length n, return the maximum number of different types of candies she can eat if she only eats n / 2 of them.
 *
 * Constraints:
 * n == candyType.length
 * 2 <= n <= 10^4
 * n is even.
 * -10^5 <= candyType[i] <= 10^5
 */

fun main() {
    val candyType = intArrayOf(1, 1, 2, 2, 3, 3)

    val result = Solution().distributeCandies(candyType)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(N)
    fun distributeCandies(candyType: IntArray): Int {
        val count = candyType.toSet().size
        val max = candyType.size / 2

        return minOf(count, max)
    }
}