/**
 * The count-and-say sequence is a sequence of digit strings defined by the recursive formula:
 *
 * countAndSay(1) = "1"
 * countAndSay(n) is the run-length encoding of countAndSay(n - 1).
 *
 * Run-length encoding (RLE) is a string compression method that works by replacing consecutive identical characters (repeated 2 or more times) with the concatenation of the character and the number marking the count of the characters (length of the run).
 * For example, to compress the string "3322251" we replace "33" with "23", replace "222" with "32", replace "5" with "15" and replace "1" with "11".
 * Thus the compressed string becomes "23321511".
 *
 * Given a positive integer n, return the nth element of the count-and-say sequence.
 *
 * 1 <= n <= 30
 */

fun main() {
    val n = 4

    val solution = Solution().countAndSay(n)

    println(solution)
}

class Solution {
    // 시간 복잡도 : O(2^N)
    // 공간 복잡도 : O(2^N)
    fun countAndSay(n: Int): String {
        fun encode(n: String): String {
            val builder = StringBuilder()
            var count = 1

            for (i in 1 until n.length) {
                if (n[i] == n[i - 1]) {
                    count++
                } else {
                    builder.append(count).append(n[i - 1])
                    count = 1
                }
            }

            builder.append(count).append(n.last())

            return builder.toString()
        }

        val memo = mutableMapOf<Int, String>()

        if (n == 1) return "1"
        if (memo.containsKey(n)) return memo[n]!!

        val prev = countAndSay(n - 1)
        val result = encode(prev)

        memo[n] = result

        return result
    }

//    // 시간 복잡도 : O(2^N)
//    // 공간 복잡도 : O(2^N)
//    fun countAndSay(n: Int): String {
//        var result = "1"
//
//        repeat(n - 1) {
//            val builder = StringBuilder()
//            var count = 1
//
//            for (i in 1 until result.length) {
//                if (result[i] == result[i - 1]) {
//                    count++
//                } else {
//                    builder.append(count).append(result[i - 1])
//                    count = 1
//                }
//            }
//
//            builder.append(count).append(result.last())
//
//            result = builder.toString()
//        }
//
//        return result
//    }

//    // 시간 복잡도 : O(2^N)
//    // 공간 복잡도 : O(2^N)
//    fun countAndSay(n: Int): String {
//        fun encode(n: String): String {
//            val builder = StringBuilder()
//            var count = 1
//
//            for (i in 1 until n.length) {
//                if (n[i] == n[i - 1]) {
//                    count++
//                } else {
//                    builder.append(count).append(n[i - 1])
//                    count = 1
//                }
//            }
//
//            builder.append(count).append(n.last())
//
//            return builder.toString()
//        }
//
//        if (n == 1) return "1"
//
//        val prev = countAndSay(n - 1)
//
//        return encode(prev)
//    }
}
