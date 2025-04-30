/**
 * You are climbing a staircase.
 * It takes n steps to reach the top.
 *
 * Each time you can either climb 1 or 2 steps.
 * In how many distinct ways can you climb to the top?
 *
 * 1 <= n <= 45
 */

fun main() {
    val n = 2

    val solution = Solution().climbStairs(n)

    println(solution)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun climbStairs(n: Int): Int {
        // 기저 조건
        if (n <= 2) return n

        // f(1)
        var first = 1
        // f(2)
        var second = 2

        for (i in 3..n) {
            // f(i) = f(i - 1) + f(i - 2)
            val third = first + second

            // 스왑
            first = second
            second = third
        }

        // f(n) 반환
        return second
    }

//    // 시간 복잡도 : O(2^N)
//    // 공간 복잡도 : O(2^N)
//    fun climbStairs(n: Int): Int {
//        val result = mutableSetOf<List<Int>>()
//
//        fun backtrack(current: MutableList<Int>) {
//            if (current.sum() == n) {
//                result.add(current)
//                return
//            }
//
//            if (current.sum() < n) {
//                backtrack(current.toMutableList().also { it.add(1) })
//                backtrack(current.toMutableList().also { it.add(2) })
//            }
//        }
//
//        backtrack(mutableListOf(1))
//        backtrack(mutableListOf(2))
//
//        return result.size
//    }
}
