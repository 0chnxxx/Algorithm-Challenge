/**
 * Given two integers left and right that represent the range [left, right], return the bitwise AND of all numbers in this range, inclusive.
 *
 * Constraints:
 * 0 <= left <= right <= 2^31 - 1
 */

fun main() {
    val left = 5
    val right = 7

    val result = Solution().rangeBitwiseAnd(left, right)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(1)
    // 공간 복잡도 : O(1)
    fun rangeBitwiseAnd(left: Int, right: Int): Int {
        var shift = 0
        var l = left
        var r = right

        // left와 right가 같아질 때까지 오른쪽으로 쉬프트
        while (l < r) {
            l = l shr 1
            r = r shr 1
            shift++
        }

        // left와 right가 같아지기 위한 shift 횟수만큼 left에 왼쪽으로 쉬프트
        return l shl shift
    }
}