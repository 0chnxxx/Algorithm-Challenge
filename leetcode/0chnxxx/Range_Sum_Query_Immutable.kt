/**
 * Given an integer array nums, handle multiple queries of the following type:
 * Calculate the sum of the elements of nums between indices left and right inclusive where left <= right.
 *
 * Implement the NumArray class:
 * NumArray(int[] nums) Initializes the object with the integer array nums.
 * int sumRange(int left, int right) Returns the sum of the elements of nums between indices left and right inclusive
 * (i.e. nums[left] + nums[left + 1] + ... + nums[right]).
 *
 * Constraints:
 * 1 <= nums.length <= 10^4
 * -10^5 <= nums[i] <= 10^5
 * 0 <= left <= right < nums.length
 * At most 10^4 calls will be made to sumRange.
 */

fun main() {
    val array = intArrayOf(-2, 0, 3, -5, 2, -1)

    val numArray = NumArray(array);

    println(numArray.sumRange(0, 2)) // return (-2) + 0 + 3 = 1
    println(numArray.sumRange(2, 5)) // return 3 + (-5) + 2 + (-1) = -1
    println(numArray.sumRange(0, 5)) // return (-2) + 0 + 3 + (-5) + 2 + (-1) = -3
}

class NumArray(nums: IntArray) {
    private val prefixSum: IntArray

    init {
        prefixSum = IntArray(nums.size + 1)

        for (i in nums.indices) {
            prefixSum[i + 1] = prefixSum[i] + nums[i]
        }
    }

    // 시간 복잡도 : O(1)
    // 공간 복잡도 : O(N)
    fun sumRange(left: Int, right: Int): Int {
        return prefixSum[right + 1] - prefixSum[left]
    }

//    val array = nums

//    // 시간 복잡도 : O(N)
//    // 공간 복잡도 : O(N)
//    fun sumRange(left: Int, right: Int): Int {
//        var result = 0
//
//        for (i in left..right) {
//            result += array[i]
//        }
//
//        return result
//    }
}
