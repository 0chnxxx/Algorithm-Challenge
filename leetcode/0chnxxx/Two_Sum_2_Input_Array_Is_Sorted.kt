/**
 * Given a 1-indexed array of integers numbers that is already sorted in non-decreasing order, find two numbers such that they add up to a specific target number.
 * Let these two numbers be numbers[index1] and numbers[index2] where 1 <= index1 < index2 <= numbers.length.
 * Return the indices of the two numbers, index1 and index2, added by one as an integer array [index1, index2] of length 2.
 * The tests are generated such that there is exactly one solution.
 * You may not use the same element twice.
 * Your solution must use only constant extra space.
 *
 * Constraints:
 * 2 <= numbers.length <= 3 * 10^4
 * -1000 <= numbers[i] <= 1000
 * numbers is sorted in non-decreasing order.
 * -1000 <= target <= 1000
 * The tests are generated such that there is exactly one solution.
 */

fun main() {
    val numbers = intArrayOf(2, 3, 4)
    val target = 6

    val result = Solution().twoSum(numbers, target)

    println(result.joinToString(", "))
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun twoSum(numbers: IntArray, target: Int): IntArray {
        var left = 0
        var right = numbers.lastIndex

        while (left < right) {
            val sum = numbers[left] + numbers[right]

            when {
                sum == target -> return intArrayOf(left + 1, right + 1)
                sum < target -> left++
                else -> right--
            }
        }

        return intArrayOf(0, 0)
    }

//    // 시간 복잡도 : O(N^2)
//    // 공간 복잡도 : O(1)
//    fun twoSum(numbers: IntArray, target: Int): IntArray {
//        for (i in numbers.indices) {
//            if (i + 1 > numbers.lastIndex) break
//            if (target - numbers[i] < numbers[i + 1]) continue
//
//            for (j in i + 1 until numbers.size) {
//                if (numbers[i] + numbers[j] == target) {
//                    return intArrayOf(i + 1, j + 1)
//                }
//            }
//        }
//
//        return intArrayOf(0, 0)
//    }
}