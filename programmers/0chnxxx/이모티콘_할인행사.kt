/**
 * 카카오톡에서는 이모티콘을 무제한으로 사용할 수 있는 이모티콘 플러스 서비스 가입자 수를 늘리려고 합니다.
 * 이를 위해 카카오톡에서는 이모티콘 할인 행사를 하는데 목표는 다음과 같습니다.
 *
 * 1. 이모티콘 플러스 서비스 가입자를 최대한 늘리는 것
 * 2. 이모티콘 판매액을 최대한 늘리는 것
 *
 * 1번 목표가 우선이며 2번 목표가 그 다음입니다.
 *
 * 이모티콘 할인 행사는 다음과 같은 방식으로 진행됩니다.
 *
 * 1. n명의 카카오톡 사용자들에게 이모티콘 m개를 할인하여 판매합니다.
 * 2. 이모티콘마다 할인율은 다를 수 있으며, 할인율은 10%, 20%, 30%, 40% 중 하나로 설정됩니다.
 *
 * 카카오톡 사용자들은 다음과 같은 기준을 따라 이모티콘을 사거나, 이모티콘 플러스 서비스에 가입합니다.
 *
 * 1. 각 사용자들은 자신의 기준에 따라 일정 비율 이상 할인하는 이모티콘을 모두 구매합니다.
 * 2. 각 사용자들은 자신의 기준에 따라 이모티콘 구매 비용의 합이 일정 가격 이상이 된다면, 이모티콘 구매를 모두 취소하고 이모티콘 플러스 서비스에 가입합니다.
 *
 * 카카오톡 사용자 n명의 구매 기준을 담은 2차원 정수 배열 users, 이모티콘 m개의 정가를 담은 1차원 정수 배열 emotions가 주어집니다.
 * users의 원소는 [비율, 가격]의 형태입니다.
 * 이때, 행사 목적을 최대한으로 달성했을 때의 이모티콘 플러스 서비스 가입 수와 이모티콘 매출액을 1차원 정수 배열에 담아 return 하도록 solution 함수를 완성해주세요.
 *
 * 1 <= users의 길이 = n <= 100
 * 1 <= 비율 <= 40
 * 100 <= 가격 <= 1000000
 * 1 <= emotions의 길이 = m <= 7
 * 100 <= emotions의 원소 <= 1000000
 */

fun main() {
    val users = arrayOf(
        intArrayOf(40, 10000),
        intArrayOf(25, 10000)
    )
    val emotions = intArrayOf(7000, 9000)

    val solution = Solution().solution(users, emotions)

    println(solution.joinToString(", "))
}

class Solution {
    fun solution(users: Array<IntArray>, emoticons: IntArray): IntArray {
        // 할인율 정의
        val discountRates = arrayOf(0.1, 0.2, 0.3, 0.4)
        // 최대 구독자
        var maxSubscribers = 0
        // 최대 판매액
        var maxSales = 0
        // 이모티콘 할인율 조합
        val combinations = mutableListOf<List<Double>>()

        // 조합 생성
        fun generate(list: MutableList<Double>) {
            // 탈출 조건 (모든 이모티콘에 할인율 매겨지면 탈출)
            if (list.size == emoticons.size) {
                combinations.add(list.toList())
                return
            }

            // 백트래킹 & 재귀
            for (rate in discountRates) {
                list.add(rate)
                generate(list)
                list.removeAt(list.size - 1)
            }
        }

        generate(mutableListOf())

        // 조합으로 만든 모든 할인 경우에 대해
        for (discounts in combinations) {
            var subscribers = 0
            var sales = 0

            // 모든 유저를 순회하며
            for (user in users) {
                val requiredDiscount = user[0]
                val maxPrice = user[1]
                var spend = 0

                // 해당 경우대로 할인한다고 했을 때
                for (i in emoticons.indices) {
                    // 유저가 원하는 할인율보다 높다면 할인된 가격으로 구매
                    if ((discounts[i] * 100) >= requiredDiscount) {
                        spend += emoticons[i] - (emoticons[i] * discounts[i]).toInt()
                    }
                }

                // 유저가 원하는 최대 비용과 구매 비용을 비교하여 구독 혹은 지불
                if (spend >= maxPrice) {
                    subscribers++
                } else {
                    sales += spend
                }
            }

            // 이모티콘 할인 행사의 1순위, 2순위 목표로 최종 결과 구하기
            if (subscribers > maxSubscribers || (subscribers == maxSubscribers && sales > maxSales)) {
                maxSubscribers = subscribers
                maxSales = sales
            }
        }

        // 결과 반환
        return intArrayOf(maxSubscribers, maxSales)
    }
}
