/**
 * You are given an integer array score of size n, where score[i] is the score of the ith athlete in a competition.
 * All the scores are guaranteed to be unique.
 *
 * The athletes are placed based on their scores, where the 1st place athlete has the highest score, the 2nd place athlete has the 2nd highest score, and so on.
 * The placement of each athlete determines their rank:
 * The 1st place athlete's rank is "Gold Medal".
 * The 2nd place athlete's rank is "Silver Medal".
 * The 3rd place athlete's rank is "Bronze Medal".
 * For the 4th place to the nth place athlete, their rank is their placement number (i.e., the xth place athlete's rank is "x").
 * Return an array answer of size n where answer[i] is the rank of the ith athlete.
 *
 * Constraints:
 * n == score.length
 * 1 <= n <= 10^4
 * 0 <= score[i] <= 10^6
 * All the values in score are unique.
 */

fun main() {
    val score = intArrayOf(5, 4, 3, 2, 1)

    val result = Solution().findRelativeRanks(score)

    println(result.joinToString(", "))
}

class Solution {
    // 시간 복잡도 : O(N log N)
    // 공간 복잡도 : O(N)
    fun findRelativeRanks(score: IntArray): Array<String> {
        val answer = Array(score.size) { "" }

        score
            .mapIndexed { idx, score -> idx to score }
            .sortedByDescending { it.second }
            .forEachIndexed { rank, (idx, _) ->
                answer[idx] = when (rank) {
                    0 -> "Gold Medal"
                    1 -> "Silver Medal"
                    2 -> "Bronze Medal"
                    else -> (rank + 1).toString()
                }
            }

        return answer
    }
}