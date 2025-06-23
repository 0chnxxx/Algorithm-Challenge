/**
 * Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
 * You must write an algorithm that runs in O(n) time.
 *
 * Constraints:
 * 0 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 */

fun main() {
    val nums = intArrayOf(100, 4, 200, 1, 3, 2)

    val solution = Solution().longestConsecutive(nums)

    println(solution)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(N)
    fun longestConsecutive(nums: IntArray): Int {
        val numSet = nums.toHashSet()
        var maxLength = 0

        for (num in numSet) {
            if (!numSet.contains(num - 1)) {
                var currentNum = num
                var currentStreak = 1

                while (numSet.contains(currentNum + 1)) {
                    currentNum++
                    currentStreak++
                }

                maxLength = maxOf(maxLength, currentStreak)
            }
        }

        return maxLength
    }

//    // 시간 복잡도 : O(N log N)
//    // 공간 복잡도 : O(N)
//    fun longestConsecutive(nums: IntArray): Int {
//        if (nums.isEmpty()) return 0
//
//        val sortedNums = nums.distinct().sorted()
//
//        var maxLength = 1
//        var length = 1
//
//        for (i in 0 until sortedNums.size - 1) {
//            if (sortedNums[i + 1] == sortedNums[i] + 1) {
//                length++
//                maxLength = maxOf(maxLength, length)
//            } else {
//                length = 1
//            }
//        }
//
//        return maxLength
//    }
}