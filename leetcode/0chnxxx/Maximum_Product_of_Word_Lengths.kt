import kotlin.math.max

/**
 * Given a string array words, return the maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters.
 * If no such two words exist, return 0.
 *
 * Constraints:
 * 2 <= words.length <= 1000
 * 1 <= words[i].length <= 1000
 * words[i] consists only of lowercase English letters.
 */

fun main() {
    val words = arrayOf(
        "abcw",
        "baz",
        "foo",
        "bar",
        "xtfn",
        "abcdef"
    )

    val result = Solution().maxProduct(words)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N * L + N^2)
    // 공간 복잡도 : O(N)
    fun maxProduct(words: Array<String>): Int {
        val n = words.size
        val masks = IntArray(n)
        val lengths = IntArray(n)

        for (i in words.indices) {
            var mask = 0

            for (c in words[i]) {
                mask = mask or (1 shl (c - 'a'))
            }

            masks[i] = mask
            lengths[i] = words[i].length
        }

        var maxProduct = 0

        for (i in 0 until n) {
            for (j in i + 1 until n) {
                if (masks[i] and masks[j] == 0) {
                    val product = lengths[i] * lengths[j]

                    maxProduct = max(maxProduct, product)
                }
            }
        }

        return maxProduct
    }
}
