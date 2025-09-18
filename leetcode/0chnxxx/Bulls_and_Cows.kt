/**
 * You are playing the Bulls and Cows game with your friend.
 * You write down a secret number and ask your friend to guess what the number is.
 *
 * When your friend makes a guess, you provide a hint with the following info:
 * The number of "bulls", which are digits in the guess that are in the correct position.
 * The number of "cows", which are digits in the guess that are in your secret number but are located in the wrong position.
 * Specifically, the non-bull digits in the guess that could be rearranged such that they become bulls.
 * Given the secret number secret and your friend's guess guess, return the hint for your friend's guess.
 *
 * The hint should be formatted as "xAyB", where x is the number of bulls and y is the number of cows.
 * Note that both secret and guess may contain duplicate digits.
 *
 * Constraints:
 * 1 <= secret.length, guess.length <= 1000
 * secret.length == guess.length
 * secret and guess consist of digits only.
 */

fun main() {
    val secret = "11"
    val guess = "01"

    val result = Solution().getHint(secret, guess)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun getHint(secret: String, guess: String): String {
        var bull = 0

        val secretCount = IntArray(10)
        val guessCount = IntArray(10)

        for (i in secret.indices) {
            if (secret[i] == guess[i]) {
                bull++
            } else {
                secretCount[secret[i] - '0']++
                guessCount[guess[i] - '0']++
            }
        }

        var cow = 0

        for (d in 0..9) {
            cow += minOf(secretCount[d], guessCount[d])
        }

        return "${bull}A${cow}B"
    }
}