/**
 * A magical string s consists of only '1' and '2' and obeys the following rule:
 * Concatenating the sequence of lengths of its consecutive groups of identical characters '1' and '2' generates the string s itself.
 * The first few elements of s is s = "1221121221221121122……".
 * If we group the consecutive 1's and 2's in s, it will be "1 22 11 2 1 22 1 22 11 2 11 22 ......" and counting the occurrences of 1's or 2's in each group yields the sequence "1 2 2 1 1 2 1 2 2 1 2 2 ......".
 * You can see that concatenating the occurrence sequence gives us s itself.
 * Given an integer n, return the number of 1's in the first n number in the magical string s.
 *
 * Constraints:
 * 1 <= n <= 10^5
 */

fun main() {
    val n = 6

    val result = Solution().magicalString(n)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(N)
    fun magicalString(n: Int): Int {
        if (n == 0) return 0
        if (n <= 3) return 1

        val s = IntArray(n + 2)
        s[0] = 1
        s[1] = 2
        s[2] = 2

        var i = 2
        var idx = 3
        var num = 1
        var ones = 1

        while (idx < n) {
            val count = s[i]

            repeat(count) {
                if (idx < n) {
                    s[idx] = num

                    if (num == 1) ones++

                    idx++
                }
            }

            num = if (num == 1) 2 else 1
            i++
        }

        return ones
    }
}
