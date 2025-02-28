import kotlin.math.ceil

/**
 * 1 ~ n의 번호가 있는 택배 상자가 창고에 있습니다.
 * 당신은 택배 상자들을 다음과 같이 정리했습니다.
 *
 * 왼쪽에서 오른쪽으로 가면서 1번 상자부터 번호 순서대로 택배 상자를 한 개씩 놓습니다.
 * 가로로 택배 상자를 w개 놓았다면 이번에는 오른쪽에서 왼쪽으로 가면서 그 위층에 택배 상자를 한 개씩 놓습니다.
 * 그 층에 상자를 w개 놓아 가장 왼쪽으로 돌아왔다면 또다시 왼쪽에서 오른쪽으로 가면서 그 위층에 상자를 놓습니다.
 * 이러한 방식으로 n개의 택배 상자를 모두 놓을 때까지 한 층에 w개씩 상자를 쌓습니다.
 *
 * 다음 날 손님은 자신의 택배를 찾으러 창고에 왔습니다.
 * 당신은 손님이 자신의 택배 상자 번호를 말하면 해당 택배 상자를 꺼내줍니다.
 * 택배 상자 A를 꺼내려면 먼저 A 위에 있는 다른 모든 상자를 꺼내야 A를 꺼낼 수 있습니다.
 *
 * 당신은 꺼내려는 상자 번호가 주어졌을 때 꺼내려는 상자를 포함해 총 몇 개의 택배 상자를 꺼내야 하는지 알고 싶습니다.
 *
 * 창고에 있는 택배 상자의 개수를 나타내는 정수 n
 * 가로로 놓는 상자의 개수를 나타내는 정수 w
 * 꺼내려는 택배 상자의 번호를 나타내는 정수 num
 *
 * 꺼내야 하는 상자의 총 개수를 return 하도록 solution 함수를 완성해 주세요.
 *
 * 2 <= n <= 100
 * 1 <= w <= 10
 * 1 <= num <= n
 */

fun main() {
    val n = 13
    val w = 3
    val num = 6

    val solution = Solution().solution(n, w, num)

    println(solution)
}

class Solution {
    fun solution(n: Int, w: Int, num: Int): Int {
        val h = ceil(n / w.toDouble()).toInt()
        val storage = Array(h) { IntArray(w) { 0 } }

        var number = 1

        // 박스 쌓기
        for (i in 0 until h) {
            val row = IntArray(w) { 0 }

            for (j in 0 until w) {
                if (number <= n) {
                    row[j] = number++
                }
            }

            // 지그재그 쌓기
            if (i % 2 == 1) {
                row.reverse()
            }

            storage[i] = row
        }

        // 타겟 박스 위치 찾기
        var targetBox = 0 to 0

        for (i in 0 until h) {
            var isFind = false

            for (j in 0 until w) {
                if (storage[i][j] == num) {
                    targetBox = i to j
                    isFind = true
                    break
                }
            }

            if (isFind) break
        }

        // 꺼내야 하는 상자 계수 구하기
        var count = 0

        for (i in targetBox.first until h) {
            if (storage[i][targetBox.second] != 0) {
                count++
            }
        }

        return count
    }
}
