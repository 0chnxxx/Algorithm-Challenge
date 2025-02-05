import java.util.PriorityQueue

/**
 * XX산은 n개의 지점으로 이루어져 있습니다.
 * 각 지점은 1부터 n까지 번호가 붙어있으며, 출입구, 쉼터, 혹은 산봉우리입니다.
 * 각 지점은 양방향 통행이 가능한 등산로로 연결되어 있으며, 서로 다른 지점을 이동할 때 이 등산로를 이용해야 합니다.
 * 이때, 등산로별로 이동하는데 일정 시간이 소요됩니다.
 *
 * 등산코스는 방문할 지점 번호들을 순서대로 나열하여 표현할 수 있습니다.
 * 예를 들어 1-2-3-2-1 으로 표현하는 등산코스는 1번지점에서 출발하여 2번, 3번, 2번, 1번 지점을 순서대로 방문한다는 뜻입니다.
 * 등산코스를 따라 이동하는 중 쉼터 혹은 산봉우리를 방문할 때마다 휴식을 취할 수 있으며, 휴식 없이 이동해야 하는 시간 중 가장 긴 시간을 해당 등산코스의 intensity라고 부르기로 합니다.
 *
 * 당신은 XX산의 출입구 중 한 곳에서 출발하여 산봉우리 중 한 곳만 방문한 뒤 다시 원래의 출입구로 돌아오는 등산코스를 정하려고 합니다.
 * 다시 말해, 등산코스에서 출입구는 처음과 끝에 한 번씩, 산봉우리는 한 번만 포함되어야 합니다.
 * 당신은 이러한 규칙을 지키면서 intensity가 최소가 되도록 등산코스를 정하려고 합니다.
 *
 * XX산의 지점 수 n
 * 각 등산로의 정보를 담은 2차원 정수 배열 paths
 * 출입구들의 번호가 담긴 정수 배열 gates
 * 산봉우리들의 번호가 담긴 정수 배열 summits
 *
 * intensity가 최소가 되는 등산코스에 포함된 산봉우리 번호와 intensity의 최솟값을 차례대로 정수 배열에 담아 return 하도록 solution 함수를 완성해주세요.
 * intensity가 최소가 되는 등산 코스가 여러 개라면 그중 산봉우리의 번호가 가장 낮은 등산코스를 선택합니다.
 *
 * 2 <= n <= 50000
 * n - 1 <= paths의 길이 <= 200000
 * paths의 원소는 [i, j, w] 형태입니다.
 * i번 지점과 j번 지점을 연결하는 등산로가 있다는 뜻입니다.
 * w는 두 지점 사이를 이동하는데 걸리는 시간입니다.
 * 1 <= i < j <= n
 * 1 <= w <= 10000000
 * 1 <= gates의 길이 <= n
 * 1 <= gates의 원소 <= n
 * 1 <= summits의 길이 <= n
 * 1 <= summits의 원소 <= n
 * gates와 summits에 등장하지 않은 지점은 모두 쉼터입니다.
 * return 하는 배열은 [산봉우리의 번호, intensity의 최솟값] 순서여야 합니다.
 */

fun main() {
    val n = 6
    val paths = arrayOf(
        intArrayOf(1, 2, 3),
        intArrayOf(2, 3, 5),
        intArrayOf(2, 4, 2),
        intArrayOf(2, 5, 4),
        intArrayOf(3, 4, 4),
        intArrayOf(4, 5, 3),
        intArrayOf(4, 6, 1),
        intArrayOf(5, 6, 1)
    )
    val gates = intArrayOf(1, 3)
    val summits = intArrayOf(5)

    val solution = Solution().solution(n, paths, gates, summits)

    println(solution.joinToString(", "))
}

class Solution {
    fun solution(n: Int, paths: Array<IntArray>, gates: IntArray, summits: IntArray): IntArray {
        val graph = Array(n + 1) { mutableListOf<Pair<Int, Int>>() }

        for ((i, j, w) in paths) {
            graph[i].add(j to w)
            graph[j].add(i to w)
        }

        val queue = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })
        val intensities = IntArray(n + 1) { Int.MAX_VALUE } // 노드별 최대 intensity
        val isSummit = BooleanArray(n + 1) // 산봉우리 여부

        for (summit in summits) {
            isSummit[summit] = true
        }

        for (gate in gates) {
            intensities[gate] = 0
            queue.add(gate to 0)
        }

        // BFS 기반의 다익스트라
        while (queue.isNotEmpty()) {
            val (current, intensity) = queue.poll()

            // 최소 가중치 경로 탐색 (간선 선택 기준은 intensities의 최대 가중치보다 작아야함)
            if (intensity > intensities[current]) {
                continue
            }

            for ((next, distance) in graph[current]) {
                val maxIntensity = maxOf(intensity, distance)

                // 다음 가중치가 현재까지의 최대 가중치보다 낮아야 이동 및 갱신
                if (maxIntensity < intensities[next]) {
                    intensities[next] = maxIntensity

                    // 산봉우리가 아닌 경우 이동
                    if (!isSummit[next]) {
                        queue.add(next to maxIntensity)
                    }
                }
            }
        }

        var minSummit = -1
        var minIntensity = Int.MAX_VALUE

        // 모든 산봉우리를 순회
        for (summit in summits) {
            // 최소 intensity 면서 번호가 가장 앞서는 산봉우리를 찾음
            if (intensities[summit] < minIntensity || (intensities[summit] == minIntensity && summit < minSummit)) {
                minSummit = summit
                minIntensity = intensities[summit]
            }
        }

        return intArrayOf(minSummit, minIntensity)
    }
}
