/**
 * 중앙에는 노란색으로 칠해져 있고 테두리 1줄은 갈색으로 칠해져 있는 격자 모양의 카펫이 있다.
 * 갈색 격자의 수 brown, 노란색 격자의 수 yellow가 주어질 때 카펫의 가로, 세로 크기를 순서대로 배열에 담아 return하라.
 *
 * 8 <= brown <= 5000
 * 1 <= yellow <= 2000000
 *
 * 카펫의 가로 길이는 세로 길이와 같거나 세로 길이보다 길다.
 */

class Solution {
    fun solution(brown: Int, yellow: Int): IntArray {
        var area = brown + yellow
        var width = yellow
        var height = 3

        while (true) {
            if (area % height != 0) {
                height++
                continue
            }

            width = area / height

            if ((width - 2) * (height - 2) == yellow) {
                return intArrayOf(width, height)
            }

            height++
        }
    }
}

fun main(args: Array<String>) {
    val brown = 24
    val yellow = 24

    val result = Solution().solution(brown, yellow)

    println("${result[0]}, ${result[1]}")
}
