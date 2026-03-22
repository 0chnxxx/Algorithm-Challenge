/**
 * You have a long flowerbed in which some of the plots are planted, and some are not.
 * However, flowers cannot be planted in adjacent plots.
 * Given an integer array flowerbed containing 0's and 1's, where 0 means empty and 1 means not empty, and an integer n, return true if n new flowers can be planted in the flowerbed without violating the no-adjacent-flowers rule and false otherwise.
 *
 * Constraints:
 * 1 <= flowerbed.length <= 2 * 10^4
 * flowerbed[i] is 0 or 1.
 * There are no two adjacent flowers in flowerbed.
 * 0 <= n <= flowerbed.length
 */

fun main() {
    val flowerbed = intArrayOf(1, 0, 0, 0, 1)
    val n = 1

    val result = Solution().canPlaceFlowers(flowerbed, n)

    print(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun canPlaceFlowers(flowerbed: IntArray, n: Int): Boolean {
        var count = 0

        for (i in flowerbed.indices) {
            if (flowerbed[i] == 0) {
                val left = if (i == 0) 0 else flowerbed[i - 1]
                val right = if (i == flowerbed.lastIndex) 0 else flowerbed[i + 1]

                if (left == 0 && right == 0) {
                    flowerbed[i] = 1
                    count++

                    if (count >= n) return true
                }
            }
        }

        return count >= n
    }
}
