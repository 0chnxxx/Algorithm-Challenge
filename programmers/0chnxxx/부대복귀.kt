import java.util.*

/**
 * 강철부대의 각 부대원이 여러 지역에 뿔뿔이 흩어져 특수 임무를 수행 중입니다.
 * 지도에서 강철부대가 위치한 지역을 포함한 각 지역은 유일한 번호로 구분되며, 두 지역 간의 길을 통과하는데 걸리는 시간은 모두 1로 동일합니다.
 * 임무를 수행한 각 부대원은 지도 정보를 이용하여 최단시간에 부대로 복귀하고자 합니다.
 * 다만 적군의 방해로 인해, 임무의 시작 때와 다르게 되돌아오는 경로가 없어져 복귀가 불가능한 부대원도 있을 수 있습니다.
 *
 * 강철부대가 위치한 지역을 포함한 총 지역의 수 n
 * 두 지역을 왕복할 수 있는 길 정보를 담은 2차원 정수 배열 roads
 * 각 부대원이 위치한 서로 다른 지역들을 나타내는 정수 배열 sources
 * 강철부대의 지역 destination
 *
 * 주어진 sources의 원소 순서대로 강철부대로 복귀할 수 있는 최단시간을 담은 배열을 return하는 solution 함수를 완성해주세요.
 * 복귀가 불가능한 경우 해당 부대원의 최단시간은 -1입니다.
 *
 * 3 <= n <= 100000
 * 2 <= roads의 길이 <= 500000
 * roads 원소의 길이 = 2
 * 1 <= sources의 길이 <= 500
 * 1 <= sources[i] <= n
 * 1 <= destination <= n
 */

fun main() {
    val n = 3
    val roads = arrayOf(
        intArrayOf(1, 2),
        intArrayOf(2, 3)
    )
    val sources = intArrayOf(2, 3)
    val destination = 1

    val solution = Solution().solution(n, roads, sources, destination)

    println(solution.joinToString(", "))
}

class Solution {
    fun solution(n: Int, roads: Array<IntArray>, sources: IntArray, destination: Int): IntArray {
        // 그래프 생성
        val graph = Array(n + 1) { mutableListOf<Int>() }

        // 그래프 양방향 간선 연결
        for (road in roads) {
            graph[road[0]].add(road[1])
            graph[road[1]].add(road[0])
        }

        // destination부터 각 노드 간의 거리를 담을 배열 생성
        val distances = IntArray(n + 1) { -1 }

        // 탐색을 위한 queue 생성
        val queue = LinkedList<Int>()

        // destination는 0으로 설정 후 출발
        distances[destination] = 0
        queue.add(destination)

        // 탐색 시작
        while (queue.isNotEmpty()) {
            val current = queue.poll()

            // 연결된 간선을 모두 순회
            for (neighbor in graph[current]) {
                // 탐색하지 않은 노드라면
                if (distances[neighbor] == -1) {
                    // 현재 거리로부터 1을 더하여 저장 후 탐색을 위해 queue에 등록
                    distances[neighbor] = distances[current] + 1
                    queue.add(neighbor)
                }
            }
        }

        // 각 source 별로 거리를 찾아서 매핑 후 반환
        return sources.map { distances[it] }.toIntArray()
    }
}
