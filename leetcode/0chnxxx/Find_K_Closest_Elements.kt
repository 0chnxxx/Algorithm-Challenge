/**
 * Given a sorted integer array arr, two integers k and x, return the k closest integers to x in the array.
 * The result should also be sorted in ascending order.
 *
 * An integer a is closer to x than an integer b if:
 * |a - x| < |b - x|, or
 * |a - x| == |b - x| and a < b
 *
 * Constraints:
 * 1 <= k <= arr.length
 * 1 <= arr.length <= 10^4
 * arr is sorted in ascending order.
 * -10^4 <= arr[i], x <= 10^4
 */

fun main() {
    val arr = intArrayOf(1, 2, 3, 4, 5)
    val k = 4
    val x = 3

    val result = Solution().findClosestElements(arr, k, x)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(log N)
    // 공간 복잡도 : O(K)
    fun findClosestElements(arr: IntArray, k: Int, x: Int): List<Int> {
        var left = 0
        var right = arr.size - k

        while (left < right) {
            val mid = (left + right) / 2

            if (x - arr[mid] > arr[mid + k] - x) {
                left = mid + 1
            } else {
                right = mid
            }
        }

        return arr.slice(left until left + k)
    }
}
