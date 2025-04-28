/**
 * You are given a large integer represented as an integer array digits, where each digits[i] is the ith digit of the integer.
 * The digits are ordered from most significant to least significant in left-to-right order.
 * The large integer does not contain any leading 0's.
 *
 * Increment the large integer by one and return the resulting array of digits.
 *
 * 1 <= digits.length <= 100
 * 0 <= digits[i] <= 9
 * digits does not contain any leading 0's.
 */

fun main() {
    val digits = intArrayOf(9, 8, 7, 6, 5, 4, 3, 2, 1, 0)

    val solution = Solution().plusOne(digits)

    println(solution.joinToString(", "))
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(N)
    fun plusOne(digits: IntArray): IntArray {
        // 뒤에서부터 순회
        for (i in digits.size - 1 downTo 0) {
            // 올림이 필요 없는 경우
            // 단순히 1을 더하고 리턴
            if (digits[i] < 9) {
                digits[i] += 1
                return digits
            // 올림이 필요한 경우
            // 해당 자리를 0으로 만들고 다음 자리로 이동
            } else {
                digits[i] = 0
            }
        }

        // 모든 숫자가 9인 경우를 처리하기 위함
        val result = IntArray(digits.size + 1)

        result[0] = 1

        return result
    }
}
