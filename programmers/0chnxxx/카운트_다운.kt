/**
 * 프로그래머스 다트 협회에서는 매년마다 새로운 특수 룰로 다트 대회를 개최합니다.
 * 이번 대회의 룰은 카운트 다운으로 제로원 룰의 변형 룰입니다.
 * 카운트 다운은 게임이 시작되면 무작위로 점수가 정해지고, 다트를 던지면서 점수를 깎아서 정확히 0점으로 만드는 게임입니다.
 * 단, 남은 점수보다 큰 점수로 득점하면 버스트가 되며 실격 합니다.
 *
 * 다트 과녁에는 1부터 20까지의 수가 하나씩 있고 각 수마다 싱글, 더블, 트리플 칸이 있습니다.
 * 싱글을 맞히면 해당 수만큼 점수를 얻고 더블을 맞히면 해당 수의 두 배만큼 점수를 얻고 트리플을 맞히면 해당 수의 세 배만큼 점수를 얻습니다.
 * 가운데에는 불과 아우터 불이 있는데 카운트 다운 게임에서는 구분 없이 50점을 얻습니다.
 *
 * 대회는 토너먼트로 진행되며 한 게임에는 두 선수가 참가하게 됩니다.
 * 게임은 두 선수가 교대로 한 번씩 던지는 라운드 방식으로 진행됩니다.
 * 가장 먼저 0점을 만든 선수가 승리하는데 만약 두 선수가 같은 라운드에 0점을 만들면 두 선수 중 싱글 또는 불을 더 많이 던진 선수가 승리하며 그 마저도 같다면 선공인 선수가 승리합니다.
 *
 * 다트 실력에 자신 있던 종호는 이 대회에 출전하기로 하였습니다.
 * 최소한의 다트로 0점을 만드는게 가장 중요하고, 그러한 방법이 여러가지 있다면 싱글 또는 불을 최대한 많이 던지는 방법을 선택해야 합니다.
 *
 * 목표 점수 target이 매개변수로 주어졌을 때 최선의 경우 던질 다트 수와 그 때의 싱글 또는 불을 맞춘 횟수(합)를 순서대로 배열에 담아 return 하도록 solution 함수를 완성해 주세요.
 *
 * 1 <= target <= 100000
 */

fun main() {
    val target = 21

    val solution = Solution().solution(target)

    println(solution.joinToString(", "))
}

class Solution {
    fun solution(target: Int): IntArray {
        val maxTarget = 100000
        val dp = Array(target + 1) { Pair(maxTarget, 0) } // (던진 다트 수, Single/Bull 여부)

        dp[0] = Pair(0, 0)

        val scores = mutableListOf<Pair<Int, Int>>() // (점수, Single/Bull 여부)

        for (i in 1..20) {
            scores.add(i to 1)
            scores.add(i * 2 to 0)
            scores.add(i * 3 to 0)
        }

        scores.add(50 to 1)

        // 1점부터 target점까지 최선의 경우 계산
        for (i in 1..target) {
            // 모든 점수의 경우 순회
            for ((score, isSingleOrBull) in scores) {
                // i점보다 큰 점수는 무시
                if (i >= score) {
                    // i점에서 score를 뺀 이전 시점의 최선의 경우
                    val (prevDarts, prevSingles) = dp[i - score]

                    val newDarts = prevDarts + 1
                    val newSingles = prevSingles + isSingleOrBull

                    // 던진 다트 수가 더 적거나 다트 수는 같지만 싱글 또는 불을 더 많이 맞힌 경우 갱신
                    if (newDarts < dp[i].first || (newDarts == dp[i].first && newSingles > dp[i].second)) {
                        dp[i] = Pair(newDarts, newSingles)
                    }
                }
            }
        }

        return intArrayOf(dp[target].first, dp[target].second)
    }
}
