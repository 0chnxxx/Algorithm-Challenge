/**
 * Given a positive integer n, write a function that returns the number of set bits in its binary representation (also known as the Hamming weight).
 *
 * Constraints:
 * 1 <= n <= 2^31 - 1
 *
 * Follow up:
 * If this function is called many times, how would you optimize it?
 */

fun main() {
    val n = 11

    val result = Solution().hammingWeight(n)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(log N)
    // 공간 복잡도 : O(1)
    fun hammingWeight(n: Int): Int {
        var count = 0
        var num = n

        while (num != 0) {
            // 맨 오른쪽 비트가 1인지 확인
            count += num and 1
            // >>> 연산 (오른쪽으로 비트를 한 칸씩 미룸)
            num = num shr 1
        }

        return count
    }

//    // 시간 복잡도 : O(log N)
//    // 공간 복잡도 : O(log N)
//    fun hammingWeight(n: Int): Int {
//        val binary = n.toString(2)
//
//        return binary.count { it == '1' }
//    }
}