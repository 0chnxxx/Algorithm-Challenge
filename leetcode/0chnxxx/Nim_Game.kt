/**
 * You are playing the following Nim Game with your friend:
 * Initially, there is a heap of stones on the table.
 * You and your friend will alternate taking turns, and you go first.
 * On each turn, the person whose turn it is will remove 1 to 3 stones from the heap.
 * The one who removes the last stone is the winner.
 *
 * Given n, the number of stones in the heap, return true if you can win the game assuming both you and your friend play optimally, otherwise return false.
 *
 * Constraints:
 * 1 <= n <= 2^31 - 1
 */

fun main() {
    val n = 4

    val result = Solution().canWinNim(n)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(1)
    // 공간 복잡도 : O(1)
    fun canWinNim(n: Int): Boolean {
        return n % 4 != 0
    }
}