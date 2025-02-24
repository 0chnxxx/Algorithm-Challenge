/**
 * n행 m열의 격자가 있습니다.
 * 격자의 각 행은 0, 1, ..., n - 1번의 번호, 그리고 각 열은 0, 1, ..., m - 1번의 번호가 순서대로 매겨져 있습니다.
 *
 * 당신은 이 격자에 공을 하나 두고 그 공에 다음과 같은 쿼리들을 날리고자 합니다.
 * - 열 번호가 감소하는 방향으로 dx칸 이동하는 쿼리 query(0, dx)
 * - 열 번호가 증가하는 방향으로 dx칸 이동하는 쿼리 query(1, dx)
 * - 행 번호가 감소하는 방향으로 dx칸 이동하는 쿼리 query(2, dx)
 * - 행 번호가 증가하는 방향으로 dx칸 이동하는 쿼리 query(3, dx)
 *
 * 단, 공은 격자 바깥으로 이동할 수 없으며, 목적지가 격자 바깥인 경우 공은 이동하다가 더 이상 이동할 수 없을 때 멈추게 됩니다.
 *
 * 격자의 행의 개수 n, 열의 개수 m
 * 정수 x, y
 * 쿼리들의 목록을 나타내는 2차원 정수 배열 queries
 *
 * n * m개의 가능한 시작점에 대해서 시작점에 공을 두고 queries 내의 쿼리들을 순서대로 시뮬레이션 했을 때
 * x행 y열에 도착하는 시작점의 개수를 return 하도록 solution 함수를 완성해주세요.
 *
 * 1 <= n <= 10^9
 * 1 <= m <= 10^9
 * 0 <= x < n
 * 0 <= y < m
 * 1 <= queries의 행의 개수 <= 200000
 * queries의 각 행은 [command, dx] 두 정수로 이루어져 있습니다.
 * 0 <= command <= 3
 * 1 <= dx <= 10^9
 */

fun main() {
    val n = 2
    val m = 2
    val x = 0
    val y = 0
    val queries = arrayOf(
        intArrayOf(2, 1),
        intArrayOf(0, 1),
        intArrayOf(1, 1),
        intArrayOf(0, 1),
        intArrayOf(2, 1)
    )

    val solution = Solution().solution(n, m, x, y, queries)

    println(solution)
}

class Solution {
    fun solution(n: Int, m: Int, x: Int, y: Int, queries: Array<IntArray>): Long {
        // 범위 초기값 설정
        var sx = x
        var sy = y
        var ex = x
        var ey = y

        // 기존 방향의 역방향 설정
        val dx = intArrayOf(0, 0, 1, -1)
        val dy = intArrayOf(1, -1, 0, 0)

        // 쿼리 역순 처리
        for (i in queries.size - 1 downTo 0) {
            val (command, distance) = queries[i]

            // y 방향일 경우
            if (command == 0 || command == 1) {
                // s, e를 기준으로 범위를 확장하거나 축소하여 다음 범위를 구함
                val (nextS, nextE) = findNextRange(sy, ey, distance * dy[command], m)

                // 격자를 벗어난 경우 예외처리
                if (nextS == -1) return 0

                sy = nextS
                ey = nextE
            }
            // x 방향일 경우
            else {
                // s, e를 기준으로 범위를 확장하거나 축소하여 다음 범위를 구함
                val (nextS, nextE) = findNextRange(sx, ex, distance * dx[command], n)

                // 격자를 벗어난 경우 예외처리
                if (nextS == -1) return 0

                sx = nextS
                ex = nextE
            }
        }

        // 최종적으로 구해진 범위의 어디에서든 도착 지점으로 갈 수 있으므로
        // 해당 범위의 면적이 시작 지점의 갯수가 됨
        return (ex - sx + 1).toLong() * (ey - sy + 1).toLong()
    }

    // 범위를 확장하거나 축소하는 함수
    private fun findNextRange(s: Int, e: Int, move: Int, max: Int): Pair<Int, Int> {
        // s, e가 경계에 있을 때 더 확장하지 못하도록 제한
        val nextS = if (s != 0) s + move else 0
        val nextE = if (e != max - 1) e + move else max - 1

        return when {
            (nextS < 0 || nextS >= max) && (nextE < 0 || nextE >= max) -> -1 to -1 //s, e 둘 다 격자를 벗어난 경우
            nextS < 0 -> 0 to nextE // s만 격자를 벗어난 경우
            nextE >= max -> nextS to max - 1 // e만 격자를 벗어난 경우
            else -> nextS to nextE // 격자를 벗어나지 않은 경우
        }
    }
}
