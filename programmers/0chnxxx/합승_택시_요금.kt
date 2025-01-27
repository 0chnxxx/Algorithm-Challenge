import java.util.PriorityQueue

/**
 * 밤늦게 귀가할 때 안전을 위해 항상 택시를 이용하던 무지는 최근 야근이 잦아져 택시를 더 많이 이용하게 되어 택시비를 아낄 수 있는 방법을 고민하고 있습니다.
 * 무지는 자신이 택시를 이용할 때 동료인 어피치 역시 자신과 비슷한 방향으로 가는 택시를 종종 이용하는 것을 알게 되었습니다.
 * 무지는 어피치와 귀가 방향이 비슷하여 택시 합승을 적절히 이용하면 택시요금을 얼마나 아낄 수 있을지 계산해보고 어피치에게 합승을 제안해 보려고 합니다.
 *
 * 예시 그림은 택시가 이동 가능한 반경에 있는 6개 지점 사이의 이동 가능한 택시노선과 예상요금을 보여주고 있습니다.
 * 그림에서 A와 B 두 사람은 출발지점인 4번 지점에서 출발해서 택시를 타고 귀가하려고 합니다.
 * A의 집은 6번 지점에 있으며 B의 집은 2번 지점에 있고 두 사람이 모두 귀가하는데 소요되는 예상 최저 택시요금이 얼마인지 계산하려고 합니다.
 *
 * 그림의 원은 지점을 나타내며 원 안의 숫자는 지점 번호를 나타냅니다.
 * 지점 번호는 1부터 n까지 사용됩니다.
 * 지점 간에 택시가 이동할 수 있는 경로를 간선이라 하며, 간선에 표시된 숫자는 두 지점 사이의 예상 택시요금을 나타냅니다.
 * A 지점에서 B 지점으로 가거나, B 지점에서 A 지점으로 갈 때 택시요금은 동일하며 이동 방향에 따라 달라지지 않습니다.
 *
 * 지점의 개수 n
 * 출발지점을 나타내는 s
 * A의 도착지점을 나타내는 a
 * B의 도착지점을 나타내는 b
 * 지점 사이의 예상 택시요금을 나타내는 fares
 *
 * 이때, A, B 두 사람이 s에서 출발해서 각각의 도착지점까지 택시를 타고 간다고 가정할 때, 최저 예상 택시요금을 계산해서 return하도록 solution 함수를 완성해주세요.
 * 만약, 아예 합승을 하지 않고 각자 이동하는 경우의 예상 택시요금이 더 낮다면 합승을 하지 않아도 됩니다.
 *
 * 3 <= n <= 200
 * 1 <= s, a, b <= n (s != a != b)
 * 2 <= fares 배열의 크기 <= n * (n - 1) / 2
 *
 * fares 배열의 각 행은 [c, d, f] 형태입니다.
 * c 지점과 d 지점 사이의 예상 택시요금이 f원이라는 뜻입니다.
 *
 * 1 <= c, d <= n
 * 1 <= f <= 100000
 */

fun main() {
    val n = 6
    val s = 4
    val a = 6
    val b = 2
    val fares = arrayOf(
        intArrayOf(4, 1, 10),
        intArrayOf(3, 5, 24),
        intArrayOf(5, 6, 2),
        intArrayOf(3, 1, 41),
        intArrayOf(5, 1, 24),
        intArrayOf(4, 6, 50),
        intArrayOf(2, 4, 66),
        intArrayOf(2, 3, 22),
        intArrayOf(1, 6, 25)
    )

    val solution = Solution().solution(n, s, a, b, fares)

    println(solution)
}

class Solution {
    fun solution(n: Int, s: Int, a: Int, b: Int, fares: Array<IntArray>): Int {
        // 그래프 생성
        val graph = Array(n + 1) { mutableListOf<Pair<Int, Int>>() }

        // 그래프 간선 양방향 연결
        for ((from, to, fee) in fares) {
            graph[from].add(Pair(to, fee))
            graph[to].add(Pair(from, fee))
        }

        // 다익스트라 알고리즘
        fun dijkstra(start: Int): IntArray {
            // 출발 지점부터 각 노드까지의 최소 비용을 저장할 배열
            val distance = IntArray(n + 1) { Int.MAX_VALUE }
            // 최소 비용인 노드를 우선 탐색하기 위한 우선 순위 큐
            val queue = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })

            distance[start] = 0
            queue.offer(start to 0)

            while (queue.isNotEmpty()) {
                val (currentNode, currentCost) = queue.poll()

                // 우선 순위 큐로 정렬한 다음 노드가 최소 비용이 아니라면 생략
                if (currentCost > distance[currentNode]) {
                    continue
                }

                for ((nextNode, nextCost) in graph[currentNode]) {
                    val newCost = currentCost + nextCost

                    // 다음 노드가 더 적은 비용이어야 탐색
                    // 초기 탐색에선 기본 값이 무한대이기 때문에 무조건 탐색
                    if (newCost < distance[nextNode]) {
                        distance[nextNode] = newCost
                        queue.offer(nextNode to newCost)
                    }
                }
            }

            return distance
        }

        // 합승, 환승을 고려하기 위한 모든 지점에서의 최소 비용 계산
        val fromStart = dijkstra(s)
        val fromA = dijkstra(a)
        val fromB = dijkstra(b)

        var minCost = Int.MAX_VALUE

        // 각 지점 k를 합승 종료 지점으로 설정하고 계산
        for (k in 1..n) {
            if (fromStart[k] == Int.MAX_VALUE || fromA[k] == Int.MAX_VALUE || fromB[k] == Int.MAX_VALUE) {
                continue
            }

            // k가 합승 종료 지점일 때
            // s -> k (합승) + k -> a (환승) + k -> b (환승) 이 최종 비용이 됨
            minCost = minOf(minCost, fromStart[k] + fromA[k] + fromB[k])
        }

        // 합승하지 않고 a, b가 각각 이동한 최소 비용 계산
        val eachCost = fromStart[a] + fromStart[b]

        minCost = minOf(minCost, eachCost)

        return minCost
    }
}
