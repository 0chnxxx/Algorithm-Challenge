/**
 * A 회사의 물류창고에는 알파벳 대문자로 종류를 구분하는 컨테이너가 세로로 n줄, 가로로 m줄 총 n * m개 놓여 있습니다.
 * 특정 종류 컨테이너의 출고 요청이 들어올 때마다 지게차로 창고에서 접근이 가능한 해당 종류의 컨테이너를 모두 꺼냅니다.
 * 접근이 가능한 컨테이너란 4면 중 적어도 1면이 창고 외부와 연결된 컨테이너를 말합니다.
 *
 * 최근 이 물류 창고에서 창고 외부와 연결되지 않은 컨테이너도 꺼낼 수 있도록 크레인을 도입했습니다.
 * 크레인을 사용하면 요청된 종류의 모든 컨테이너를 꺼냅니다.
 *
 * 처음 물류창고에 놓인 컨테이너의 정보를 담은 1차원 문자열 배열 storage
 * 출고할 컨테이너의 종류와 출고방법을 요청 순서대로 담은 1차원 문자열 배열 requests
 * 이때 모든 요청을 순서대로 완료한 후 남은 컨테이너의 수를 return 하도록 solution 함수를 완성해 주세요.
 *
 * 2 <= storage의 길이 = n <= 50
 * 2 <= storage[i]의 길이 = m <= 50
 * storage[i][j]는 위에서 부터 i + 1번째 행, j + 1번째 열에 놓인 컨테이너의 종류를 의미합니다.
 * 1 <= requests의 길이 <= 100
 * 1 <= requests[i]의 길이 <= 2
 * requests[i]의 길이가 1이면 지게차를 이용한 출고 요청을, 2이면 크레인을 이용한 출고 요청을 의미합니다.
 */

fun main() {
    val storage = arrayOf(
        "HAH", "HBH", "HHH", "HAH", "HBH"
    )
    val requests = arrayOf(
        "C", "B", "B", "B", "B", "H"
    )

    val solution = Solution().solution(storage, requests)

    println(solution)
}

class Solution {
    val directions = arrayOf(-1 to 0, 1 to 0, 0 to -1, 0 to 1)

    fun solution(storage: Array<String>, requests: Array<String>): Int {
        // 0 : 외부 , 1 : 빈 공간
        val expandedStorage = Array(storage.size + 2) { Array(storage[0].length + 2) { "0" } }

        // 컨테이너 초기화
        for (i in 1..storage.size) {
            for (j in 1..storage[0].length) {
                expandedStorage[i][j] = storage[i - 1][j - 1].toString()
            }
        }

        // 요청 처리
        for (request in requests) {
            when (request.length) {
                1 -> fork(expandedStorage, request)
                2 -> crane(expandedStorage, request[0].toString())
            }
        }

        // 남아 있는 컨테이너 갯수 반환
        return expandedStorage.sumOf { row -> row.count { column -> column !in arrayOf("0", "1") } }
    }

    // 지게차 출고
    private fun fork(storage: Array<Array<String>>, target: String) {
        val mark = mutableListOf<Pair<Int, Int>>()

        // 컨테이너 전체 탐색
        for (i in 1 until storage.size - 1) {
            for (j in 1 until storage[i].size - 1) {
                // 타겟 컨테이너라면
                if (storage[i][j] == target) {
                    // 상하좌우 중 외부와 연결된 경우에만 마킹
                    for ((dx, dy) in directions) {
                        val newX = i + dx
                        val newY = j + dy

                        if (storage[newX][newY] == "0") {
                            mark.add(i to j)
                            break
                        }
                    }
                }
            }
        }

        // 마킹된 컨테이너들을
        for ((x, y) in mark) {
            storage[x][y] = "0" // 외부로 변경 (제거 처리)
            propagation(storage, x, y) // 외부 연결 전파
        }
    }

    // 크레인 출고
    private fun crane(storage: Array<Array<String>>, target: String) {
        // 컨테이너 전체 탐색
        for (i in 1 until storage.size - 1) {
            for (j in 1 until storage[i].size - 1) {
                // 타겟 컨테이너라면
                if (storage[i][j] == target) {
                    storage[i][j] = "1" // 빈 공간 처리 (아직 외부가 전파되지 않음)
                    propagation(storage, i, j) // 외부 연결 전파
                }
            }
        }
    }

    fun propagation(storage: Array<Array<String>>, x: Int, y: Int) {
        var isOutside = false

        // 크레인 출고 시 외부 처리를 위한 탐색
        for ((dx, dy) in directions) {
            val newX = x + dx
            val newY = y + dy

            // 외부와 인접하다면
            if (storage[newX][newY] == "0") {
                storage[x][y] = "0" // 외부 처리 (제거 처리)
                isOutside = true
                break
            }
        }

        // 해당 위치가 외부라면
        if (isOutside) {
            for ((dx, dy) in directions) {
                val newX = x + dx
                val newY = y + dy

                // 빈 공간을 찾아서 전파
                if (storage[newX][newY] == "1") {
                    storage[newX][newY] = "0"
                    propagation(storage, newX, newY)
                }
            }
        }
    }
}
