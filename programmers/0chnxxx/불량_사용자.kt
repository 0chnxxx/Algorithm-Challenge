/**
 * 개발팀 내에서 이벤트 개발을 담당하고 있는 "무지"는 최근 진행된 카카오 이모티콘 이벤트에 비정상적인 방법으로 당첨을 시도한 응모자들을 발견하였습니다.
 * 이런 응모자들을 따로 모아 불량 사용자라는 이름으로 목록을 만들어서 당첨 처리 시 제외하도록 이벤트 당첨자 담당자인 "프로도"에게 전달하려고 합니다.
 * 이 때 개인정보 보호를 위해 사용자 아이디 중 일부 문자를 '*' 문자로 가려서 전달했습니다.
 * 가리고자 하는 문자 하나에 '*' 문자 하나를 사용하였고 아이디 당 최소 하나 이상의 '*' 문자를 사용하였습니다.
 * "무지"와 "프로도"는 불량 사용자 목록에 매핑된 응모자 아이디를 제재 아이디라고 부르기로 하였습니다.
 *
 * 이벤트 응모자 아이디 목록이 담긴 배열 user_id와 불량 사용자 아이디 목록이 담긴 배열 banned_id가 매개변수로 주어질 때,
 * 당첨에서 제외되어야 할 제재 아이디 목록은 몇가지 경우의 수가 가능한지 return하도록 solution 함수를 완성해주세요.
 *
 * 1 <= user_id 배열의 크기 <= 8
 * 1 <= user_id 배열 각 원소들의 값의 길이 <= 8 (응모한 사용자 아이디들은 서로 중복되지 않습니다.)
 * 1 <= banned_id 배열의 크기 <= user_id 배열의 크기
 * 1 <= banned_id 배열 각 원소들의 값의 길이 <= 8
 */

fun main() {
    val user_id = arrayOf("frodo", "fradi", "crodo", "abc123", "frodoc")
    val banned_id = arrayOf("fr*d*", "*rodo", "******", "******")

    val result = Solution().solution(user_id, banned_id)

    println(result)
}

class Solution {
    fun solution(user_id: Array<String>, banned_id: Array<String>): Int {
        val result = mutableSetOf<Set<String>>()
        val visited = mutableSetOf<String>()

        fun isMatched(pattern: String, input: String): Boolean {
            if (pattern.length != input.length) {
                return false
            }

            for (i in pattern.indices) {
                if (pattern[i] != '*' && pattern[i] != input[i]) {
                    return false
                }
            }

            return true
        }

        fun backtrack(index: Int, current: MutableSet<String>) {
            if (index == banned_id.size) {
                result.add(current.toSet())
                return
            }

            for (user in user_id) {
                if (!visited.contains(user) && isMatched(banned_id[index], user)) {
                    visited.add(user)
                    current.add(user)

                    backtrack(index + 1, current)

                    current.remove(user)
                    visited.remove(user)
                }
            }
        }

        backtrack(0, mutableSetOf())

        return result.size
    }
}
