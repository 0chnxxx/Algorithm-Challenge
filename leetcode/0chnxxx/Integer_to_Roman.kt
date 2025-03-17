/**
 * Seven different symbols represent Roman numerals with the following values:
 *
 * Symbol	Value
 * I	1
 * V	5
 * X	10
 * L	50
 * C	100
 * D	500
 * M	1000
 * Roman numerals are formed by appending the conversions of decimal place values from highest to lowest. Converting a decimal place value into a Roman numeral has the following rules:
 *
 * If the value does not start with 4 or 9, select the symbol of the maximal value that can be subtracted from the input, append that symbol to the result, subtract its value, and convert the remainder to a Roman numeral.
 * If the value starts with 4 or 9 use the subtractive form representing one symbol subtracted from the following symbol, for example, 4 is 1 (I) less than 5 (V): IV and 9 is 1 (I) less than 10 (X): IX. Only the following subtractive forms are used: 4 (IV), 9 (IX), 40 (XL), 90 (XC), 400 (CD) and 900 (CM).
 * Only powers of 10 (I, X, C, M) can be appended consecutively at most 3 times to represent multiples of 10. You cannot append 5 (V), 50 (L), or 500 (D) multiple times. If you need to append a symbol 4 times use the subtractive form.
 * Given an integer, convert it to a Roman numeral.
 *
 * 1 <= num <= 3999
 */

fun main() {
    val num = 3749

    val solution = Solution().intToRoman(num)

    println(solution)
}

class Solution {
    // O(N) 풀이
    fun intToRoman(num: Int): String {
        val symbols = arrayOf(
            1000 to "M",
            900 to "CM",
            500 to "D",
            400 to "CD",
            100 to "C",
            90 to "XC",
            50 to "L",
            40 to "XL",
            10 to "X",
            9 to "IX",
            5 to "V",
            4 to "IV",
            1 to "I"
        )

        val builder = StringBuilder()
        var number = num

        for ((value, symbol) in symbols) {
            while (number >= value) {
                builder.append(symbol)
                number -= value
            }
        }

        return builder.toString()
    }

//    // O(N^3) 풀이
//    fun intToRoman(num: Int): String {
//        val symbols = arrayOf(
//            1000 to "M",
//            500 to "D",
//            100 to "C",
//            50 to "L",
//            10 to "X",
//            5 to "V",
//            1 to "I"
//        )
//
//        val number = num.toString()
//        var length = number.length
//
//        val builder = StringBuilder()
//
//        for (char in number) {
//            var originalNumber = if (length != 1) (char.digitToInt() * 10.0.pow(length.toDouble() - 1)).toInt() else char.digitToInt()
//
//            if (char == '4' || char == '9') {
//                when (originalNumber) {
//                    4 -> builder.append("IV")
//                    9 -> builder.append("IX")
//                    40 -> builder.append("XL")
//                    90 -> builder.append("XC")
//                    400 -> builder.append("CD")
//                    900 -> builder.append("CM")
//                }
//            } else {
//                while (originalNumber > 0) {
//                    for (symbol in symbols) {
//                        if (originalNumber - symbol.first < 0) continue
//
//                        originalNumber -= symbol.first
//                        builder.append(symbol.second)
//                        break
//                    }
//                }
//            }
//
//            length--
//        }
//
//        return builder.toString()
//    }
}
