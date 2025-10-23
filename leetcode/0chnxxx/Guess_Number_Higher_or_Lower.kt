/**
 * We are playing the Guess Game. The game is as follows:
 * I pick a number from 1 to n.
 * You have to guess which number I picked (the number I picked stays the same throughout the game).
 * Every time you guess wrong, I will tell you whether the number I picked is higher or lower than your guess.
 *
 * You call a pre-defined API int guess(int num), which returns three possible results:
 * -1: Your guess is higher than the number I picked (i.e. num > pick).
 * 1: Your guess is lower than the number I picked (i.e. num < pick).
 * 0: your guess is equal to the number I picked (i.e. num == pick).
 *
 * Return the number that I picked.
 *
 * Constraints:
 * 1 <= n <= 2^31 - 1
 * 1 <= pick <= n
 */

fun main() {
    val n = 10
    val pick = 6

    val game = Solution()

    game.setPickedNumber(pick)

    val result = game.guessNumber(n)

    println(result)
}

abstract class GuessGame {
    private var picked = 0

    fun setPickedNumber(num: Int) {
        picked = num
    }

    fun guess(num: Int): Int {
        return when {
            num > picked -> -1
            num < picked -> 1
            else -> 0
        }
    }

    abstract fun guessNumber(n: Int): Int
}

class Solution: GuessGame() {
    // 시간 복잡도 : O(log N)
    // 공간 복잡도 : O(1)
    override fun guessNumber(n:Int):Int {
        var left = 1
        var right = n

        while (left <= right) {
            val mid = left + (right - left) / 2

            when (guess(mid)) {
                0 -> return mid
                -1 -> right = mid - 1
                1 -> left = mid + 1
            }
        }

        return -1
    }
}