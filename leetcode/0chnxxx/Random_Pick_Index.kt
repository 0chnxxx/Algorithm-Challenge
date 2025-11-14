/**
 * Given an integer array nums with possible duplicates, randomly output the index of a given target number.
 * You can assume that the given target number must exist in the array.
 *
 * Implement the Solution class:
 * Solution(int[] nums) Initializes the object with the array nums.
 * int pick(int target) Picks a random index i from nums where nums[i] == target.
 * If there are multiple valid i's, then each index should have an equal probability of returning.
 *
 * Constraints:
 * 1 <= nums.length <= 2 * 10^4
 * -2^31 <= nums[i] <= 2^31 - 1
 * target is an integer from nums.
 * At most 10^4 calls will be made to pick.
 */

fun main() {
    val solution = Solution(intArrayOf(1, 2, 3, 3, 3));
    println(solution.pick(3))
    println(solution.pick(1))
    println(solution.pick(3))
}

class Solution(private val nums: IntArray) {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun pick(target: Int): Int {
        var result = -1
        var count = 0

        for (i in nums.indices) {
            if (nums[i] == target) {
                count++

                if ((0 until count).random() == 0) {
                    result = i
                }
            }
        }

        return result
    }
}