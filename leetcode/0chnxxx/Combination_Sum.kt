import java.util.*

/**
 * Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations of candidates where the chosen numbers sum to target.
 * You may return the combinations in any order.
 *
 * The same number may be chosen from candidates an unlimited number of times.
 * Two combinations are unique if the frequency of at least one of the chosen numbers is different.
 *
 * The test cases are generated such that the number of unique combinations that sum up to target is less than 150 combinations for the given input.
 *
 * 1 <= candidates.length <= 30
 * 2 <= candidates[i] <= 40
 * All elements of candidates are distinct.
 * 1 <= target <= 40
 */

fun main() {
    val candidates = intArrayOf(2, 3, 6, 7)
    val target = 7

    val solution = Solution().combinationSum(candidates, target)

    println(solution)
}

class Solution {
    // target / candidates[0] = T
    // 시간 복잡도 : O(N^T)
    // 공간 복잡도 : O(T)
    // 누적합 변수를 사용하여 O(N)의 current.sum() 메서드 호출을 줄이면서 최적화
    fun combinationSum(candidates: IntArray, target: Int): List<List<Int>> {
        val result = mutableListOf<List<Int>>()

        fun dfs(index: Int, current: MutableList<Int>, sum: Int) {
            if (sum > target) {
                return
            }

            if (sum == target) {
                result.add(current.toList())
                return
            }

            for (i in index until candidates.size) {
                current.add(candidates[i])
                dfs(i, current, sum + candidates[i])
                current.removeAt(current.size - 1)
            }
        }

        dfs(0, mutableListOf(), 0)

        return result
    }

//    // target / candidates[0] = T
//    // 시간 복잡도 : O(N^T * T)
//    // 공간 복잡도 : O(T)
//    fun combinationSum(candidates: IntArray, target: Int): List<List<Int>> {
//        val result = mutableListOf<List<Int>>()
//
//        fun dfs(index: Int, current: MutableList<Int>) {
//            if (current.sum() > target) {
//                return
//            }
//
//            if (current.sum() == target) {
//                result.add(current.toList())
//                return
//            }
//
//            for (i in index until candidates.size) {
//                current.add(candidates[i])
//                dfs(i, current)
//                current.removeAt(current.size - 1)
//            }
//        }
//
//        dfs(0, mutableListOf())
//
//        return result
//    }
}
