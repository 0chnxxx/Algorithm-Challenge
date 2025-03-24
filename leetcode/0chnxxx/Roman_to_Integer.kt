/**
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 *
 * Symbol       Value
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * For example, 2 is written as II in Roman numeral, just two ones added together. 12 is written as XII, which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.
 *
 * Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:
 *
 * I can be placed before V (5) and X (10) to make 4 and 9.
 * X can be placed before L (50) and C (100) to make 40 and 90.
 * C can be placed before D (500) and M (1000) to make 400 and 900.
 * Given a roman numeral, convert it to an integer.
 *
 * 1 <= s.length <= 15
 * s contains only the characters ('I', 'V', 'X', 'L', 'C', 'D', 'M').
 * It is guaranteed that s is a valid roman numeral in the range [1, 3999].
 */

fun main() {
    val s = "MCMXCIV"

    val solution = Solution().romanToInt(s)

    println(solution)
}

class Solution {
    // 최적화 풀이
    fun romanToInt(s: String): Int {
        val symbols = mapOf(
            "I" to 1,
            "IV" to 4,
            "V" to 5,
            "IX" to 9,
            "X" to 10,
            "XL" to 40,
            "L" to 50,
            "XC" to 90,
            "C" to 100,
            "CD" to 400,
            "D" to 500,
            "CM" to 900,
            "M" to 1000
        )

        var index = 0
        var integer = 0

        while (index < s.length) {
            val finalChar = if (index + 1 < s.length && symbols.containsKey(s.substring(index, index + 2))) {
                s.substring(index, index + 2).also { index++ }
            } else {
                s.substring(index, index + 1)
            }

            integer += symbols[finalChar] ?: 0
            index++
        }

        return integer
    }

//    // O(N) 풀이
//    fun romanToInt(s: String): Int {
//        val symbols = arrayOf(
//            "I" to 1,
//            "IV" to 4,
//            "V" to 5,
//            "IX" to 9,
//            "X" to 10,
//            "XL" to 40,
//            "L" to 50,
//            "XC" to 90,
//            "C" to 100,
//            "CD" to 400,
//            "D" to 500,
//            "CM" to 900,
//            "M" to 1000
//        )
//
//        var index = 0
//        var integer = 0
//
//        while (index < s.length) {
//            val char = s[index]
//            val nextChar = if (index + 1 < s.length) s[index + 1] else null
//            var finalChar = char.toString()
//
//            if (nextChar != null) {
//                val tempChar = finalChar + nextChar
//
//                if (listOf("IV", "IX", "XL", "XC", "CD", "CM").contains(tempChar)) {
//                    finalChar = tempChar
//                    index++
//                }
//            }
//
//            val symbol = symbols.find { it.first == finalChar }?.second
//
//            if (symbol != null) {
//                integer += symbol
//            }
//
//            index++
//        }
//
//        return integer
//    }
}
