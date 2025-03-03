/**
 * 선물을 직접 전하기 힘들 때 카카오톡 선물하기 기능을 이용해 축하 선물을 보낼 수 있습니다.
 *
 * 당신의 친구들이 이번 달까지 선물을 주고받은 기록을 바탕으로 다음 달에 누가 선물을 많이 받을지 예측하려고 합니다.
 * - 두 사람이 선물을 주고받은 기록이 있다면, 이번 달까지 두 사람 사이에 더 많은 선물을 준 사람이 다음 달에 선물을 하나 받습니다.
 * - 두 사람이 선물을 주고받은 기록이 하나도 없거나 주고받은 수가 같다면, 선물 지수가 더 큰 사람이 선물 지수가 더 작은 사람에게 선물을 하나 받습니다.
 * - 선물 지수는 이번 달까지 자신이 친구들에게 준 선물의 수에서 받은 선물의 수를 뺀 값입니다.
 * - 만약 두 사람의 선물 지수도 같다면 다음 달에 선물을 주고받지 않습니다.
 *
 * 위에서 설명한 규칙대로 다음 달에 선물을 주고받을 때, 당신은 선물을 가장 많이 받을 친구가 받을 선물의 수를 알고 싶습니다.
 *
 * 친구들의 이름을 담은 1차원 문자열 배열 friends
 * 이번 달까지 친구들이 주고받은 선물 기록을 담은 1차원 문자열 배열 gifts
 *
 * 이때, 다음 달에 가장 많이 선물을 받는 친구가 받을 선물의 수를 return 하도록 solution 함수를 완성해 주세요.
 *
 * 2 <= friends의 길이 = 친구들의 수 <= 50
 * friends[i]는 친구의 이름을 의미하는 알파벳 소문자로 이루어진 길이가 10 이하인 문자열입니다.
 * 1 <= gifts의 길이 <= 10000
 * gifts[i]는 "A B" 형태의 문자열로 A는 선물을 준 친구의 이름을 B는 선물을 받은 친구의 이름을 의미하며 공백 하나로 구분됩니다.
 * A, B는 friends의 원소입니다.
 */

fun main() {
    val friends = arrayOf("muzi", "ryan", "frodo", "neo")
    val gifts = arrayOf("muzi frodo", "muzi frodo", "ryan muzi", "ryan muzi", "ryan muzi", "frodo muzi", "frodo ryan", "neo muzi")

    val solution = Solution().solution(friends, gifts)

    println(solution)
}

class Solution {
    fun solution(friends: Array<String>, gifts: Array<String>): Int {
        val giftTable = friends.associateWith { mutableMapOf<String, Int>() }
        val giveAndReceive = friends.groupBy { it }.mapValues { IntArray(2) { 0 } }
        val resultMap = friends.associateWith { 0 }.toMutableMap()

        friends.forEach { f ->
            friends.forEach { t ->
                giftTable[f]!![t] = 0
            }
        }

        for (gift in gifts) {
            val (from, to) = gift.split(' ')

            giftTable[from]!![to] = giftTable[from]!![to]!! + 1
            giveAndReceive[from]!![0]++
            giveAndReceive[to]!![1]++
        }

        for (i in friends.indices) {
            for (j in i + 1 until friends.size) {
                val a = friends[i]
                val b = friends[j]

                val aCount = giftTable[a]!![b]!!
                val bCount = giftTable[b]!![a]!!

                if (aCount > bCount) {
                    resultMap[a] = resultMap[a]!! + 1
                } else if (bCount > aCount) {
                    resultMap[b] = resultMap[b]!! + 1
                } else {
                    val aValue = giveAndReceive[a]!![0] - giveAndReceive[a]!![1]
                    val bValue = giveAndReceive[b]!![0] - giveAndReceive[b]!![1]

                    if (aValue > bValue) {
                        resultMap[a] = resultMap[a]!! + 1
                    } else if (bValue > aValue) {
                        resultMap[b] = resultMap[b]!! + 1
                    }
                }
            }
        }

        return resultMap.maxOf { it.value }
    }
}
