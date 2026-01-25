/**
 * Given an integer num, return a string of its base 7 representation.
 *
 * Constraints:
 * -10^7 <= num <= 10^7
 */

fun main() {
    val num = 100

    val result = Solution().convertToBase7(num)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(log |num|)
    // 공간 복잡도 : O(log |num|)
    fun convertToBase7(num: Int): String {
        return num.toString(7)
    }
}
