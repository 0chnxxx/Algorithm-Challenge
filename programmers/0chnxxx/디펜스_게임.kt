import java.util.PriorityQueue

/**
 * 준호는 요즘 디펜스 게임에 푹 빠져 있습니다.
 * 디펜스 게임은 준호가 보유한 병사 n명으로 연속되는 적의 공격을 순서대로 막는 게임입니다.
 * 디펜스 게임은 다음과 같은 규칙으로 진행됩니다.
 *
 * 1. 준호는 처음에 병사 n명을 가지고 있습니다.
 * 2. 매 라운드마다 enemy[i] 마리의 적이 등장합니다.
 * 3. 남은 병사 중 enemy[i] 명 만큼 소모하여 enemy[i] 마리의 적을 막을 수 있습니다.
 * 4. 남은 병사의 수보다 현재 라운드의 적의 수가 더 많으면 게임이 종료됩니다.
 * 5. 게임에는 무적권이라는 스킬이 있으며, 무적권을 사용하면 병사의 소모없이 한 라운드의 공격을 막을 수 있습니다.
 * 6. 무적권은 최대 k번 사용할 수 있습니다.
 *
 * 준호는 무적권을 적절한 시기에 사용하여 최대한 많은 라운드를 진행하고 싶습니다.
 *
 * 준호가 처음 가지고 있는 병사의 수 n, 사용 가능한 무적권의 횟수 k, 매 라운드마다 공격해오는 적의 수가 순서대로 담긴 정수 배열 enemy가 매개변수로 주어집니다.
 * 준호가 몇 라운드까지 막을 수 있는지 return 하도록 solution 함수를 완성해주세요.
 * 모든 라운드를 막을 수 있는 경우에는 enemy[i] 의 길이를 return 해주세요.
 *
 * 1 <= n <= 1000000000
 * 1 <= k <= 500000
 * 1 <= enemy의 길이 <= 1000000
 * 1 <= enemy[i] <= 1000000
 */

fun main() {
    val n = 7
    val k = 3
    val enemy = intArrayOf(4, 2, 4, 5, 3, 3, 1)

    val solution = Solution().solution(n, k, enemy)

    println(solution)
}

class Solution {
    fun solution(n: Int, k: Int, enemy: IntArray): Int {
        val queue = PriorityQueue<Int>()
        var remain = n

        for (i in enemy.indices) {
            queue.add(enemy[i])

            if (queue.size > k) {
                remain -= queue.poll()
            }

            if (remain < 0) {
                return i
            }
        }

        return enemy.size
    }
}

