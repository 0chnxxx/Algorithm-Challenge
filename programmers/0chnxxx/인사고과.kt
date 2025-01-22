/**
 * 원호네 회사는 연말마다 1년 간의 인사고과에 따라 인센티브를 지급합니다.
 * 각 사원마다 근무 태도 점수와 동료 평가 점수가 기록되어 있는데 만약 어떤 사원이 다른 임의의 사원보다 두 점수가 모두 낮은 경우가 한 번이라도 있다면 그 사원은 인센티브를 받지 못합니다.
 * 그렇지 않은 사원들에 대해서는 두 점수의 합이 높은 순으로 석차를 내어 석차에 따라 인센티브가 차등 지급됩니다.
 * 이때, 두 점수의 합이 동일한 사원들은 동석차이며, 동석차의 수만큼 다음 석차는 건너뜁니다.
 * 예를 들어, 점수의 합이 가장 큰 사원이 2명이라면 1등이 2명이고 2등 없이 다음 석차는 3등부터입니다.
 *
 * 각 사원의 근무 태도 점수와 동료 평가 점수 목록 scores가 주어졌을 때, 완호의 석차를 return하도록 solution 함수를 완성해주세요.
 * 완호가 인센티브를 받지 못하는 경우 -1을 return합니다.
 *
 * 1 <= scores의 길이 <= 100000
 * scores의 각 행은 한 사원의 근무 태도 점수와 동료 평가 점수를 나타내며 [a, b] 형태입니다.
 * scores[0]은 완호의 점수입니다.
 * 0 <= a, b <= 100000
 */

fun main() {
    val scores = arrayOf(
        intArrayOf(2, 2),
        intArrayOf(1, 4),
        intArrayOf(3, 2),
        intArrayOf(3, 2),
        intArrayOf(2, 1)
    )

    val solution = Solution().solution(scores)

    println(solution)
}

class Solution {
    fun solution(scores: Array<IntArray>): Int {
        val target = scores[0]
        val targetScore = target[0] + target[1]
        val sortedScores = scores.sortedWith(compareByDescending<IntArray> { it[0] }.thenBy { it[1] })
        val filteredScores = mutableListOf<IntArray>()
        var maxScore = -1

        for (score in sortedScores) {
            if (score[1] >= maxScore) {
                filteredScores.add(score)
                maxScore = score[1]
            }
        }

        if (scores.any { it[0] > target[0] && it[1] > target[1] }) {
            return -1
        }

        val ranks = filteredScores
            .map { it[0] + it[1] }
            .sortedDescending()

        var rank = 1

        for (score in ranks) {
            if (score > targetScore) {
                rank++
            } else if (score == targetScore) {
                return rank
            }
        }

        return rank
    }
}
