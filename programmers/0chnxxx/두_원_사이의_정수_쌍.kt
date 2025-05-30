import kotlin.math.pow

/**
 * x축과 y축으로 이루어진 2차원 직교 좌표계에 중심이 원점인 서로 다른 크기의 원이 두 개 주어집니다.
 * 반지름을 나타내는 두 정수 r1, r2가 매개변수로 주어질 때, 두 원 사이의 공간에 x좌표와 y좌표가 모두 정수인 점의 개수를 return 하도록 solution 함수를 완성해주세요.
 * 각 원 위의 점도 포함하여 셉니다.
 *
 * 1 <= r1 < r2 <= 1000000
 */

fun main() {
    val r1 = 2
    val r2 = 3

    val solution = Solution().solution(r1, r2)

    println(solution)
}

class Solution {
    fun solution(r1: Int, r2: Int): Long {
        var point = 0L

        for (x in 1..r2) {
            var y2 = Math.sqrt((r2.toDouble().pow(2) - x.toDouble().pow(2)))
            var y1 = Math.sqrt((r1.toDouble().pow(2) - x.toDouble().pow(2)))

            if (y1.isNaN()) {
                y1 = 0.0
            }

            point += (Math.floor(y2) - Math.ceil(y1) + 1).toLong()
        }

        return point * 4
    }
}
