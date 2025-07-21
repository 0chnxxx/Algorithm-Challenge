/**
 * Given a list of non-negative integers nums, arrange them such that they form the largest number and return it.
 * Since the result may be very large, so you need to return a string instead of an integer.
 *
 * Constraints:
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 109
 */

fun main() {
    val nums = intArrayOf(3, 30, 34, 5, 9)

    val result = Solution().largestNumber(nums)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(n log n * k)
    // 공간 복잡도 : O(n)
    fun largestNumber(nums: IntArray): String {
        val sortedNums = nums
            .map { it.toString() }
            .sortedWith { a, b ->
                (b + a).compareTo(a + b)
            }

        if (sortedNums[0] == "0") return "0"

        return sortedNums.joinToString("")
    }

//    // 시간 복잡도 : O(n log n * k)
//    // 공간 복잡도 : O(n * k)
//    fun largestNumber(nums: IntArray): String {
//        val sorted = nums.sortedWith { a, b ->
//            val order1 = "$a$b"
//            val order2 = "$b$a"
//            order2.compareTo(order1)
//        }
//
//        val result = sorted.joinToString("")
//
//        val finalResult = if (result.all { it == '0' }) "0" else result
//
//        return finalResult
//    }
}
