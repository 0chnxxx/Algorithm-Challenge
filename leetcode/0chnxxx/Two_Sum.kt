/**
 * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 *
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 *
 * You can return the answer in any order.
 *
 * 2 <= nums.length <= 10^4
 * -10^9 <= nums[i] <= 10^9
 * -10^9 <= target <= 10^9
 *
 * + 시간 복잡도를 O(n^2) 이하로 줄일 수 있는가?
 */

fun main() {
    val nums = intArrayOf(3, 2, 3)
    val target = 6

    val solution = Solution().twoSum(nums, target)

    println(solution.joinToString(", "))
}

class Solution {
    // O(n) 풀이
    fun twoSum(nums: IntArray, target: Int): IntArray {
        val map = mutableMapOf<Int, Int>()

        for (i in nums.indices) {
            val num = nums[i]
            val other = target - num

            if (map.containsKey(other)) {
                return intArrayOf(i, map[other]!!)
            }

            map[num] = i
        }

        return intArrayOf()
    }

//    // O(n^2) 풀이
//    fun twoSum(nums: IntArray, target: Int): IntArray {
//        val indices = mutableListOf<Int>()
//        val visited = BooleanArray(nums.size) { false }
//
//        for (i in 0 until nums.size) {
//            for (j in i + 1 until nums.size) {
//                if (visited[i] || visited[j]) continue
//
//                if (nums[i] + nums[j] == target) {
//                    indices.add(i)
//                    indices.add(j)
//                    visited[i] = true
//                    visited[j] = true
//                }
//            }
//        }
//
//        return indices.toIntArray()
//    }
}
