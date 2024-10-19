import java.util.*

/**
 * 자연수 x를 y로 변환하려고 한다.
 *
 * 사용할 수 있는 연산은 다음과 같다.
 * 1. x에 n을 더한다.
 * 2. x에 2를 곱한다.
 * 3. x에 3을 곱한다.
 *
 * 자연수 x, y, n이 매개변수로 주어질 때 x를 y로 변환하기 위해 필요한 최소 연산 횟수를 return하라
 * 이때, x를 y로 만들 수 없다면 -1을 return하라.
 *
 * 1 <= x <= y <= 1000000
 * 1 <= n < y
 */

class Solution {
    fun solution(x: Int, y: Int, n: Int): Int {
        if (x == y) {
            return 0
        }

        val visited = IntArray(y + 1) { -1 }
        val queue: Queue<Int> = LinkedList()

        queue.add(x)
        visited[x] = 0

        while (queue.isNotEmpty()) {
            val current = queue.poll()
            val nextValues = listOf(current + n, current * 2, current * 3)

            for (nextValue in nextValues) {
                if (nextValue == y) {
                    return visited[current] + 1
                }

                if (nextValue <= y && visited[nextValue] == -1) {
                    visited[nextValue] = visited[current] + 1
                    queue.add(nextValue)
                }
            }
        }

        return -1
    }
}

fun main(args: Array<String>) {
    val x = 10
    val y = 40
    val n = 5

    val solution = Solution().solution(x, y, n)

    println(solution)
}
