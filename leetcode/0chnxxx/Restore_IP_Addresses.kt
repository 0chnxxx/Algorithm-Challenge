/**
 * A valid IP address consists of exactly four integers separated by single dots.
 * Each integer is between 0 and 255 (inclusive) and cannot have leading zeros.
 *
 * For example, "0.1.2.201" and "192.168.1.1" are valid IP addresses, but "0.011.255.245", "192.168.1.312" and "192.168@1.1" are invalid IP addresses.
 * Given a string s containing only digits, return all possible valid IP addresses that can be formed by inserting dots into s.
 * You are not allowed to reorder or remove any digits in s.
 * You may return the valid IP addresses in any order.
 *
 * Constraints:
 * 1 <= s.length <= 20
 * s consists of digits only.
 */

fun main() {
    val s = "25525511135"

    val solution = Solution().restoreIpAddresses(s)

    println(solution)
}

class Solution {
    // 시간 복잡도 : O(81)
    // 공간 복잡도 : O(81)
    fun restoreIpAddresses(s: String): List<String> {
        val result = mutableListOf<String>()

        fun dfs(index: Int, current: MutableList<String>) {
            // IP를 완성시킬 경우
            if (current.size == 4) {
                // 모든 숫자를 다 사용한 경우
                if (index == s.length) {
                    // . 로 join 후 결과에 추가
                    result.add(current.joinToString("."))
                }

                return
            }

            // 1자리부터 3자리까지 탐색
            for (i in 1..3) {
                // 자릿수를 초과한 경우 종료
                if (index + i > s.length) break

                // IP의 파트 추출
                val part = s.substring(index, index + i)

                // 0으로 시작하는 유효하지 않은 파트는 스킵
                if (part.startsWith("0") && part.length > 1) continue

                // 0~255까지의 파트만 허용
                if (part.toInt() > 255) continue

                // backtrack
                current.add(part)
                dfs(index + i, current)
                current.removeAt(current.size - 1)
            }
        }

        dfs(0, mutableListOf())

        return result
    }
}