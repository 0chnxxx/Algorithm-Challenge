/**
 * The DNA sequence is composed of a series of nucleotides abbreviated as 'A', 'C', 'G', and 'T'.
 * For example, "ACGAATTCCG" is a DNA sequence.
 * When studying DNA, it is useful to identify repeated sequences within the DNA.
 * Given a string s that represents a DNA sequence, return all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.
 * You may return the answer in any order.
 *
 * Constraints:
 * 1 <= s.length <= 10^5
 * s[i] is either 'A', 'C', 'G', or 'T'.
 */

fun main() {
    val s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"

    val result = Solution().findRepeatedDnaSequences(s)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(N)
    fun findRepeatedDnaSequences(s: String): List<String> {
        val seen = mutableSetOf<String>()
        val repeated = mutableSetOf<String>()

        for (i in 0..s.length - 10) {
            val substring = s.substring(i, i + 10)

            if (!seen.add(substring)) {
                repeated.add(substring)
            }
        }

        return repeated.toList()
    }
}