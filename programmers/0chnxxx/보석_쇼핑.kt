/**
 * 개발자 출신으로 세계 최고의 갑부가 된 어피치는 스트레스를 받을 때면 이를 풀기 위해 오프라인 매장에 쇼핑을 하러 가곤 합니다.
 * 어피치는 쇼핑을 할 때면 매장 진열대의 특정 범위의 물건들을 모두 싹쓸이 구매하는 습관이 있습니다.
 * 어느 날 스트레스를 풀기 위해 보석 매장에 쇼핑을 하러 간 어피치는 이전처럼 진열대의 특정 범위의 보석을 모두 구매하되 특별히 아래 목적을 달성하고 싶었습니다.
 *
 * [진열된 모든 종류의 보석을 적어도 1개 이상 포함하는 가장 짧은 구간을 찾아서 구매]
 *
 * 진열대 번호 순서대로 보석들의 이름이 저장된 배열 gems가 매개변수로 주어집니다.
 * 이때 모든 보석을 하나 이상 포함하는 가장 짧은 구간을 찾아서 return 하도록 solution 함수를 완성해주세요.
 * 가장 짧은 구간의 시작 진열대 번호와 끝 진열대 번호를 차례대로 배열에 담아서 return 하도록 하며, 만약 가장 짧은 구간이 여러 개라면 시작 진열대 번호가 가장 작은 구간을 return 합니다.
 *
 * 1 <= gems 배열의 크기 <= 100000
 */

fun main() {
    val gems = arrayOf("DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA")

    val result = Solution().solution(gems)

    println(result.joinToString(" "))
}

class Solution {
    fun solution(gems: Array<String>): IntArray {
        val gemTypes = gems.toMutableSet()
        val gemCount = mutableMapOf<String, Int>()

        var start = 0
        var end = 0
        var minLength = Int.MAX_VALUE
        var answer = intArrayOf(0, gems.size - 1)

        // end를 통해 윈도우를 확장
        while (end < gems.size) {
            gemCount[gems[end]] = gemCount.getOrDefault(gems[end], 0) + 1
            end++

            // start를 통해 윈도우를 축소
            while (gemCount.size == gemTypes.size) {
                if (end - start < minLength) {
                    minLength = end - start
                    answer = intArrayOf(start + 1, end)
                }

                gemCount[gems[start]] = gemCount[gems[start]]!! - 1

                if (gemCount[gems[start]] == 0) {
                    gemCount.remove(gems[start])
                }

                start++
            }
        }

        return answer
    }
}
