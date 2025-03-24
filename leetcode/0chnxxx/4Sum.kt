/**
 * Given an array nums of n integers, return an array of all the unique quadruplets [nums[a], nums[b], nums[c], nums[d]] such that:
 *
 * 0 <= a, b, c, d < n
 * a, b, c, and d are distinct.
 * nums[a] + nums[b] + nums[c] + nums[d] == target
 * You may return the answer in any order.
 *
 * 1 <= nums.length <= 200
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 */

fun main() {
    val nums = intArrayOf(1, 0, -1, 0, -2, 2)
    val target = 0

    val solution = Solution().fourSum(nums, target)

    println(solution)
}

class Solution {
    // O(N^3) 풀이
    fun fourSum(nums: IntArray, target: Int): List<List<Int>> {
        val n = nums.size
        val result = mutableListOf<List<Int>>()

        nums.sort()

        for (i in 0 until n - 3) {
            // 중복 제거
            if (i > 0 && nums[i] == nums[i - 1]) continue

            for (j in i + 1 until n - 2) {
                // 중복 제거
                if (j > i + 1 && nums[j] == nums[j - 1]) continue

                var left = j + 1
                var right = n - 1

                while (left < right) {
                    // Integer 범위를 벗어나는 케이스를 처리하기 위한 Long 변환
                    val sum = nums[i].toLong() + nums[j].toLong() + nums[left].toLong() + nums[right].toLong()

                    when {
                        sum < target -> left++
                        sum > target -> right--
                        else -> {
                            result.add(listOf(nums[i], nums[j], nums[left], nums[right]))

                            left++
                            right--

                            // 중복 제거
                            while (left < right && nums[left] == nums[left - 1]) left++
                            while (left < right && nums[right] == nums[right + 1]) right--
                        }
                    }
                }
            }
        }

        return result
    }

//    // O(N^4) 풀이 -> 시간초과
//    fun fourSum(nums: IntArray, target: Int): List<List<Int>> {
//        val result = mutableListOf<List<Int>>()
//
//        nums.sort()
//
//        fun backtrack(index: Int, current: MutableList<Int>, result: MutableList<List<Int>>) {
//            if (current.size == 4 && current.sum() == target) {
//                result.add(ArrayList(current))
//                return
//            }
//
//            for (i in index until nums.size) {
//                if (i > index && nums[i] == nums[i - 1]) continue
//
//                current.add(nums[i])
//                backtrack(i + 1, current, result)
//                current.removeAt(current.size - 1)
//            }
//        }
//
//        backtrack(0, mutableListOf(), result)
//
//        return result.toList()
//    }
}
