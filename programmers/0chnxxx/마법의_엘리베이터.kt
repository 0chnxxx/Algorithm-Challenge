import java.util.*

/**
 * 마법의 엘리베이터에는 -1, +1, -10, +10, -100, +100 등과 같이 절댓값이 10^c (c >= 0인 정수) 형태인 정수들이 적힌 버튼이 있다.
 * 마법의 엘리베이터의 버튼을 누르면 현재 층 수에 버튼에 적혀 있는 값을 더한 층으로 이동하게 된다.
 * 단, 엘리베이터가 위치해 있는 층과 버튼의 값을 더한 결과가 0보다 작으면 엘리베이터는 움직이지 않는다.
 * 0층이 가장 아래층이며 엘리베이터는 현재 민수가 있는 층에 있다.
 *
 * 버튼 한 번당 마법의 돌 한 개를 사용하게 된다.
 * 마법의 돌을 아끼기 위해 항상 최소한의 버튼을 눌러서 이동하려고 한다.
 * 0층으로 내려가는데 필요한 마법의 돌의 최소 개수를 구하여라.
 *
 * 1 <= storey <= 100000000
 */

class Solution {
    fun solution(storey: Int): Int {
        var result = Int.MAX_VALUE
        val queue = LinkedList<Pair<Int, Int>>()

        queue.add(storey to 0)

        while (queue.isNotEmpty()) {
            val (number, count) = queue.poll()

            // 10층 미만인 경우
            if (number < 10) {
                result = Math.min(result, number + count) // -1 만으로 0층을 만드는 마법의 돌 최소 개수
                result = Math.min(result, count + (10 - number) + 1) // 10층으로 만든 후 -10 로 0층을 만드는 마법의 돌 최소 개수
                continue
            }

            // 0층인 경우
            if (number == 0) {
                queue.add(number to count) // 큐에 해당 층의 정보를 넣음
                continue
            }

            // 10^c 버튼이기 때문에 10을 나눈 몫과 나머지로 다음 층 계산
            val remain = number % 10

            queue.add(number / 10 to count + remain)
            queue.add(number / 10 + 1 to  count + (10 - remain))
        }

        return result
    }
}

fun main(args: Array<String>) {
    println(Solution().solution(16))
    println(Solution().solution(2554))
}
