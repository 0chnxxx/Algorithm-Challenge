/**
 * 당신은 코딩 테스트를 준비하기 위해 공부하려고 합니다.
 * 코딩 테스트 문제를 풀기 위해서는 알고리즘에 대한 지식과 코드를 구현하는 능력이 필요합니다.
 *
 * 알고리즘에 대한 지식은 알고력, 코드를 구현하는 능력은 코딩력이라고 표현합니다.
 * 알고력과 코딩력은 0 이상의 정수로 표현됩니다.
 *
 * 문제를 풀기 위해서는 문제가 요구하는 일정 이상의 알고력과 코딩력이 필요합니다.
 * 풀 수 없는 문제를 해결하기 위해서는 알고력과 코딩력을 높여야 합니다.
 *
 * 알고력과 코딩력을 높이기 위한 다음과 같은 방법들이 있습니다.
 * - 알고력을 높이기 위해 알고리즘 공부를 합니다. 알고력 1을 높이기 위해서 1위 시간이 필요합니다.
 * - 코딩력을 높이기 위해 코딩 공부를 합니다. 코딩력 1을 높이기 위해서 1의 시간이 필요합니다.
 * - 현재 풀 수 있는 문제 중 하나를 풀어 알고력과 코딩력을 높입니다. 각 문제마다 문제를 풀면 올라가는 알고력과 코딩력이 정해져 있습니다.
 * - 문제를 하나 푸는 데는 문제가 요구하는 시간이 필요하며 같은 문제를 여러 번 푸는 것이 가능합니다.
 *
 * 당신은 주어진 모든 문제들을 풀 수 있는 알고력과 코딩력을 얻는 최단시간을 구하려 합니다.
 *
 * 초기의 알고력과 코딩력을 담은 정수 alp와 cop, 문제의 정보를 담은 2차원 정수 배열 problems가 매개변수로 주어졌을 때,
 * 모든 문제들을 풀 수 있는 알고력과 코딩력을 얻는 최단시간을 return 하도록 solution 함수를 작성해주세요.
 *
 * problems의 원소는 [alp_req, cop_req, alp_rwd, cop_rwd, cost]의 형태로 이루어져 있습니다.
 * alp_req와 cop_req는 문제를 푸는데 필요한 알고력과 코딩력입니다.
 * alp_rwd와 cop_rwd는 문제를 풀었을 때 증가하는 알고력과 코딩력입니다.
 * cost는 문제를 푸는데 드는 시간입니다.
 *
 * [효율성 테스트 케이스 제한 사항]
 * 0 <= alp, cop <= 150
 * 1 <= problems의 길이 <= 100
 * 0 <= alp_req, cop_req <= 150
 * 0 <= alp_rwd, cop_rwd <= 30
 * 1 <= cost <= 100
 *
 * [정확성 테스트 케이스 제한 사항]
 * 0 <= alp, cop <= 20
 * 1 <= problems의 길이 <= 6
 * 0 <= alp_req, cop_req <= 20
 * 0 <= alp_rwd, cop_rwd <= 5
 * 1 <= cost <= 10
 */

fun main() {
    val alp = 10
    val cop = 10
    val problems = arrayOf(
        intArrayOf(10, 15, 2, 1, 2),
        intArrayOf(20, 20, 3, 3, 4)
    )

    val solution = Solution().solution(alp, cop, problems)

    println(solution)
}

class Solution {
    fun solution(alp: Int, cop: Int, problems: Array<IntArray>): Int {
        // 목표 alp, cop를 계산
        val maxAlp = Math.max(alp, problems.maxOf { it[0] })
        val maxCop = Math.max(cop, problems.maxOf { it[1] })

        // alp, cop의 각 점수에 대해 필요한 최소 시간을 저장
        val dp = Array(maxAlp + 1) { IntArray(maxCop + 1) { Int.MAX_VALUE } }

        dp[alp][cop] = 0

        // 모든 경우 탐색
        for (i in alp..maxAlp) {
            for (j in cop..maxCop) {
                // 기본 학습
                // if문은 범위를 벗어나는 것을 방지하기 위함
                // 문제를 풀어 추가 학습을 했더라도 기본 학습이 더 시간이 덜 든다면 min으로 보정
                if (i < maxAlp) dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + 1)
                if (j < maxCop) dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + 1)

                // 문제 풀기
                for (problem in problems) {
                    val (alpReq, copReq, alpRwd, copRwd, cost) = problem

                    // alp, cop가 충족돼서 풀 수 있는 경우에만 추가 학습
                    if (i >= alpReq && j >= copReq) {
                        // min을 구하는 이유는 목표 alp, cop를 벗어나는 것을 방지하기 위함
                        val newAlp = Math.min(maxAlp, i + alpRwd)
                        val newCop = Math.min(maxCop, j + copRwd)

                        // 해당 점수까지 가기 위한 시간 저장
                        dp[newAlp][newCop] = Math.min(dp[newAlp][newCop], dp[i][j] + cost)
                    }
                }
            }
        }

        // 목표 alp, cop 까지 도달하기 위한 최소 시간을 반환
        // 문제 조건에 따라 모든 문제를 풀 수 없는 경우는 없기 때문에 무조건 maxAlp, maxCop까지 도달
        return dp[maxAlp][maxCop]
    }
}
