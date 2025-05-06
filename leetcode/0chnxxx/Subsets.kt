/**
 * Given an integer array nums of unique elements, return all possible subsets (the power set).
 *
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 *
 * Constraints:
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 * All the numbers of nums are unique.
 */

fun main() {
    val nums = intArrayOf(1, 2, 3)

    val solution = Solution().subsets(nums)

    println(solution)
}

class Solution {
    // 시간 복잡도 : O(2^N)
    // 공간 복잡도 : O(N * 2^N)
    fun subsets(nums: IntArray): List<List<Int>> {
        val result = mutableListOf<List<Int>>()

        fun dfs(index: Int, current: MutableList<Int>) {
            // 모든 자리를 다 사용한 경우 결과 추가 후 리턴
            if (index == nums.size) {
                result.add(current.toList())
                return
            }

            // 숫자를 추가하지 않는 경우
            dfs(index + 1, current)

            // 숫자를 추가하는 경우
            current.add(nums[index])
            dfs(index + 1, current)
            current.removeAt(current.size - 1)
        }

        dfs(0, mutableListOf())

        return result
    }

//    // 시간 복잡도 : O(N^2 * 2^N)
//    // 공간 복잡도 : O(N * 2^N)
//    fun subsets(nums: IntArray): List<List<Int>> {
//        val result = mutableSetOf<List<Int>>()
//
//        result.add(mutableListOf())
//
//        fun dfs(index: Int, current: MutableList<Int>) {
//            if (current.size > nums.size) {
//                return
//            }
//
//            result.add(current.distinct().sorted().toList())
//
//            for (num in nums) {
//                current.add(num)
//                dfs(index + 1, current)
//                current.removeAt(current.size - 1)
//            }
//        }
//
//        for (num in nums) {
//            dfs(0, mutableListOf(num))
//        }
//
//        return result.toList()
//    }
}
