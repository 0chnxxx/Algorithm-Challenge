/**
 * A binary watch has 4 LEDs on the top to represent the hours (0-11), and 6 LEDs on the bottom to represent the minutes (0-59). Each LED represents a zero or one, with the least significant bit on the right.
 * For example, the below binary watch reads "4:51".
 * Given an integer turnedOn which represents the number of LEDs that are currently on (ignoring the PM), return all possible times the watch could represent. You may return the answer in any order.
 * The hour must not contain a leading zero.
 * For example, "01:00" is not valid. It should be "1:00".
 * The minute must consist of two digits and may contain a leading zero.
 * For example, "10:2" is not valid. It should be "10:02".
 *
 * Constraints:
 * 0 <= turnedOn <= 10
 */

fun main() {
    val turnedOn = 1

    val result = Solution().readBinaryWatch(turnedOn)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(1)
    // 공간 복잡도 : O(1)
    fun readBinaryWatch(turnedOn: Int): List<String> {
        val result = mutableListOf<String>()

        for (hour in 0..11) {
            for (minute in 0..59) {
                val hourBits = hour.countOneBits()
                val minuteBits = minute.countOneBits()

                if (hourBits + minuteBits == turnedOn) {
                    result.add("${hour}:${minute.toString().padStart(2, '0')}")
                }
            }
        }

        return result
    }
}