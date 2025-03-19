/**
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
 *
 * Notice that the solution set must not contain duplicate triplets.
 *
 * 3 <= nums.length <= 3000
 * -10^5 <= nums[i] <= 10^5
 */

fun main() {
    val nums = intArrayOf(-1, 0, 1, 2, -1, -4)

    val solution = Solution().threeSum(nums)

    println(solution)
}

class Solution {
    // 최적화 O(N^2) 풀이
    fun threeSum(nums: IntArray): List<List<Int>> {
        val result = mutableListOf<List<Int>>()

        nums.sort()

        for (i in nums.indices) {
            // 정렬을 했는데 첫번째 숫자가 0보다 큰 경우엔 0을 만들 수 없음 (조기 종료)
            if (nums[i] > 0) break
            // 중복된 숫자 건너뜀
            if (i > 0 && nums[i] == nums[i - 1]) continue

            var left = i + 1
            var right = nums.size - 1

            while (left < right) {
                val sum = nums[i] + nums[left] + nums[right]

                when {
                    sum == 0 -> {
                        result.add(listOf(nums[i], nums[left], nums[right]))
                        left++
                        right--

                        // 중복된 숫자 건너뜀
                        while (left < right && nums[left] == nums[left - 1]) left++
                        while (left < right && nums[right] == nums[right + 1]) right--
                    }
                    sum < 0 -> left++
                    else -> right--
                }
            }
        }

        return result.distinct()
    }

//    // O(N^2) 풀이
//    fun threeSum(nums: IntArray): List<List<Int>> {
//        val result = mutableListOf<List<Int>>()
//
//        nums.sort()
//
//        for (i in nums.indices) {
//            var left = i + 1
//            var right = nums.size - 1
//
//            while (left < right) {
//                val sum = nums[i] + nums[left] + nums[right]
//
//                when {
//                    sum == 0 -> {
//                        result.add(listOf(nums[i], nums[left], nums[right]))
//                        left++
//                        right--
//                    }
//                    sum < 0 -> left++
//                    else -> right--
//                }
//            }
//        }
//
//        return result.distinct()
//    }

//    // O(N^3) 풀이
//    fun threeSum(nums: IntArray): List<List<Int>> {
//        val result = mutableListOf<List<Int>>()
//
//        for (i in 0 until nums.size) {
//            for (j in i + 1 until nums.size) {
//                for (k in j + 1 until nums.size) {
//                    if (nums[i] + nums[j] + nums[k] == 0) {
//                        result.add(listOf(nums[i], nums[j], nums[k]))
//                    }
//                }
//            }
//        }
//
//        return result.map { it.sorted() }.distinct()
//    }
}
