/**
 * Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sum to target.
 *
 * Each number in candidates may only be used once in the combination.
 *
 * Note: The solution set must not contain duplicate combinations.
 *
 * 1 <= candidates.length <= 100
 * 1 <= candidates[i] <= 50
 * 1 <= target <= 30
 */

fun main() {
    val candidates = intArrayOf(10, 1, 2, 7, 6, 1, 5)
    val target = 8

    val solution = Solution().combinationSum2(candidates, target)

    println(solution)
}

class Solution {
    // 시간 복잡도 : O(2^N)
    // 공간 복잡도 : O(2^N * N)
    fun combinationSum2(candidates: IntArray, target: Int): List<List<Int>> {
        // 정렬을 통한 연산 횟수 최소화
        candidates.sort()

        val result = mutableListOf<List<Int>>()

        fun dfs(index: Int, current: MutableList<Int>, sum: Int) {
            // 원하는 조건 확인
            if (sum == target) {
                result.add(current.toList())
                return
            }

            for (i in index until candidates.size) {
                // 중복된 원소를 사용하는 경우 가지치기
                if (i > index && candidates[i] == candidates[i - 1]) continue

                val newSum = sum + candidates[i]

                // target을 초과하는 경우 가지치기
                if (newSum > target) break

                // 백트래킹을 통한 모든 조합 계산
                current.add(candidates[i])
                dfs(i + 1, current, newSum)
                current.removeAt(current.size - 1)
            }
        }

        dfs(0, mutableListOf(), 0)

        return result
    }
}
