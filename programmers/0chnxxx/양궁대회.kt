/**
 * 카카오배 양궁대회가 열렸습니다.
 * 라이언은 저번 카카오배 양궁대회 우승자이고 이번 대회에도 결승전까지 올라왔습니다.
 * 결승전 상대는 어피치입니다.
 * 카카오배 양궁대회 운영위원회는 한 선수의 연속 우승보다는 다양한 선수들이 양궁대회에서 우승하기를 원합니다.
 * 따라서, 양궁대회 운영위원회는 결승전 규칙을 전 대회 우승자인 라이언에게 불리하게 다음과 같이 정했습니다.
 *
 * 1. 어피치가 화살 n발을 다 쏜 후에 라이언이 화살 n발을 쏩니다.
 * 2. 점수를 계산합니다.
 * 2-1. 과녁판은 가운데 원부터 10점부터 0점까지 있습니다.
 * 2-2. 만약 k점을 어피치가 a발을 맞혔고 라이언이 b발을 맞혔을 경우 더 많은 화살을 k점에 맞힌 선수가 k점을 가져갑니다.
 * 단, a = b일 경우는 어피치가 k점을 가져갑니다.
 * k점을 여러 발 맞혀도 k점보다 많은 점수를 가져가는게 아니고 k점만 가져가는 것을 유의하세요.
 * 또한 a = b = 0 인 경우, 즉, 라이언과 어피치 모두 k점에 단 하나의 화살도 맞히지 못한 경우는 어느 누구도 k점을 가져가지 않습니다.
 * 3. 최종 점수가 더 높은 선수를 우승자로 결정합니다.
 * 단, 최종 점수가 같을 경우 어피치를 우승자로 결정합니다.
 *
 * 현재 상황은 어피치가 화살 n발을 다 쏜 후이고 라이언이 화살을 쏠 차레입니다.
 * 라이언은 어피치를 가장 큰 점수 차이로 이기기 위해서 n발의 화살을 어떤 과녁 점수에 맞혀야 하는지를 구하려고 합니다.
 * 화살의 개수를 담은 자연수 n, 어피치가 맞힌 과녁 점수의 개수를 10점부터 0점까지 순서대로 담은 정수 배열 info가 매개변수로 주어집니다.
 * 이때, 라이언이 가장 큰 점수 차이로 우승하기 위해 n발의 화살을 어떤 과녁 점수에 맞혀야 하는지를 10점부터 0점까지 순서대로 정수 배열에 담아 return해주세요.
 * 만약, 라이언이 우승할 수 없는 경우(무조건 지거나 비기는 경우)는 [-1]을 return 해주세요.
 *
 * 1 <= n <= 10
 * info의 길이 = 11
 * 0 <= info의 원소 <= n
 */

fun main() {
    val n = 5
    val info = intArrayOf(2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0)

    val solution = Solution().solution(n, info)

    println(solution.joinToString(", "))
}

class Solution {
    var maxDiff = 0
    var bestCombination = IntArray(11) { 0 }

    fun solution(n: Int, info: IntArray): IntArray {
        backtrack(0, n, IntArray(11), info)

        return if (maxDiff > 0) {
            bestCombination
        } else {
            intArrayOf(-1)
        }
    }

    private fun backtrack(score: Int, arrows: Int, ryan: IntArray, apeach: IntArray) {
        if (score == 11) {
            ryan[10] += arrows

            val diff = calculateScore(ryan, apeach)

            if (diff > maxDiff || (diff == maxDiff && isBetterCombination(ryan))) {
                maxDiff = diff
                bestCombination = ryan.clone()
            }

            ryan[10] -= arrows

            return
        }

        if (arrows > apeach[score]) {
            ryan[score] = apeach[score] + 1
            backtrack(score + 1, arrows - ryan[score], ryan, apeach)
            ryan[score] = 0
        }

        backtrack(score + 1, arrows, ryan, apeach)
    }

    private fun calculateScore(ryan: IntArray, apeach: IntArray): Int {
        var ryanScore = 0
        var apeachScore = 0

        for (i in 0..10) {
            val point = 10 - i

            if (ryan[i] > apeach[i]) {
                ryanScore += point
            } else if (apeach[i] > 0) {
                apeachScore += point
            }
        }

        return ryanScore - apeachScore
    }

    private fun isBetterCombination(ryan: IntArray): Boolean {
        for (i in 10 downTo 0) {
            if (ryan[i] != bestCombination[i]) {
                return ryan[i] > bestCombination[i]
            }
        }

        return false
    }
}
