/**
 * You have a set of integers s, which originally contains all the numbers from 1 to n.
 * Unfortunately, due to some error, one of the numbers in s got duplicated to another number in the set, which results in repetition of one number and loss of another number.
 * You are given an integer array nums representing the data status of this set after the error.
 * Find the number that occurs twice and the number that is missing and return them in the form of an array.
 *
 * Constraints:
 * 2 <= nums.length <= 10^4
 * 1 <= nums[i] <= 10^4
 */

fun main() {
    val nums = intArrayOf(1, 2, 2, 4)

    val result = Solution().findErrorNums(nums)

    println(result.joinToString(", "))
}

class Solution {
    // 시간 복잡도 : O(n log n)
    // 공간 복잡도 : O(1)
    fun findErrorNums(nums: IntArray): IntArray {
        nums.sort()

        var duplicate = -1
        var missing = -1

        for (i in 1 until nums.size) {
            if (nums[i] == nums[i - 1]) {
                duplicate = nums[i]
            } else if (nums[i] > nums[i - 1] + 1) {
                missing = nums[i - 1] + 1
            }
        }

        if (nums[0] != 1) missing = 1
        if (nums[nums.size - 1] != nums.size) missing = nums.size

        return intArrayOf(duplicate, missing)
    }
}