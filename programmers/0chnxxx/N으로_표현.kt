/**
 * 아래와 같이 5와 사칙연산만으로 12를 표현할 수 있습니다.
 *
 * 12 = 5 + 5 (5 / 5) + (5 / 5)
 * 12 = 55 / 5 + 5 / 5
 * 12 = (55 + 5) / 5
 *
 * 5를 사용한 횟수는 각각 6, 5, 4 입니다.
 * 그리고 이중 가장 작은 경우는 4입니다.
 * 이처럼 숫자 N과 number가 주어질 때, N과 사칙연산만 사용해서 표현할 수 있는 방법 중 N 사용횟수의 최솟값을 return 하도록 solution 함수를 작성하세요.
 *
 * 1 <= N <= 9
 * 1 <= number <= 32000
 * 수식에는 괄호와 사칙연산만 가능하며 나누기 연산에서 나머지는 무시합니다.
 * 최솟값이 8보다 크면 -1을 return 합니다.
 */

fun main() {
    val N = 5
    val number = 12

    val solution = Solution().solution(N, number)

    println(solution)
}

class Solution {
    fun solution(N: Int, number: Int): Int {
        if (N == number) {
            return 1
        }

        // N을 i번 사용해서 만들 수 있는 숫자들
        val dp = Array(9) { mutableSetOf<Int>() }

        // N을 1번부터 8번까지 사용
        // N 사용횟수의 최솟값이 8보다 크면 -1을 반환
        for (i in 1..8) {
            // N 사용 횟수에 따라 N, NN, NN, ... 을 추가
            dp[i].add(N.toString().repeat(i).toInt())

            // j와 i - j를 통해 이전에 계산했던 조합들을 이용
            // j와 i - j의 합은 항상 i이기 때문에 모든 조합을 고려할 수 있음
            for (j in 1 until i) {
                for (a in dp[j]) {
                    for (b in dp[i - j]) {
                        dp[i].add(a + b)
                        dp[i].add(a - b)
                        dp[i].add(a * b)
                        if (b != 0) dp[i].add(a / b)
                    }
                }
            }

            // 원하는 숫자가 만들어졌다면 해당 사용 횟수를 반환
            if (number in dp[i]) return i
        }

        return -1
    }
}
