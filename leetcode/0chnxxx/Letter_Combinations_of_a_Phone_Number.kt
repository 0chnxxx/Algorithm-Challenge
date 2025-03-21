/**
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.
 *
 * A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
 *
 * 0 <= digits.length <= 4
 * digits[i] is a digit in the range ['2', '9'].
 */

fun main() {
    val digits = "23"

    val solution = Solution().letterCombinations(digits)

    println(solution)
}

class Solution {
    // O(digits.length * 4) = O(N * M) 풀이
    fun letterCombinations(digits: String): List<String> {
        val letters = mapOf(
            "2" to listOf('a', 'b', 'c'),
            "3" to listOf('d', 'e', 'f'),
            "4" to listOf('g', 'h', 'i'),
            "5" to listOf('j', 'k', 'l'),
            "6" to listOf('m', 'n', 'o'),
            "7" to listOf('p', 'q', 'r', 's'),
            "8" to listOf('t', 'u', 'v'),
            "9" to listOf('w', 'x', 'y', 'z')
        )

        val cases = digits.split("").filter { it.isNotEmpty() }.map { letters[it]!! }.toList()

        return generateCombinations(cases);
    }

    private fun generateCombinations(cases: List<List<Char>>): List<String> {
        if (cases.isEmpty()) return emptyList()

        fun backtrack(index: Int, current: String, result: MutableList<String>) {
            if (index == cases.size) {
                result.add(current)
                return
            }

            for (char in cases[index]) {
                backtrack(index + 1, current + char, result)
            }
        }

        val result = mutableListOf<String>()

        backtrack(0, "", result)

        return result
    }
}
