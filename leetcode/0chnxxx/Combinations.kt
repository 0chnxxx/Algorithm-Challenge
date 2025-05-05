/**
 * Given two integers n and k, return all possible combinations of k numbers chosen from the range [1, n].
 *
 * You may return the answer in any order.
 *
 * Constraints:
 * 1 <= n <= 20
 * 1 <= k <= n
 */

fun main() {
    val n = 4
    val k = 2

    val solution = Solution().combine(n, k)

    solution.forEach { println(it.joinToString(", ")) }
}

class Solution {
    // 시간 복잡도 : O(C(N, K) * K)
    // 공간 복잡도 : O(C(N, K) * K)
    fun combine(n: Int, k: Int): List<List<Int>> {
        val result = mutableListOf<List<Int>>()

        fun dfs(start: Int, current: MutableList<Int>) {
            if (current.size == k) {
                result.add(current.toList())
                return
            }

            for (i in start..(n - (k - current.size) + 1)) {
                current.add(i)
                dfs(i + 1, current)
                current.removeAt(current.size - 1)
            }
        }

        dfs(1, mutableListOf())

        return result
    }

//    // 시간 복잡도 : O(C(N, K) * K)
//    // 공간 복잡도 : O(C(N, K) * K)
//    fun combine(n: Int, k: Int): List<List<Int>> {
//        val result = mutableListOf<List<Int>>()
//
//        fun dfs(start: Int, current: MutableList<Int>) {
//            if (current.size == k) {
//                result.add(current.toList())
//                return
//            }
//
//            for (i in start..n) {
//                current.add(i)
//                dfs(i + 1, current)
//                current.removeAt(current.size - 1)
//            }
//        }
//
//        dfs(1, mutableListOf())
//
//        return result
//    }

//    // 시간 복잡도 : O(N^K * K log K)
//    // 공간 복잡도 : O(C(N, K) * K)
//    fun combine(n: Int, k: Int): List<List<Int>> {
//        val nums = mutableListOf<Int>()
//        val result = mutableSetOf<List<Int>>()
//
//        nums.addAll(1..n)
//
//        fun dfs(current: MutableList<Int>) {
//            if (current.size == k) {
//                result.add(current.sorted().toList())
//                return
//            }
//
//            for (num in nums) {
//                if (!current.contains(num)) {
//                    current.add(num)
//                    dfs(current)
//                    current.removeAt(current.size - 1)
//                }
//            }
//        }
//
//        dfs(mutableListOf())
//
//        return result.map { it.toList() }
//    }
}
