/**
 * Given an integer array nums, design an algorithm to randomly shuffle the array.
 * All permutations of the array should be equally likely as a result of the shuffling.
 *
 * Implement the Solution class:
 * Solution(int[] nums) Initializes the object with the integer array nums.
 * int[] reset() Resets the array to its original configuration and returns it.
 * int[] shuffle() Returns a random shuffling of the array.
 *
 * Constraints:
 * 1 <= nums.length <= 50
 * -10^6 <= nums[i] <= 10^6
 * All the elements of nums are unique.
 * At most 10^4 calls in total will be made to reset and shuffle.
 */

fun main() {
    val array = intArrayOf(1, 2, 3)

    val solution = Solution(array);
    println(solution.shuffle())
    println(solution.reset())
    println(solution.shuffle())
}

class Solution(nums: IntArray) {
    private val init = nums.clone()
    private var current = nums.clone()
    private val random = java.util.Random()

    // 시간 복잡도 : O(1)
    // 공간 복잡도 : O(N)
    fun reset(): IntArray {
        current = init.clone()

        return current
    }

    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(N)
    fun shuffle(): IntArray {
        val array = current.clone()

        for (i in array.indices.reversed()) {
            val j = random.nextInt(i + 1)

            val temp = array[i]
            array[i] = array[j]
            array[j] = temp
        }

        return array
    }
}
