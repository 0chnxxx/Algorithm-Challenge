/**
 * 비내림차순으로 정렬된 수열이 주어질 때 다음 조건을 만족하는 부분 수열을 찾으려고 한다.
 *
 * 1. 기존 수열에서 임의의 두 인덱스의 원소와 그 사이의 원소를 모두 포함하는 부분 수열이어야 한다.
 * 2. 부분 수열의 합은 k이다.
 * 3. 합이 k인 부분 수열이 여러 개인 경우 길이가 짧은 수열을 찾는다.
 * 4. 길이가 짧은 수열이 여러 개인 경우 앞쪽(시작 인덱스가 작은)에 나오는 수열을 찾는다.
 *
 * 수열을 나타내는 정수 배열 sequence와 부분 수열의 합을 나타내는 정수 k가 매개변수로 주어질 때
 * 위 조건을 만족하는 부분 수열의 시작 인덱스와 마지막 인덱스를 배열에 담아 return하라.
 *
 * 수열의 인덱스는 0부터 시작한다.
 * 5 <= sequence의 길이 <= 1000000
 * 1 <= sequence의 원소 <= 1000
 * 5 <= k <= 1000000000
 */

class Solution {
    fun solution(sequence: IntArray, k: Int): IntArray {
        var start = 0
        var end = 0
        var sum = 0
        var range = intArrayOf(-1, -1)
        var minLength = Int.MAX_VALUE

        while (end < sequence.size) {
            sum += sequence[end]

            while (sum > k && start < end) {
                sum -= sequence[start]
                start++
            }

            if (sum == k) {
                val currentLength = end - start

                if (currentLength < minLength || (currentLength == minLength && start < range[0])) {
                    range = intArrayOf(start, end)
                    minLength = currentLength
                }
            }

            end++
        }

        return range
    }
}

fun main(args: Array<String>) {
    println(Solution().solution(intArrayOf(1, 2, 3, 4, 5), 7).joinToString(", "))
    println(Solution().solution(intArrayOf(1, 1, 1, 2, 3, 4, 5), 5).joinToString(", "))
    println(Solution().solution(intArrayOf(2, 2, 2, 2, 2), 6).joinToString(", "))
}
