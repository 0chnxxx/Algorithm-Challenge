/**
 * 당신은 순서대로 n개의 퍼즐을 제한 시간 내에 풀어야 하는 퍼즐 게임을 하고 있습니다.
 * 각 퍼즐은 난이도와 소요 시간이 정해져 있습니다.
 * 당신의 숙련도에 따라 퍼즐을 풀 때 틀리는 횟수가 바뀌게 됩니다.
 * 현재 퍼즐의 난이도를 diff, 현재 퍼즐의 소요 시간을 time_cur, 이전 퍼즐의 소요 시간을 time_prev, 당신의 숙련도를 level이라 하면, 게임은 다음과 같이 진행됩니다.
 *
 * 1. diff <= level
 * 퍼즐을 틀리지 않고 time_cur 만큼의 시간을 사용하여 해결합니다.
 *
 * 2.diff > level
 * 퍼즐을 총 diff - level번 틀립니다.
 * 퍼즐을 틀릴 때마다, time_cur 만큼의 시간을 사용하며, 추가로 time_prev 만큼의 시간을 사용해 이전 퍼즐을 다시 풀고 와야 합니다.
 * 이전 퍼즐을 다시 풀 때는 이전 퍼즐의 난이도에 상관없이 틀리지 않습니다.
 * diff - level번 틀린 이후에 다시 퍼즐을 풀면 time_cur 만큼의 시간을 사용하여 퍼즐을 해결합니다.
 *
 * 예를 들어 diff = 3, time_cur = 2, time_prev = 4인 경우, level에 따라 퍼즐을 푸는데 걸리는 시간은 다음과 같습니다.
 *
 * 1. level = 1
 * 퍼즐을 3 - 1 = 2번 틀립니다.
 * 한 번 틀릴 때마다 2 + 4 = 6의 시간을 사용하고, 다시 퍼즐을 푸는데 2의 시간을 사용하므로 총 6 * 2 + 2 = 14의 시간을 사용하게 됩니다.
 *
 * 2. level = 2
 * 퍼즐을 3 - 2 = 1번 틀리므로, 6 + 2 = 8의 시간을 사용하게 됩니다.
 *
 * 3. level >= 3
 * 퍼즐을 틀리지 않으며 2의 시간을 사용하게 됩니다.
 *
 * 퍼즐 게임에는 전체 제한 시간 limit가 정해져 있습니다.
 * 제한 시간 내에 퍼즐을 모두 해결하기 위한 숙련도의 최솟값을 구하려고 합니다.
 * 난이도, 소요 시간은 모두 양의 정수며, 숙련도도 양의 정수여야 합니다.
 *
 * 퍼즐의 난이도를 순서대로 담은 1차원 정수 배열 diffs
 * 퍼즐의 소요 시간을 순서대로 담은 1차원 정수 배열 times
 * 전체 제한 시간 limit
 *
 * 제한 시간 내에 퍼즐을 모두 해결하기 위한 숙련도의 최솟값을 정수로 return 하도록 solution 함수를 완성해 주세요.
 *
 * 1 <= diffs의 길이 = times의 길이 = n <= 300000
 * diffs[i]는 i번째 퍼즐의 난이도, times[i]는 i번째 퍼즐의 소요 시간입니다.
 * diffs[0] = 1
 * 1 <= diffs[i] <= 100000
 * 1 <= times[i] <= 10000
 * 1 <= limit <= 10^15
 */

fun main() {
    val diffs = intArrayOf(1, 5, 3)
    val times = intArrayOf(2, 4, 7)
    val limit = 30L

    val solution = Solution().solution(diffs, times, limit)

    println(solution)
}

class Solution {
    fun solution(diffs: IntArray, times: IntArray, limit: Long): Int {
        var level = 1
        var left = 1
        var right = diffs.maxOrNull() ?: 1

        while (left <= right) {
            val mid = (left + right) / 2

            if (canSolve(mid, diffs, times, limit)) {
                level = mid
                right = mid - 1
            } else {
                left = mid + 1
            }
        }

        return level
    }

    private fun canSolve(level: Int, diffs: IntArray, times: IntArray, limit: Long): Boolean {
        var totalTime = 0L

        for (i in diffs.indices) {
            val diff = diffs[i]
            val curTime = times[i]
            val prevTime = if (i == 0) 0 else times[i - 1]

            if (diff <= level) {
                totalTime += curTime
            } else if (diff > level) {
                totalTime += (curTime + prevTime) * (diff - level) + curTime
            }

            if (totalTime > limit) {
                return false
            }
        }

        return totalTime <= limit
    }
}
