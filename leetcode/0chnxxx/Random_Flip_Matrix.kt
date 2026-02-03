import kotlin.random.Random

/**
 * There is an m x n binary grid matrix with all the values set 0 initially.
 * Design an algorithm to randomly pick an index (i, j) where matrix[i][j] == 0 and flips it to 1.
 * All the indices (i, j) where matrix[i][j] == 0 should be equally likely to be returned.
 * Optimize your algorithm to minimize the number of calls made to the built-in random function of your language and optimize the time and space complexity.
 *
 * Implement the Solution class:
 * Solution(int m, int n) Initializes the object with the size of the binary matrix m and n.
 * int[] flip() Returns a random index [i, j] of the matrix where matrix[i][j] == 0 and flips it to 1.
 * void reset() Resets all the values of the matrix to be 0.
 *
 * Constraints:
 * 1 <= m, n <= 10^4
 * There will be at least one free cell for each call to flip.
 * At most 1000 calls will be made to flip and reset.
 */

fun main() {
    val solution = Solution(3, 1)
    println(solution.flip()) // return [1, 0], [0,0], [1,0], and [2,0] should be equally likely to be returned.
    println(solution.flip()) // return [2, 0], Since [1,0] was returned, [2,0] and [0,0]
    println(solution.flip()) // return [0, 0], Based on the previously returned indices, only [0,0] can be returned.
    println(solution.reset()) // All the values are reset to 0 and can be returned.
    println(solution.flip()) // return [2, 0], [0,0], [1,0], and [2,0] should be equally likely to be returned.
}

class Solution(private val m: Int, private val n: Int) {
    private val map = HashMap<Int, Int>()
    private var total = m * n

    // 시간 복잡도 : O(1)
    // 공간 복잡도 : O(K)
    fun flip(): IntArray {
        val rand = Random.nextInt(total)
        val idx = map[rand] ?: rand

        total--

        map[rand] = map[total] ?: total

        return intArrayOf(idx / n, idx % n)
    }

    // 시간 복잡도 : O(1)
    // 공간 복잡도 : O(K)
    fun reset() {
        map.clear()
        total = m * n
    }
}