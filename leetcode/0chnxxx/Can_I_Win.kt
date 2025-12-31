/**
 * In the "100 game" two players take turns adding, to a running total, any integer from 1 to 10.
 * The player who first causes the running total to reach or exceed 100 wins.
 * What if we change the game so that players cannot re-use integers?
 * For example, two players might take turns drawing from a common pool of numbers from 1 to 15 without replacement until they reach a total >= 100.
 * Given two integers maxChoosableInteger and desiredTotal, return true if the first player to move can force a win, otherwise, return false.
 * Assume both players play optimally.
 *
 * Constraints:
 * 1 <= maxChoosableInteger <= 20
 * 0 <= desiredTotal <= 300
 */

fun main() {
    val maxChoosableInteger = 10
    val desiredTotal = 11

    val result = Solution().canIWin(maxChoosableInteger, desiredTotal)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N * 2^N)
    // 공간 복잡도 : O(2^N)
    fun canIWin(maxChoosableInteger: Int, desiredTotal: Int): Boolean {
        // 가지치기1: 필요한 누적합이 0 이하인 경우는 무조건 승리
        if (desiredTotal <= 0) return true

        // 가지치기2: 모든걸 다 골라도 필요한 누적합보다 작은 경우엔 무조건 패배
        val maxSum = (1 + maxChoosableInteger) * maxChoosableInteger / 2

        if (maxSum < desiredTotal) return false

        // 메모이제이션을 통해 사용된 숫자들을 기억
        val memo = HashMap<Int, Boolean>()

        // 메모이제이션을 활용한 깊이우선탐색
        fun dfs(usedMask: Int, currentSum: Int): Boolean {
            // 이미 처리했던 케이스인 경우 그대로 반환
            memo[usedMask]?.let { return it }

            // 고를 수 있는 모든 숫자를 시도
            for (i in 1..maxChoosableInteger) {
                val bit = 1 shl (i - 1)

                // 이미 사용했던 숫자면 스킵
                if (usedMask and bit != 0) continue

                // 승리 조건 검증
                if (currentSum + i >= desiredTotal) {
                    memo[usedMask] = true
                    return true
                }

                // 상대에게 턴 전환
                val nextMask = usedMask or bit

                // 상대도 승리할 수 없는지 확인
                if (!dfs(nextMask, currentSum + i)) {
                    memo[usedMask] = true
                    return true
                }
            }

            // 모든 시도를 했음에도 결과가 나오지 않는다면 패배
            memo[usedMask] = false
            return false
        }

        return dfs(0, 0)
    }
}