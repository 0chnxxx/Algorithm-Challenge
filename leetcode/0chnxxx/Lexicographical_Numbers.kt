/**
 * Given an integer n, return all the numbers in the range [1, n] sorted in lexicographical order.
 * You must write an algorithm that runs in O(n) time and uses O(1) extra space.
 *
 * Constraints:
 * 1 <= n <= 5 * 10^4
 */

fun main() {
    val n = 13

    val result = Solution().lexicalOrder(n)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun lexicalOrder(n: Int): List<Int> {
        val result = mutableListOf<Int>()
        var current = 1

        repeat(n) {
            result.add(current)

            if (current * 10 <= n) {
                current *= 10
            } else {
                while (current % 10 == 9 || current + 1 > n) {
                    current /= 10
                }

                current += 1
            }
        }

        return result
    }
}
