/**
 * 민호는 다단계 조직을 이용하여 칫솔을 판매하고 있습니다.
 * 판매원이 칫솔을 판매하면 그 이익이피라미드 조직을 타고 조금씩 분배되는 형태의 판매망입니다.
 * 어느정도 판매가 이루어진 후, 조직을 운영하던 민호는 조직 내 누가 얼마만큼의 이득을 가져갔는지가 궁금해졌습니다.
 *
 * 민호는 center이며, 판매원 각각은 자신을 조직에 참여시킨 추천인에 연결되어 피라미드 식의 구조를 이루고 있습니다.
 * 조직의 이익 분배 규칙은 간단합니다.
 * 모든 판매원은 칫솔의 판매에 의하여 발생하는 이익에서 10%를 계산하여 자신의 조직에 참여시킨 추천인에게 배분하고 나머지는 자신이 가집니다.
 * 모든 판매원은 자신이 칫솔 판매에서 발생한 이익뿐만 아니라, 자신이 조직에 추천하여 가입시킨 판매원에게서 발생하는 이익의 10%까지 자신에 이익이 됩니다.
 * 자신에게 발생하는 이익 또한 마찬가지의 규칙으로 자신의 추천인에게 분배됩니다.
 * 단 10%를 계산할 때에는 원 단위에서 절사하며 10%를 계산한 금액이 1원 미만인 경우에는 이득을 분배하지 않고 자신이 모두 가집니다.
 *
 * 칫솔의 판매에서 발생하는 이익은 개당 100원으로 정해져 있습니다.
 *
 * 각 판매원의 이름을 담은 배열 enroll
 * 각 판매원을 다단계 조직에 참여시킨 다른 판매원의 이름을 담은 배열 referral
 * 판매량 집계 데이터의 판매원 이름을 나열한 배열 seller
 * 판매량 집계 데이터의 판매 수량을 나열한 배열 amount
 *
 * 각 판매원이 득한 이익금을 나열한 배열을 return 하도록 solution 함수를 완성해주세요.
 * 판매원에게 배분된 이익금의 총합을 계산하여(정수형으로) 입력으로 주어진 enroll에 이름이 포함된 순서에 따라 나열하면 됩니다.
 *
 * 1 <= enroll의 길이 <= 10000 (center 제외)
 * referral의 길이 = enroll의 길이 (추천 없이 조직에 참여한 사람은 "-"가 기입됩니다.)
 * 1 <= seller의 길이 <= 100000
 * amount의 길이 = seller의 길이
 */

fun main() {
    val enroll = arrayOf("john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young")
    val referral = arrayOf("-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward")
    val seller = arrayOf("young", "john", "tod", "emily", "mary")
    val amount = intArrayOf(12, 4, 2, 5, 10)

    val solution = Solution().solution(enroll, referral, seller, amount)

    println(solution.joinToString(", "))
}

class Solution {
    fun solution(enroll: Array<String>, referral: Array<String>, seller: Array<String>, amount: IntArray): IntArray {
        val result = mutableMapOf<String, Int>()
        val parents = mutableMapOf<String, String>()

        for (i in enroll.indices) {
            parents[enroll[i]] = referral[i]
            result[enroll[i]] = 0
        }

        for (i in seller.indices) {
            var current = seller[i]
            var income = amount[i] * 100

            while (current != "-" && income > 0) {
                val commission = income / 10
                val profit = income - commission

                result[current] = result.getOrDefault(current, 0) + profit

                current = parents[current] ?: "-"
                income = commission
            }
        }

        return enroll.map { result[it] ?: 0 }.toIntArray()
    }
}
