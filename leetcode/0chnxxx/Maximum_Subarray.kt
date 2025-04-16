/**
 * Given an integer array nums, find the subarray with the largest sum, and return its sum.
 *
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 */

fun main() {
    val nums = intArrayOf(-2, 1, -3, 4, -1, 2, 1, -5, 4)

    val solution = Solution().maxSubArray(nums)

    println(solution)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun maxSubArray(nums: IntArray): Int {
        // [카데인 알고리즘]
        //
        // 합이 양수라면 다음 값과 더했을 때 더 큰 값이 될 수도 있으니 누적합 유지
        // 합이 음수라면 누적합을 버리고 다시 시작
        //
        // maxOf()를 통해 nums[i]가 현재까지의 누적합보다 크다면 누적합을 새로 시작
        var currentSum = nums[0]
        var maxSum = nums[0]

        for (i in 1 until nums.size) {
            currentSum = maxOf(nums[i], currentSum + nums[i])
            maxSum = maxOf(maxSum, currentSum)
        }

        return maxSum
    }

//    // 시간 초과
//    // 시간 복잡도 : O(N^2)
//    // 공간 복잡도 : O(N)
//    fun maxSubArray(nums: IntArray): Int {
//        var result = Int.MIN_VALUE
//
//        for (size in nums.indices) {
//            for (i in nums.indices) {
//                val window = i + size
//
//                if (window < nums.size) {
//                    val slicedNums = nums.slice(i..window)
//                    val sum = slicedNums.sum()
//
//                    result = maxOf(result, sum)
//                }
//            }
//        }
//
//        return result
//    }
}
