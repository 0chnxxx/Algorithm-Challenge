/**
 * 당신은 비밀 조직의 보안 시스템을 뚫고 중요한 정보를 해독해야 합니다.
 * 시스템은 1부터 n까지의 서로 다른 정수 5개가 오름차순으로 정렬된 비밀 코드를 가지고 있으며, 당신은 이 비밀 코드를 맞혀야 합니다.
 *
 * 당신은 비밀 코드를 알아내기 위해 암호 분석 도구를 사용하며, m번의 시도를 할 수 있습니다.
 * 각 시도마다 서로 다른 5개의 정수를 입력하면, 시스템은 그 중 몇 개가 비밀 코드에 포함되어 있는지 알려줍니다.
 *
 * 당신은 m번의 시도 후, 비밀 코드로 가능한 정수 조합의 개수를 알고 싶습니다.
 *
 * 정수 n
 * 입력한 정수를 담은 2차원 정수 배열 q
 * 시스템 응답을 담은 1차원 정수 배열 ans
 *
 * 이때, 비밀 코드로 가능한 정수 조합 개수를 return 하도록 solution 함수를 완성해 주세요.
 *
 * 10 <= n <= 30
 * 1 <= q의 길이 = m <= 10
 *  - q[i]의 길이 = 5
 *  - q[i]는 i + 1번째 시도에서 입력한 5개의 서로 다른 정수를 담고 있으며, 오름차순으로 정렬되어 있습니다.
 *  - 1 <= q[i][j] <= n
 * ans의 길이 = m
 *  - ans[i]는 i + 1번째 시도에서 입력한 5개의 정수 중 비밀 코드에 포함된 정수의 개수를 나타냅니다.
 *  - 0 <= ans[i] <= 5
 */

fun main() {
    val n = 10
    val q = arrayOf(
        intArrayOf(1, 2, 3, 4, 5),
        intArrayOf(6, 7, 8, 9, 10),
        intArrayOf(3, 7, 8, 9, 10),
        intArrayOf(2, 5, 7, 9, 10),
        intArrayOf(3, 4, 5, 6, 7)
    )
    val ans = intArrayOf(2, 3, 4, 3, 3)

    val solution = Solution().solution(n, q, ans)

    println(solution)
}

class Solution {
    private var result = 0

    fun solution(n: Int, q: Array<IntArray>, ans: IntArray): Int {
        dfs(1, n, 0, mutableListOf(), q, ans)

        return result
    }

    private fun dfs(start: Int, end: Int, count: Int, current: MutableList<Int>, q: Array<IntArray>, ans: IntArray) {
        // 5자리의 조합이 완성되면 비밀 코드 확인
        if (count == 5) {
            check(current, q, ans)
            return
        }

        // 재귀 + 백트래킹을 통한 모든 조합 생성
        for (i in start..end) {
            current.add(i)
            dfs(i + 1, end, count + 1, current, q, ans)
            current.removeAt(current.size - 1)
        }
    }

    private fun check(current: MutableList<Int>, q: Array<IntArray>, ans: IntArray) {
        val set = current.toSet()

        // q와 ans를 순회
        // 현재 조합이 query에 포함된 갯수와 query에 비밀 코드가 포함된 갯수(ans)와 동일하다면
        // 비밀 코드 후보로 간주
        for (i in q.indices) {
            val query = q[i]
            val answer = ans[i]
            val include = query.count { it in set }

            if (include != answer) {
                return
            }
        }

        result++
    }
}
