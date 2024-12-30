/**
 * 주어진 항공권을 모두 이용하여 여행경로를 짜려고 합니다.
 * 항상 "ICN" 공항에서 출발합니다.
 * 항공권 정보가 담긴 2차원 배열 tickets가 매개변수로 주어질 때, 방문하는 공항 경로를 배열에 담아 return 하도록 solution 함수를 작성해주세요.
 * 만약 가능한 경로가 2개 이상일 경우 알파벳 순서가 앞서는 경로를 return 합니다.
 *
 * 모든 공항은 알파벳 대문자 3글자로 이루어집니다.
 * tickets의 각 행 [a, b] 는 a 공항에서 b 공항으로 가는 항공권이 있다는 의미입니다.
 * 주어진 항공권은 모두 사용해야 합니다.
 * 모든 도시를 방문할 수 없는 경우는 주어지지 않습니다.
 *
 * 3 <= 공항 수 <= 10000
 */

fun main() {
    val tickets = arrayOf(
        arrayOf("ICN", "SFO"),
        arrayOf("ICN", "ATL"),
        arrayOf("SFO", "ATL"),
        arrayOf("ATL", "ICN"),
        arrayOf("ATL", "SFO")
    )

    val solution = Solution().solution(tickets)

    println(solution.joinToString(", "))
}

class Solution {
    fun solution(tickets: Array<Array<String>>): Array<String> {
        val result = mutableListOf<String>()
        val used = BooleanArray(tickets.size)

        tickets.sortWith(compareBy({ it[0] }, { it[1] }))

        fun dfs(current: String, path: MutableList<String>): Boolean {
            path.add(current)

            if (path.size == tickets.size + 1) {
                result.addAll(path)
                return true
            }

            for (i in tickets.indices) {
                if (!used[i] && tickets[i][0] == current) {
                    used[i] = true

                    if (dfs(tickets[i][1], path)) {
                        return true
                    }

                    used[i] = false
                }
            }

            path.removeAt(path.size - 1)

            return false
        }

        dfs("ICN", mutableListOf())

        return result.toTypedArray()
    }
}
