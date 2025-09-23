/**
 * Given an integer array nums, handle multiple queries of the following types:
 * Update the value of an element in nums.
 * Calculate the sum of the elements of nums between indices left and right inclusive where left <= right.
 *
 * Implement the NumArray class:
 * NumArray(int[] nums) Initializes the object with the integer array nums.
 * void update(int index, int val) Updates the value of nums[index] to be val.
 * int sumRange(int left, int right) Returns the sum of the elements of nums between indices left and right inclusive (i.e. nums[left] + nums[left + 1] + ... + nums[right]).
 *
 * Constraints:
 * 1 <= nums.length <= 3 * 10^4
 * -100 <= nums[i] <= 100
 * 0 <= index < nums.length
 * -100 <= val <= 100
 * 0 <= left <= right < nums.length
 * At most 3 * 10^4 calls will be made to update and sumRange.
 */

fun main() {
    val array = intArrayOf(1, 3, 5)

    val numArray = NumArray(array)

    println(numArray.sumRange(0, 2)) // return 1 + 3 + 5 = 9
    println(numArray.update(1, 2))   // nums = [1, 2, 5]
    println(numArray.sumRange(0, 2)) // return 1 + 2 + 5 = 8
}

class NumArray(nums: IntArray) {
    private val n = nums.size
    private val tree = IntArray(n + 1)
    private val array = IntArray(n)

    init {
        for (i in nums.indices) {
            update(i, nums[i])
        }
    }

    // 시간 복잡도 : O(log N)
    // 공간 복잡도 : O(1)
    fun update(index: Int, `val`: Int) {
        val diff = `val` - array[index]

        array[index] = `val`

        var i = index + 1

        while (i <= n) {
            tree[i] += diff
            i += (i and -i)
        }
    }

    // 시간 복잡도 : O(log N)
    // 공간 복잡도 : O(1)
    fun sumRange(left: Int, right: Int): Int {
        fun prefixSum(i: Int): Int {
            var index = i + 1
            var sum = 0

            while (index > 0) {
                sum += tree[index]
                index -= (index and -index)
            }

            return sum
        }

        return prefixSum(right) - prefixSum(left - 1)
    }
}