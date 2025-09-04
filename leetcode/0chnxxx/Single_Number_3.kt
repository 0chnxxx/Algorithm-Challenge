/**
 * Given an integer array nums, in which exactly two elements appear only once and all the other elements appear exactly twice.
 * Find the two elements that appear only once.
 * You can return the answer in any order.
 *
 * You must write an algorithm that runs in linear runtime complexity and uses only constant extra space.
 *
 * Constraints:
 * 2 <= nums.length <= 3 * 10^4
 * -2^31 <= nums[i] <= 2^31 - 1
 * Each integer in nums will appear twice, only two integers will appear once.
 */

fun main() {
    val nums = intArrayOf(1, 2, 1, 3, 2, 5)

    val result = Solution().singleNumber(nums)

    println(result.joinToString(", "))
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun singleNumber(nums: IntArray): IntArray {
        var n = 0

        // XOR 연산
        // a xor a = 0
        // a xor 0 = a
        // 두번 등장한 숫자들을 필터링하고 a xor b 의 2개 숫자만 남김
        for (num in nums) {
            n = n xor num
        }

        // XOR 연산한 숫자의 보수를 구함
        // 서로 다른 비트 자리가 어딘지 찾음
        val diff = n and -n

        // 보수와의 XOR 연산을 통해 숫자를 나눔
        var a = 0
        var b = 0

        for (num in nums) {
            if ((num and diff) == 0) {
                a = a xor num
            } else {
                b = b xor num
            }
        }

        return intArrayOf(a, b)
    }
}