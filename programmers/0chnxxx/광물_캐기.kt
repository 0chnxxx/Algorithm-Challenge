/**
 * 마인은 곡괭이로 광산에서 광석을 캐려고 합니다.
 * 마인은 다이아몬드 곡괭이, 철 곡괭이, 돌 곡괭이를 각각 0개에서 5개까지 가지고 있으며,
 * 곡괭이로 광물을 캘 때는 피로도가 소모됩니다.
 * 각 곡괭이로 광물을 캘 때의 피로도는 아래와 같습니다.
 *
 * 다이아몬드 곡괭이 => 다이아몬드 1, 철 1, 돌 1
 * 철 곡괭이 => 다이아몬드 5, 철 1, 돌 1
 * 돌 곡괭이 => 다이아몬드 25, 철 5, 돌 1
 *
 * 예를 들어, 철 곡괭이는 다이아몬드를 캘 때 피로도가 5가 소모되며
 * 철과 돌을 캘 때는 피로도가 1씩 소모됩니다.
 * 각 곡괭이는 종류에 상관없이 광물 5개를 캔 후에는 더 이상 사용할 수 없습니다.
 *
 * 마인은 다음과 같은 규칙을 지키면서 최소한의 피로도로 광물을 캐려고 합니다.
 *
 * 1. 사용할 수 있는 곡괭이 중 아무거나 하나를 선택해 광물을 캡니다.
 * 2. 한 번 사용하기 시작한 곡괭이는 사용할 수 없을 때까지 사용합니다.
 * 3. 광물은 주어진 순서대로만 캘 수 있습니다.
 * 4. 광산에 있는 모든 광물을 캐거나, 더 사용할 곡괭이가 없을 때까지 광물을 캡니다.
 *
 * 즉, 곡괭이를 하나 선택해서 광물 5개를 연속으로 캐고, 다음 곡괭이를 선택해서 광물 5개를 연속으로 캐는 과정을 반복하며,
 * 더 사용할 곡괭이가 없거나 광산에 있는 모든 광물을 캘 때까지 과정을 반복하면 됩니다.
 *
 * 마인이 갖고 있는 곡괭이의 개수를 나타내는 정수 배열 picks와 광물들의 순서를 나타내는 문자열 배열 minerals가 매개변수로 주어질 때,
 * 마인이 작업을 끝내기까지 필요한 최소한의 피로도를 return 하는 solution 함수를 완성해주세요.
 *
 * picks는 [dia, iron, stone] 과 같은 구조로 이루어져 있습니다.
 *
 * 0 <= dia, iron, stone <= 5
 * dia는 다이아몬드 곡괭이의 수를 의미합니다.
 * iron은 철 곡괭이의 수를 의미합니다.
 * stone은 돌 곡괭이의 수를 의미합니다.
 *
 * 5 <= minerals 의 길이 <= 50
 */

fun main() {
    val picks = intArrayOf(1, 3, 2)
    val minerals = arrayOf("diamond", "diamond", "diamond", "iron", "iron", "diamond", "iron", "stone")

    val solution = Solution().solution(picks, minerals)

    println(solution)
}

class Solution {
    fun solution(picks: IntArray, minerals: Array<String>): Int {
        val fatigue = arrayOf(
            intArrayOf(1, 1, 1), // 다이아몬드 곡괭이
            intArrayOf(5, 1, 1), // 철 곡괭이
            intArrayOf(25, 5, 1) // 돌 곡괭이
        )
        val maxMinerals = picks.sum() * 5
        var totalFatigue = 0

        val limitedMinerals = if (minerals.size > maxMinerals) {
            minerals.copyOf(maxMinerals)
        } else {
            minerals
        }

        val groups = limitedMinerals.asSequence()
            .chunked(5)
            .map { group ->
                group.groupingBy { it }.eachCount().let { counts ->
                    intArrayOf(
                        counts.getOrDefault("diamond", 0),
                        counts.getOrDefault("iron", 0),
                        counts.getOrDefault("stone", 0)
                    )
                }
            }.toList()

        val sortedGroups = groups.sortedByDescending { group ->
            group[0] * 25 + group[1] * 5 + group[2]
        }

        for (group in sortedGroups) {
            var usedPick  = -1

            for (pickIndex in picks.indices) {
                if (picks[pickIndex] > 0) {
                    usedPick = pickIndex
                    break
                }
            }

            if (usedPick == -1) {
                break
            }

            totalFatigue += group[0] * fatigue[usedPick][0] +
                            group[1] * fatigue[usedPick][1] +
                            group[2] * fatigue[usedPick][2]

            picks[usedPick]--
        }

        return totalFatigue
    }
}
