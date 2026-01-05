/**
 * You are given an integer array matchsticks where matchsticks[i] is the length of the ith matchstick.
 * You want to use all the matchsticks to make one square.
 * You should not break any stick, but you can link them up, and each matchstick must be used exactly one time.
 * Return true if you can make this square and false otherwise.
 *
 * Constraints:
 * 1 <= matchsticks.length <= 15
 * 1 <= matchsticks[i] <= 10^8
 */

fun main() {
    val matchsticks = intArrayOf(1, 1, 2, 2, 2)

    val result = Solution().makesquare(matchsticks)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(4^N)
    // 공간 복잡도 : O(N)
    fun makesquare(matchsticks: IntArray): Boolean {
        val sum = matchsticks.sum()

        // 성냥개비 전체 길이의 합이 4로 나누어떨어져야함
        if (sum % 4 != 0) return false

        val target = sum / 4

        // 가지치기 효율 극대화
        matchsticks.sortDescending()

        // 가장 긴 성냥개비가 한 변의 길이보다 길면 안됨
        if (matchsticks[0] > target) return false

        val sides = IntArray(4)

        fun dfs(index: Int): Boolean {
            if (index == matchsticks.size) {
                // 모든 성냥개비를 다 사용하면서 4변이 모두 target과 같으면 성공
                return sides[0] == target && sides[1] == target && sides[2] == target && sides[3] == target
            }

            val stick = matchsticks[index]

            for (i in 0 until 4) {
                // 한 변이 target을 넘어가면 안됨
                if (sides[i] + stick > target) continue
                // 같은 길이의 변에는 중복 시도를 안함
                if (i > 0 && sides[i] == sides[i - 1]) continue

                // 백트래킹
                sides[i] += stick
                if (dfs(index + 1)) return true
                sides[i] -= stick
            }

            return false
        }

        return dfs(0)
    }
}