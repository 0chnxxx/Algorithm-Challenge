/**
 * Given an integer array nums where every element appears three times except for one, which appears exactly once.
 * Find the single element and return it.
 * You must implement a solution with a linear runtime complexity and use only constant extra space.
 *
 * Constraints:
 * 1 <= nums.length <= 3 * 10^4
 * -2^31 <= nums[i] <= 2^31 - 1
 * Each element in nums appears exactly three times except for one element which appears once.
 */

fun main() {
    val nums = intArrayOf(2, 2, 3, 2)

    val result = Solution().singleNumber(nums)

    println(result)
}

class Solution {
    fun singleNumber(nums: IntArray): Int {
        var result = 0

        // Integer의 범위인 32자리 비트에서 등장 횟수를 찾기 위함
        for (i in 0 until 32) {
            var sum = 0

            // nums를 전부 순회하면서 해당 자리 비트가 1인지 확인
            // num shr i -> num을 i만큼 오른쪽으로 비트 시프트
            // and 1 -> 가장 오른쪽 비트가 1인지 여부 (0 or 1 반환)
            for (num in nums) {
                println("${num} / ${num shr i} / ${(num shr i) and 1}")
                sum += (num shr i) and 1
            }

            // 해당 자리 비트가 3번 등장하지 않는다면
            if (sum % 3 != 0) {
                // result 변수의 32자리 비트 중 i번째 비트를 한 번 등장한 숫자의 i번째 비트로 복원
                // 결국은 32자리의 비트 확인이 끝나야 온전한 Integer가 복원됨
                result = result or (1 shl i)
            }
        }

        return result
    }
}