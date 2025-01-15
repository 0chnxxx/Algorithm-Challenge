/**
 * n명의 권투선수가 권투 대회에 참여했고 각각 1번부터 n번까지 번호를 받았습니다.
 * 권투 경기는 1대1 방식으로 진행이 되고, 만약 A 선수가 B 선수보다 실력이 좋다면 A 선수는 B 선수를 항상 이깁니다.
 * 심판은 주어진 경기 결과를 가지고 선수들의 순위를 매기려 합니다.
 * 하지만 몇몇 경기 결과를 분실하여 정확하게 순위를 매길 수 없습니다.
 *
 * 선수의 수 n, 경기 결과를 담은 2차원 배열 results가 매개변수로 주어질 때
 * 정확하게 순위를 매길 수 있는 선수의 수를 return하도록 solution 함수를 작성해주세요.
 *
 * 1 <= n <= 100
 * 1 <= results의 길이 <= 4500
 * results의 원소 [A, B]는 A 선수가 B 선수를 이겼다는 의미입니다.
 */

fun main() {
    val n = 5
    val results = arrayOf(
        intArrayOf(4, 3),
        intArrayOf(4, 2),
        intArrayOf(3, 2),
        intArrayOf(1, 2),
        intArrayOf(2, 5)
    )

    val solution = Solution().solution(n, results)

    println(solution)
}

class Solution {
    fun solution(n: Int, results: Array<IntArray>): Int {
        // 선수 간의 승패관계를 담는 그래프
        val graph = Array(n + 1) { BooleanArray(n + 1) }

        // 직접적인 승패관계 저장
        for ((winner, loser) in results) {
            graph[winner][loser] = true
        }

        // A가 B를 이기고 B가 C를 이겼다면 A는 C를 이겼기에
        // 직접적인 승패관계를 기반으로 승패관계를 확장 추론
        for (player in 1..n) {
            for (winner in 1..n) {
                for (loser in 1..n) {
                    if (graph[winner][player] && graph[player][loser]) {
                        graph[winner][loser] = true
                    }
                }
            }
        }

        var count = 0

        // 확장된 승패관계를 기반으로 순위가 명확한 선수 찾기
        for (player in 1..n) {
            val winCount = graph[player].count { it }
            val loseCount =  graph.indices.count { graph[it][player] }

            if ((winCount + loseCount) == n - 1) {
                count++
            }
        }

        return count
    }
}
