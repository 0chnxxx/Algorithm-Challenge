class Solution {
    fun sortColors(nums: IntArray): Unit {
        val counts = IntArray(3)
        for (num in nums) counts[num]++
        var index = 0
        for (i in 0..2) {
            repeat(counts[i]) { nums[index++] = i }
        }
        println(nums.toList())
    }
}

fun main() {
    val solution = Solution()

    solution.sortColors(intArrayOf(2, 0, 2, 1, 1, 0))
}