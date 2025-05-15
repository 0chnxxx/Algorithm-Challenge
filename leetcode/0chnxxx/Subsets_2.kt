/**
 * Given an integer array nums that may contain duplicates, return all possible subsets (the power set).
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 *
 * Constraints:
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 */

fun main() {
    val nums = intArrayOf(1, 2, 2)

    val solution = Solution().subsetsWithDup(nums)

    println(solution)
}

class Solution {
    // 시간 복잡도 : O(2^N)
    // 공간 복잡도 : O(N * 2^N)
    fun subsetsWithDup(nums: IntArray): List<List<Int>> {
        // 중복 제거를 하기 위한 오름차순 정렬
        nums.sort()

        val result = mutableListOf<List<Int>>()
        val current = mutableListOf<Int>()

        fun backtrack(index: Int) {
            result.add(current.toList())

            for (i in index until nums.size) {
                // 인덱스 범위 초과 및 중복 확인
                if (i > index && nums[i] == nums[i - 1]) continue

                current.add(nums[i])
                backtrack(i + 1)
                current.removeAt(current.size - 1)
            }
        }

        backtrack(0)

        return result
    }
}