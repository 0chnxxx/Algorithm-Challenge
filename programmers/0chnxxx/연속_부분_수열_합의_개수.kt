/**
 * 어떤 자연수로 이루어진 원형 수열의 연속하는 부분 수열의 합으로 만들 수 있는 수가 모두 몇가지일까?
 * 원형 수열이란 일반적인 수열에서 처음과 끝이 연결된 형태의 수열이다.
 *
 * 원형 수열의 모든 원소 elements가 순서대로 주어질 때 원형 수열의 연속 부분 수열 합으로 만들 수 있는 수의 갯수를 구하여라.
 *
 * 3 <= elements의 길이 <= 1000
 * 1 <= elements의 원소 <= 1000
 */

class Solution {
    fun solution(elements: IntArray): Int {
        val set = mutableSetOf<Int>()

        for (i in 0 until elements.size) {
            val circle = elements.toMutableList()
            circle.addAll(elements.slice(0..i))

            for (j in 0 until circle.size) {
                val start = j
                val end = (j + i) % circle.size

                val slice = circle.slice(start..end)
                val sum = slice.sum()

                if (sum != 0) {
                    set.add(sum)
                }
            }
        }

        return set.size
    }
}

fun main(args: Array<String>) {
    val elements = intArrayOf(7, 9, 1, 1, 4)
    val result = Solution().solution(elements)

    println(result)
}
