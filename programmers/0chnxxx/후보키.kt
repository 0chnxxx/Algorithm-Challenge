/**
 * 프렌즈대학교 컴퓨터공학과 조교인 제이지는 네오 학과장님의 지시로 학생들의 인적사항을 정리하는 업무를 담당하게 되었다.
 * 그의 학부 시절 프로그래밍 경험을 되살려, 모든 인적사항을 데이터베이스에 넣기로 하였고 이를 위해 정리를 하던 중에 후보키에 대한 고민이 필요하게 되었다.
 * 후보키에 대한 내용이 잘 기억나지 않던 제이지는 정확한 내용을 파악하기 위해 데이터베이스 관련 서적을 확인하여 아래와 같은 내용을 확인하였다.
 *
 * 관계 데이터베이스에서 릴레이션의 튜플을 유일하게 식별할 수 있는 속성 또는 속성의 집합 중, 다음 두 성질을 만족하는 것을 후보 키라고 한다.
 *
 * 유일성 : 릴레이션에 있는 모든 튜플에 대해 유일하게 식별되어야 하낟.
 * 최소성 : 유일성을 가진 키를 구성하는 속성 중 하나라도 제외하는 경우 유일성이 깨지는 것을 의미한다.
 * 즉, 릴레이션의 모든 튜플을 유일하게 식별하는데 꼭 필요한 속성들로만 구성되어야 한다.
 *
 * 제이지를 위해 아래와 같은 학생들의 인적사항이 주어졌을 때 후보 키의 최대 개수를 구하라.
 *
 * 학생의 인적사항 릴레이션에서 모든 학생은 각자 유일한 학번을 가지고 있다.
 * 따라서 학번은 릴레이션의 후보 키가 될 수 있다.
 * 그 다음 이름에 대해서는 같은 이름을 사용하는 학생이 있기 때문에 이름은 후보 키가 될 수 없다.
 * 그러나 만약 [이름, 전공]을 함께 사용한다면 릴레이션의 모든 튜플을 유일하게 식별 가능하므로 후보 키가 될 수 있게 된다.
 * 물론 [이름, 전공, 학년]을 함께 사용해도 릴레이션의 모든 튜플을 유일하게 식별할 수 있지만 최소성을 만족하지 못하기 때문에 후보 키가 될 수 없다.
 * 따라서, 위의 학생 인적사항의 후보키는 학번, [이름, 전공] 두 개가 된다.
 *
 * 릴레이션을 나타내는 문자열 배열 relation이 매개변수로 주어질 때, 이 릴레이션에서 후보 키의 개수를 return 하도록 solution 함수를 완성하라.
 *
 * relation은 2차원 문자열 배열이다.
 * relation의 컬럼의 길이는 1 이상 8 이하이며 각각의 컬럼은 릴레이션의 속성을 나타낸다.
 * relation의 로우의 길이는 1 이상 20 이하이며 각각의 로우는 릴레이션의 튜플을 나타낸다.
 * relation의 모든 문자열 길이는 1 이상 8 이하이며 알파벳 소문자와 숫자로만 이루어져 있다.
 * 중복되는 튜플은 없다.
 */

fun main() {
    val relation = arrayOf(
        arrayOf("100", "ryan", "music", "2"),
        arrayOf("200", "apeach", "math", "2"),
        arrayOf("300", "tube", "computer", "3"),
        arrayOf("400", "con", "computer", "4"),
        arrayOf("500", "muzi", "music", "3"),
        arrayOf("600", "apeach", "music", "2")
    )

    val solution = Solution().solution(relation)

    println(solution)
}

class Solution {
    fun solution(relation: Array<Array<String>>): Int {
        val rows = relation.size
        val columns = relation[0].size
        val combinations = mutableListOf<Set<Int>>()

        fun generate(start: Int, size: Int, set: Set<Int>) {
            if (set.size == size) {
                combinations.add(set)
                return
            }

            for (i in start until columns) {
                generate(i + 1, size, set + i)
            }
        }

        for (size in 1..columns) {
            generate(0, size, emptySet())
        }

        fun isUnique(index: Set<Int>): Boolean {
            val seen = mutableSetOf<String>()

            for (row in 0 until rows) {
                val key = index.map { relation[row][it] }.joinToString(",")

                if (!seen.add(key)) {
                    return false
                }
            }

            return true
        }

        val candidateKeys = mutableListOf<Set<Int>>()

        for (combination in combinations) {
            if (isUnique(combination)) {
                if (candidateKeys.none { combination.containsAll(it) }) {
                    candidateKeys.add(combination)
                }
            }
        }

        return candidateKeys.size
    }
}
