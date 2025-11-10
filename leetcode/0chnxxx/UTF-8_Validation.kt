/**
 * Given an integer array data representing the data, return whether it is a valid UTF-8 encoding (i.e. it translates to a sequence of valid UTF-8 encoded characters).
 *
 * A character in UTF8 can be from 1 to 4 bytes long, subjected to the following rules:
 * For a 1-byte character, the first bit is a 0, followed by its Unicode code.
 * For an n-bytes character, the first n bits are all one's, the n + 1 bit is 0, followed by n - 1 bytes with the most significant 2 bits being 10.
 *
 * This is how the UTF-8 encoding would work:
 *
 *      Number of Bytes   |        UTF-8 Octet Sequence
 *                        |              (binary)
 *    --------------------+-----------------------------------------
 *             1          |   0xxxxxxx
 *             2          |   110xxxxx 10xxxxxx
 *             3          |   1110xxxx 10xxxxxx 10xxxxxx
 *             4          |   11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
 * x denotes a bit in the binary form of a byte that may be either 0 or 1.
 *
 * Note:
 * The input is an array of integers. Only the least significant 8 bits of each integer is used to store the data. This means each integer represents only 1 byte of data.
 *
 * Constraints:
 * 1 <= data.length <= 2 * 10^4
 * 0 <= data[i] <= 255
 */

fun main() {
    val data = intArrayOf(197, 130, 1)

    val result = Solution().validUtf8(data)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun validUtf8(data: IntArray): Boolean {
        // 남은 continuation byte 수
        var remain = 0

        for (num in data) {
            // 하위 8비트 사용
            val byte = num and 0xFF

            if (remain == 0) {
                // 몇 바이트짜리 문자인지
                when {
                    (byte shr 7) == 0b0 -> remain = 0 // 0xxxxxxx
                    (byte shr 5) == 0b110 -> remain = 1 // 110xxxxx
                    (byte shr 4) == 0b1110 -> remain = 2 // 1110xxxx
                    (byte shr 3) == 0b11110 -> remain = 3 // 11110xxx
                    else -> return false // 잘못된 바이트
                }
            } else {
                if ((byte shr 6) != 0b10) {
                    return false
                }

                remain--
            }
        }

        // continuation byte가 정확히 끝났는지 확인
        return remain == 0
    }
}
