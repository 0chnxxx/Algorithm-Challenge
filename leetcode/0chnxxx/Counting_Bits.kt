/**
 * Given an integer n, return an array ans of length n + 1 such that for each i (0 <= i <= n), ans[i] is the number of 1's in the binary representation of i.
 *
 * Follow up:
 * It is very easy to come up with a solution with a runtime of O(n log n). Can you do it in linear time O(n) and possibly in a single pass?
 * Can you do it without using any built-in function (i.e., like __builtin_popcount in C++)?
 */

fun main() {
    val n = 5

    val result = Solution().countBits(n)

    println(result.joinToString(", "))
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(N)
    fun countBits(n: Int): IntArray {
        val array = IntArray(n + 1)

        for (i in 1..n) {
            array[i] = array[i shr 1] + (i and 1)
        }

        return array
    }

//    // 시간 복잡도 : O(N log N)
//    // 공간 복잡도 : O(N)
//    fun countBits(n: Int): IntArray {
//        val array = IntArray(n + 1)
//
//        for (i in 0..n) {
//            array[i] = i.toString(2).count { it == '1' }
//        }
//
//        return array
//    }
}
