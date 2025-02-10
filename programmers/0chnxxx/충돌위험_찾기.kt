/**
 * 어떤 물류 센터는 로봇을 이용한 자동 운송 시스템을 운영합니다.
 *
 * 운송 시스템이 작동하는 규칙은 다음과 같습니다.
 * 1. 물류 센터에는 (r, c)와 같이 2차원 좌표로 나타낼 수 있는 n개의 포인트가 존재합니다.
 * 각 포인트는 1~n까지의 서로 다른 번호를 가집니다.
 * 2. 로봇마다 정해진 운송 경로가 존재합니다.
 * 운송 경로는 m개의 포인트로 구성되고 로봇은 첫 포인트에서 시작해 할당된 포인트를 순서대로 방문합니다.
 * 3. 운송 시스템에 사용되는 로봇은 x대이고, 모든 로봇은 0초에 동시에 출발합니다.
 * 로봇은 1초마다 r 좌표와 c 좌표 중 하나가 1만큼 감소하거나 증가한 좌표로 이동할 수 있습니다.
 * 4. 다음 포인트로 이동할 때는 항상 최단 경로로 이동하며 최단 경로가 여러 가지일 경우, r 좌표가 변하는 이동을 c 좌표가 변하는 이동보다 먼저 합니다.
 * 5. 마지막 포인트에 도착한 로봇은 운송을 마치고 물류 센터를 벗어납니다.
 * 로봇이 물류 센터를 벗어나는 경로는 고려하지 않습니다.
 *
 * 이동 중 같은 좌표에 로봇이 2대 이상 모인다면 충돌할 가능성이 있는 위험 상황으로 판단합니다.
 * 관리자인 당신은 현재 설정대로 로봇이 움직일 때 위험한 상황이 총 몇 번 일어나는지 알고 싶습니다.
 * 만약 어떤 시간에 여러 좌표에서 위험 상황이 발생한다면 그 횟수를 모두 더합니다.
 *
 * 운송 포인트 n개의 좌표를 담은 2차원 정수 배열 points
 * 로봇 x대의 운송 경로를 담은 2차원 정수 배열 routes
 *
 * 이때 모든 로봇이 운송을 마칠 때까지 발생하는 위험한 상황의 횟수를 return하도록 solution 함수를 완성해 주세요.
 *
 * 2 <= points의 길이 = n <= 100
 * points[i]는 i + 1번 포인트의 [r 좌표, c 좌표]를 나타내는 길이가 2인 정수 배열입니다.
 * 1 <= r <= 100
 * 1 <= c <= 100
 * 2 <= routes의 길이 = m <= 100
 * routes[i]는 i + 1번째 로봇의 운송 경로를 나타냅니다.
 * routes[i]의 길이는 모두 같습니다.
 * routes[i][j]는 i + 1번째 로봇이 j + 1번째로 방문하는 포인트 번호를 나타냅니다.
 * 1 <= routes[i][j] <= n
 */

fun main() {
    val points = arrayOf(
        intArrayOf(3, 2),
        intArrayOf(6, 4),
        intArrayOf(4, 7),
        intArrayOf(1, 4)
    )
    val routes = arrayOf(
        intArrayOf(4, 2),
        intArrayOf(1, 3),
        intArrayOf(2, 4)
    )

    val solution = Solution().solution(points, routes)

    println(solution)
}

class Solution {
    fun solution(points: Array<IntArray>, routes: Array<IntArray>): Int {
        var crash = 0

        // 포인트 매핑
        val pointMap = mutableMapOf<Int, Pair<Int, Int>>()

        for (i in points.indices) {
            pointMap[i + 1] = points[i][0] to points[i][1]
        }

        // 모든 로봇 경로 계산
        val paths = mutableListOf<List<Pair<Int, Int>>>()

        for (route in routes) {
            val path = mutableListOf<Pair<Int, Int>>()
            var (startR, startC) = pointMap[route[0]]!!

            path.add(startR to startC)

            // 모든 구간
            for (j in 1 until route.size) {
                var currentR = startR
                var currentC = startC
                val (endR, endC) = pointMap[route[j]]!!

                while (currentR != endR || currentC != endC) {
                    // R 우선 이동
                    if (currentR != endR) {
                        currentR += if (currentR < endR) 1 else -1
                    } else {
                        currentC += if (currentC < endC) 1 else -1

                    }

                    path.add(currentR to currentC)
                }

                // 다음 구간을 위한 위치 업데이트
                startR = endR
                startC = endC
            }

            paths.add(path)
        }

        // 최대 시간 계산
        val maxTime = paths.maxOf { it.size }

        // 시간별 이동 및 충돌 확인
        for (time in 0 until maxTime) {
            val positionSet = mutableSetOf<Pair<Int, Int>>()
            val crashSet = mutableSetOf<Pair<Int, Int>>()

            for (path in paths) {
                // 모든 이동을 마친 로봇은 스킵
                if (time >= path.size) {
                    continue
                }

                // 해당 시간의 위치
                val position = path[time]

                // 동일 위치에 로봇이 이미 있다면 충돌
                if (position in positionSet) {
                    crashSet.add(position) // 같은 위치에서 중복 충돌을 방지하기 위한 Set 사용
                    continue
                }

                positionSet.add(position)
            }

            crash += crashSet.size
        }

        return crash
    }
}
