/**
 * 시소는 중심으로부터 2(m), 3(m), 4(m) 거리의 지점에 좌석이 하나씩 있다.
 * 시소를 두 명이 마주 보고 탄다.
 * 탑승한 사람의 무게와 시소 축과 좌석 간의 거리의 곱이 양쪽 다 같다면 시소 짝꿍이다.
 * 사람들의 몸무게 목록 weights가 주어질 때 시소 짝꿍이 몇 쌍 존재하는지 구하여라.
 *
 * 2 <= weights의 길이 <= 100000
 * 100 <= weights의 원소 <= 1000
 * 몸무게 단위는 N(뉴턴)
 */

class Solution {
    fun solution(weights: IntArray): Long {
        var result = 0L
        val originalWeights = LongArray(1001) { 0 } // 몸무게의 최대치인 1000으로 초기화
        val calculatedWeights = LongArray(4001) { 0 } // 계산된 몸무게의 최대치인 4000으로 초기화

        for (i in 0 until weights.size) {
            val weight = weights[i]
            val originalWeight = originalWeights[weight]
            val case2 = weight * 2
            val case3 = weight * 3
            val case4 = weight * 4

            // 중복된 사람 확인
            if (originalWeight > 0) {
                result += originalWeight
                result += calculatedWeights[case2] - originalWeight
                result += calculatedWeights[case3] - originalWeight
                result += calculatedWeights[case4] - originalWeight
            } else {
                result += calculatedWeights[case2] - originalWeight
                result += calculatedWeights[case3] - originalWeight
                result += calculatedWeights[case4] - originalWeight
            }

            originalWeights[weight]++
            calculatedWeights[case2]++
            calculatedWeights[case3]++
            calculatedWeights[case4]++
        }

        return result
    }
}

fun main(args: Array<String>) {
    println(Solution().solution(intArrayOf(100, 180, 360, 100, 270)))
}
