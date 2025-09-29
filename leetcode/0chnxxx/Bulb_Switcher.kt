import kotlin.math.sqrt

/**
 * There are n bulbs that are initially off.
 * You first turn on all the bulbs, then you turn off every second bulb.
 * On the third round, you toggle every third bulb (turning on if it's off or turning off if it's on).
 * For the ith round, you toggle every i bulb.
 * For the nth round, you only toggle the last bulb.
 * Return the number of bulbs that are on after n rounds.
 *
 * Constraints:
 * 0 <= n <= 10^9
 */

fun main() {
    val n = 3

    val result = Solution().bulbSwitch(n)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(1)
    // 공간 복잡도 : O(1)
    fun bulbSwitch(n: Int): Int {
        return sqrt(n.toDouble()).toInt()
    }
}
