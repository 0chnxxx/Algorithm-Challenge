/**
 * You are given an integer array nums. The adjacent integers in nums will perform the float division.
 * For example, for nums = [2,3,4], we will evaluate the expression "2/3/4".
 * However, you can add any number of parenthesis at any position to change the priority of operations.
 * You want to add these parentheses such the value of the expression after the evaluation is maximum.
 * Return the corresponding expression that has the maximum value in string format.
 *
 * Note:
 * your expression should not contain redundant parenthesis.
 *
 * Constraints:
 * 1 <= nums.length <= 10
 * 2 <= nums[i] <= 1000
 * There is only one optimal division for the given input.
 */

fun main() {
    val nums = intArrayOf(1000, 100, 10, 2)

    val result = Solution().optimalDivision(nums)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(N)
    fun optimalDivision(nums: IntArray): String {
        if (nums.size == 1) return nums[0].toString()
        if (nums.size == 2) return "${nums[0]}/${nums[1]}"

        val sb = StringBuilder()

        sb.append(nums[0])
        sb.append("/(")

        for (i in 1 until nums.size) {
            sb.append(nums[i])

            if (i != nums.lastIndex) sb.append("/")
        }

        sb.append(")")

        return sb.toString()
    }
}