

class Solution {
    fun find132pattern(nums: IntArray): Boolean {
        for (i in 0 ..< nums.size - 2) {
            if (nums[i] < nums[i + 2] && nums[i + 2] < nums[i + 1]) {
                return true
            }
        }
        return false
    }
}

fun main() {
    val solution = Solution()
    println(solution.find132pattern(intArrayOf(1, 2, 3, 4)))
    println(solution.find132pattern(intArrayOf(3, 1, 4, 2)))
}