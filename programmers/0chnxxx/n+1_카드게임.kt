/**
 * 당신은 1~n 사이의 수가 적힌 카드가 하나씩 있는 카드 뭉치와 동전 coin 개를 이용한 게임을 하려고 합니다.
 *
 * 카드 뭉치에서 카드를 뽑는 순서가 정해져 있으며, 게임은 다음과 같이 진행합니다.
 * 1. 처음에 카드 뭉치에서 카드 n / 3장을 뽑아 모두 가집니다.
 *    당신은 카드와 교환 가능한 동전 coin개를 가지고 있습니다.
 * 2. 게임은 1라운드부터 시작되며, 각 라운드가 시작할 때 카드를 두 장 뽑습니다.
 *    카드 뭉치에 남은 카드가 없다면 게임을 종료합니다.
 *    뽑은 카드는 카드 한 장당 동전 하나를 소모해 가지거나, 동전을 소모하지 않고 버릴 수 있습니다.
 * 3. 카드에 적힌 수의 합이 n + 1이 되도록 카드 두 장을 내고 다음 라운드로 진행할 수 있습니다.
 *    만약 카드 두 장을 낼 수 없다면 게임을 종료합니다.
 *
 * 처음에 가진 동전수를 나타내는 정수 coin
 * 카드를 뽑는 순서대로 카드에 적힌 수를 담은 1차원 정수 배열 cards
 *
 * 게임에서 도달 가능한 최대 라운드의 수를 return 하도록 solution 함수를 완성해 주세요.
 *
 * 0 <= coin <= n
 * 6 <= cards의 길이 = n < 1000
 * cards[i]는 i + 1번째로 뽑는 카드에 적힌 수를 나타냅니다.
 * 1 <= cards[i] <= n
 * n은 6의 배수입니다.
 */

fun main() {
    val coin = 4
    val cards = intArrayOf(3, 6, 7, 2, 1, 10, 5, 9, 8, 12, 11, 4)

    val solution = Solution().solution(coin, cards)

    println(solution)
}

class Solution {
    fun solution(coin: Int, cards: IntArray): Int {
        val n = cards.size
        var leftCoins = coin
        var round = 1

        val hand = cards.slice(0 until n / 3).toMutableSet()
        val leftCards = cards.drop(n / 3).reversed().toMutableList()
        val pickedCards = mutableSetOf<Int>()

        while (leftCards.isNotEmpty()) {
            pickedCards.add(leftCards.removeAt(leftCards.lastIndex))
            pickedCards.add(leftCards.removeAt(leftCards.lastIndex))

            var isFind = false

            // 초기 카드에서 짝을 만듦
            for (card in hand.toList()) {
                val other = (n + 1) - card

                if (other in hand && card != other) {
                    hand.remove(card)
                    hand.remove(other)

                    round++
                    isFind = true
                    break
                }
            }

            if (isFind) continue

            // 초기 카드 + 뽑은 카드로 짝을 만듦
            if (leftCoins >= 1 && hand.isNotEmpty() && pickedCards.isNotEmpty()) {
                for (card in hand.toList()) {
                    val other = (n + 1) - card

                    if (other in pickedCards) {
                        hand.remove(card)
                        pickedCards.remove(other)

                        round++
                        leftCoins--
                        isFind = true
                        break
                    }
                }
            }

            if (isFind) continue

            // 뽑은 카드에서 짝을 만듦
            if (leftCoins >= 2 && pickedCards.size >= 2) {
                for (card in pickedCards.toList()) {
                    val other = (n + 1) - card

                    if (other in pickedCards && card != other) {
                        pickedCards.remove(card)
                        pickedCards.remove(other)

                        round++
                        leftCoins -= 2
                        isFind = true
                        break
                    }
                }
            }

            if (isFind) continue

            // 짝을 만들 수 없는 경우 게임 종료
            break
        }

        return round
    }
}
