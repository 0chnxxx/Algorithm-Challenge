/**
 * 당신은 표 편집 프로그램을 작성하고 있습니다.
 * 표의 크기는 50 * 50으로 고정되어 있고 초기에 모든 셀은 비어 있습니다.
 * 각 셀은 문자열 값을 가질 수 있고, 다른 세로가 병합될 수 있습니다.
 *
 * 위에서 r번째, 왼쪽에서 c번째 위치를 (r, c)라고 표현할 때, 당신은 다음 명령어들에 대한 기능을 구현하려고 합니다.
 * 1. UPDATE r c value
 * - (r, c) 위치의 셀을 선택합니다.
 * - 선택한 셀의 값을 value로 바꿉니다.
 * 2. UPDATE value1 value2
 * - value1을 값으로 가지고 있는 모든 셀을 선택합니다.
 * - 선택한 셀의 값을 value2로 바꿉니다.
 * 3. MERGE r1 c1 r2 c2
 * - (r1 c1) 위치의 셀과 (r2, c2) 위치의 셀을 선택하여 병합합니다.
 * - 선택한 두 위치의 셀이 같은 셀일 경우 무시합니다.
 * - 선택한 두 셀은 서로 인접하지 않을 수도 있습니다. 이 경우 (r1, c1) 위치의 셀과 (r2, c2) 위치의 셀만 영향을 받으며, 그 사이에 위치한 셀들은 영향을 받지 않습니다.
 * - 두 셀 중 한 셀이 값을 가지고 있을 경우 병합된 셀을 그 값을 가지게 됩니다.
 * - 두 셀 모두 값을 가지고 있을 경우 병합된 셀은 (r1, c1) 위치의 셀 값을 가지게 됩니다.
 * - 이후 (r1, c1) 과 (r2, c2) 중 어느 위치를 선택하여도 병합된 셀로 접근합니다.
 * 4. UNMERGE r c
 * - (r, c) 위치의 셀을 선택하여 해당 셀의 모든 병합을 해제합니다.
 * - 선택한 셀이 포함하고 있던 모든 셀은 프로그램 실행 초기의 상태로 돌아갑니다.
 * - 병합을 해제하기 전 셀이 값을 가지고 있었을 경우 (r, c) 위치의 셀이 그 값을 가지게 됩니다.
 * 5. PRINT r c
 * - (r, c) 위치의 셀을 선택하여 셀의 값을 출력합니다.
 * - 선택한 셀이 비어있을 경우 EMPTY를 출력합니다.
 *
 * 실행할 명령어들이 담긴 1차원 문자열 배열 commands가 매개변수로 주어집니다.
 * commands의 명령어들을 순서대로 실행하였을 때, PRINT r c 명령어에 대한 실행결과를 순서대로 1차원 문자열 배열에 담아 return 하도록 solution 함수를 완성해주세요.
 *
 * 1 <= commands 의 길이 <= 1000
 * 1 <= r, c <= 50
 * 1 <= value <= 10
 * commands는 1개 이상의 PRINT r c 명령어를 포함하고 있습니다.
 */

fun main() {
    val commands = arrayOf(
        "UPDATE 1 1 menu",
        "UPDATE 1 2 category",
        "UPDATE 2 1 bibimbap",
        "UPDATE 2 2 korean",
        "UPDATE 2 3 rice",
        "UPDATE 3 1 ramyeon",
        "UPDATE 3 2 korean",
        "UPDATE 3 3 noodle",
        "UPDATE 3 4 instant",
        "UPDATE 4 1 pasta",
        "UPDATE 4 2 italian",
        "UPDATE 4 3 noodle",
        "MERGE 1 2 1 3",
        "MERGE 1 3 1 4",
        "UPDATE korean hansik",
        "UPDATE 1 3 group",
        "UNMERGE 1 4",
        "PRINT 1 3",
        "PRINT 1 4"
    )

    val solution = Solution().solution(commands)

    println(solution.joinToString(", "))
}

class Solution {
    val table = Array(51) { r -> Array(51) { c -> Pair(r, c) } }
    val value = Array(51) { Array(51) { "EMPTY" } }

    fun solution(commands: Array<String>): Array<String> {
        val result = mutableListOf<String>()

        for (command in commands) {
            val tokens = command.split(" ")

            when (tokens[0]) {
                "UPDATE" -> {
                    if (tokens.size == 4) {
                        update(tokens[1].toInt(), tokens[2].toInt(), tokens[3])
                    } else {
                        updateAll(tokens[1], tokens[2])
                    }
                }
                "MERGE" -> merge(tokens[1].toInt(), tokens[2].toInt(), tokens[3].toInt(), tokens[4].toInt())
                "UNMERGE" -> unmerge(tokens[1].toInt(), tokens[2].toInt())
                "PRINT" -> result.add(print(tokens[1].toInt(), tokens[2].toInt()))
            }
        }

        return result.toTypedArray()
    }

    // 루트 셀 찾기
    private fun find(r: Int, c: Int): Pair<Int, Int> {
        if (table[r][c] == Pair(r, c)) return Pair(r, c)

        val root = find(table[r][c].first, table[r][c].second)

        table[r][c] = root // 경로 압축

        return root
    }

    // 특정 값 변경
    private fun update(r: Int, c: Int, newValue: String) {
        val (x, y) = find(r, c)

        value[x][y] = newValue
    }

    // 특정 값 전체 변경
    private fun updateAll(oldValue: String, newValue: String) {
        for (r in 1 until 51) {
            for (c in 1 until 51) {
                if (value[r][c] == oldValue) value[r][c] = newValue
            }
        }
    }

    // 셀 병합
    private fun merge(r1: Int, c1: Int, r2: Int, c2: Int) {
        val root1 = find(r1, c1)
        val root2 = find(r2, c2)

        if (root1 == root2) return // 이미 같은 그룹

        val (x1, y1) = root1
        val (x2, y2) = root2

        // 병합 후 유지할 값 결정
        val mergedValue = if (value[x1][y1] == "EMPTY") value[x2][y2] else value[x1][y1]

        // root2를 root1에 병합
        table[x2][y2] = root1
        value[x1][y1] = mergedValue
    }

    // 셀 병합 해제
    private fun unmerge(r: Int, c: Int) {
        val root = find(r, c)
        val oldValue = value[root.first][root.second]

        // root와 같은 그룹에 있는 셀들 찾기
        val toReset = mutableListOf<Pair<Int, Int>>()

        for (i in 1 until 51) {
            for (j in 1 until 51) {
                if (find(i, j) == root) {
                    toReset.add(Pair(i, j))
                }
            }
        }

        // 같은 그룹에 있는 셀들 전부 병합 해제
        for ((i, j) in toReset) {
            table[i][j] = Pair(i, j)
            value[i][j] = "EMPTY"
        }

        // 원래 위치에 값 유지
        value[r][c] = oldValue
    }

    // 값 출력
    private fun print(r: Int, c: Int): String {
        val (x, y) = find(r, c)
        return value[x][y]
    }
}
