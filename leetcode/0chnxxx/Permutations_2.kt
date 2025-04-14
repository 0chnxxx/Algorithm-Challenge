/**
 * Given a collection of numbers, nums, that might contain duplicates, return all possible unique permutations in any order.
 *
 * 1 <= nums.length <= 8
 * -10 <= nums[i] <= 10
 */

fun main() {
    val nums = intArrayOf(1, 1, 2)

    val solution = Solution().permuteUnique(nums)

    println(solution)
}

class Solution {
    // 시간 복잡도 : O(k * N!)
    // 공간 복잡도 : O(k * N!)
    // Set이 아닌 sort()와 중복 체크를 통한 가지치기로 최적화
    fun permuteUnique(nums: IntArray): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        val used = BooleanArray(nums.size)

        // 중복 가지치기를 하기 위한 사전 정렬
        nums.sort()

        fun dfs(current: MutableList<Int>) {
            if (current.size == nums.size) {
                result.add(current.toList())
                return
            }

            for (i in nums.indices) {
                if (used[i]) continue

                // 사용하지 않은 원소 중 이전 원소가 중복된다면 가지치기
                if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue

                used[i] = true
                current.add(nums[i])
                dfs(current)
                current.removeAt(current.size - 1)
                used[i] = false
            }
        }

        dfs(mutableListOf())

        return result
    }

//    // 시간 복잡도 : O(N * N!)
//    // 공간 복잡도 : O(N * N!)
//    fun permuteUnique(nums: IntArray): List<List<Int>> {
//        val result = mutableSetOf<List<Int>>()
//        val used = BooleanArray(nums.size)
//
//        fun dfs(current: MutableList<Int>) {
//            if (current.size == nums.size) {
//                result.add(current.toList())
//                return
//            }
//
//            for (i in nums.indices) {
//                if (used[i]) continue
//
//                used[i] = true
//                current.add(nums[i])
//                dfs(current)
//                current.removeAt(current.size - 1)
//                used[i] = false
//            }
//        }
//
//        dfs(mutableListOf())
//
//        return result.toList()
//    }
}
