/**
 * 정수 n이 매개변수로 주어진다.
 * 밑변의 길이와 높이가 n인 삼각형에서 맨 위 꼭짓점부터 반시계 방향으로 달팽이 채우기를 진행한다.
 * 그 후 첫 행부터 마지막 행까지 모두 순서대로 합친 새로운 배열을 return하라.
 *
 * 1 <= n <= 1000
 */

class Solution {
    fun solution(n: Int): IntArray {
        val array = Array(n) { IntArray(n) }

        var number = 1
        var x = -1
        var y = 0

        for (i in 0 until n) {
            for (j in i until n) {
                when (i % 3) {
                    0 -> { // 아래로 이동
                        x += 1
                    }
                    1 -> { // 오른쪽으로 이동
                        y += 1
                    }
                    2 -> { // 위로 이동
                        x -= 1
                        y -= 1
                    }
                }
                array[x][y] = number++
            }
        }

        return array.flatMap { it.asSequence() }.filter { it > 0 }.toIntArray()
    }
}

fun main(args: Array<String>) {
    println(Solution().solution(4).joinToString(", "))
    println(Solution().solution(5).joinToString(", "))
    println(Solution().solution(6).joinToString(", "))
}
