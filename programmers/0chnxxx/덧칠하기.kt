/**
 * 페인트가 칠해진 길이가 n미터인 벽이 있다.
 * 페인트가 벗겨진 벽이 보기 흉해져 벽에 페인트를 덧칠하기로 했다.
 * 벽을 1미터 길이의 구역 n개로 나누고, 각 구역에 순서대로 1번부터 n번까지 번호를 붙였다.
 * 그리고 페인트를 다시 칠해야 할 구역들을 정했다.
 *
 * 벽에 페인트를 칠하는 롤러의 길이는 m미터이고, 롤러로 벽에 페인트를 한 번 칠하는 규칙은 다음과 같다.
 * 1. 롤러가 벽에서 벗어나면 안 된다.
 * 2. 구역의 일부분만 포함되도록 칠하면 안 된다.
 *
 * 현재 페인트를 칠하는 구역들을 완전히 칠한 후 벽에서 롤러를 떼며 한 번 칠했다고 정의한다.
 * 한 구역에 페인트를 여러 번 칠해도 되고 다시 칠해야 할 구역이 아닌 곳에 페인트를 칠해도 되지만 다시 칠하기로 정한 구역은 적어도 한 번 페인트칠을 해야 한다.
 * 롤러로 페인트칠을 하는 횟수를 최소화하려고 한다.
 *
 * 정수 n, m과 다시 페인트를 칠하기로 정한 구역들의 번호가 담긴 정수 배열 section이 매개변수로 주어진다.
 * 롤러로 페인트칠을 해야 하는 최소 횟수를 return하라.
 *
 * 1 <= m <= n <= 100000
 * 1 <= section의 길이 <= n
 * 1 <= section의 원소 <= n
 *
 * section의 원소는 중복되지 않는다.
 * section의 원소는 오름차순으로 정렬되어 있다.
 */

class Solution {
    fun solution(n: Int, m: Int, section: IntArray): Int {
        val array = BooleanArray(n) { true }
        var result = 0

        for (i in section) {
            array[i - 1] = false
        }

        for (i in 0 until array.size) {
            if (array[i] == false) {
                val start = i
                val end = Math.min(i + m, n)

                array.fill(true, start, end)
                result++
            }
        }

        return result
    }
}

fun main(args: Array<String>) {
    println(Solution().solution(8, 4, intArrayOf(2, 3, 6)))
    println(Solution().solution(5, 4, intArrayOf(1, 3)))
    println(Solution().solution(4, 1, intArrayOf(1, 2, 3, 4)))
}
