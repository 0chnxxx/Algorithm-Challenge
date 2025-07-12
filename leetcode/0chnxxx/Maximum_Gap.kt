/**
 * Given an integer array nums, return the maximum difference between two successive elements in its sorted form.
 * If the array contains less than two elements, return 0.
 * You must write an algorithm that runs in linear time and uses linear extra space.
 *
 * Constraints:
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i] <= 10^9
 */

fun main() {
    val nums = intArrayOf(3, 6, 9, 1)

    val result = Solution().maximumGap(nums)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(N)
    fun maximumGap(nums: IntArray): Int {
        if (nums.size < 2) return 0

        fun radixSort(nums: IntArray) {
            val max = nums.maxOrNull() ?: return
            var exp = 1
            val n = nums.size
            val output = IntArray(n)

            while (max / exp > 0) {
                val count = IntArray(10)

                for (i in nums.indices) {
                    count[(nums[i] / exp) % 10]++
                }

                for (i in 1 until 10) {
                    count[i] += count[i - 1]
                }

                for (i in nums.indices.reversed()) {
                    val digit = (nums[i] / exp) % 10
                    output[--count[digit]] = nums[i]
                }

                for (i in nums.indices) {
                    nums[i] = output[i]
                }

                exp *= 10
            }
        }

        radixSort(nums)

        var max = 0

        for (i in 0 until nums.size) {
            if (i + 1 >= nums.size) continue

            val diff = nums[i + 1] - nums[i]

            max = maxOf(max, diff)
        }

        return max
    }

//    // 시간 복잡도 : O(N log N)
//    // 공간 복잡도 : O(1)
//    fun maximumGap(nums: IntArray): Int {
//        if (nums.size < 2) return 0
//
//        nums.sort()
//
//        var max = 0
//
//        for (i in 0 until nums.size) {
//            if (i + 1 >= nums.size) continue
//
//            val diff = nums[i + 1] - nums[i]
//
//            max = maxOf(max, diff)
//        }
//
//        return max
//    }
}
