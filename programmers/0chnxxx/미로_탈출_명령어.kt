/**
 * n * m 격자 미로가 주어집니다.
 * 당신은 미로의 (x, y)에서 출발해 (r, c)로 이동해서 탈출해야 합니다.
 *
 * 단, 미로를 탈출하는 조건이 세 가지 있습니다.
 * 1. 격자의 바깥으로는 나갈 수 없습니다.
 * 2. (x, y)에서 (r, c)까지 이동하는 거리가 총 k여야 합니다. 이때 (x, y)와 (r, c) 격자를 포함해, 같은 격자를 두 번 이상 방문해도 됩니다.
 * 3. 미로에서 탈출한 경로를 문자열로 나타냈을 때, 문자열이 사전 순으로 가장 빠른 경로로 탈출해야 합니다.
 *
 * 이동 경로는 다음과 같이 문자열로 바꿀 수 있습니다.
 * - l : 왼쪽으로 한 칸 이동
 * - r : 오른쪽으로 한 칸 이동
 * - u : 위쪽으로 한 칸 이동
 * - d : 아래쪽으로 한 칸 이동
 * 예를 들어, 왼쪽으로 한 칸, 위로 한 칸, 왼쪽으로 한 칸 움직였다면, 문자열 "lul"로 나타낼 수 있습니다.
 * 미로에서는 인접한 상하좌우 격자로 한 칸씩 이동할 수 있습니다.
 *
 * 격자의 크기를 뜻하는 정수 n, m
 * 출발 위치를 뜻하는 정수 x, y
 * 탈출 지점을 뜻하는 정수 r, c
 * 탈출까지 이동해야 하는 거리를 뜻하는 정수 k
 *
 * 미로를 탈출하기 위한 경로를 return하도록 solution 함수를 완성해주세요.
 * 단, 위 조건대로 미로를 탈출할 수 없는 경우 "impossible"을 return 해야 합니다.
 *
 * 2 <= n, m <= 50
 * 1 <= x, r <= n
 * 1 <= y, c <= m
 * (x, y) != (r, c)
 * 1 <= k <= 2500
 */

fun main() {
    val n = 3
    val m = 4
    val x = 2
    val y = 3
    val r = 3
    val c = 1
    val k = 5

    val solution = Solution().solution(n, m, x, y, r, c, k)

    println(solution)
}

class Solution {
    fun solution(n: Int, m: Int, x: Int, y: Int, r: Int, c: Int, k: Int): String {
        val minDistance = Math.abs(x - r) + Math.abs(y - c)

        // 불가능한 경우 체크
        if (minDistance > k || (k - minDistance) % 2 == 1) {
            return "impossible"
        }

        val directions = listOf(
            Triple(1, 0, "d"),  // 아래
            Triple(0, -1, "l"), // 왼쪽
            Triple(0, 1, "r"),  // 오른쪽
            Triple(-1, 0, "u")  // 위쪽
        )

        val path = StringBuilder()

        fun dfs(currentX: Int, currentY: Int, moves: Int): Boolean {
            val remainingMoves = k - moves
            val requiredMoves = Math.abs(currentX - r) + Math.abs(currentY - c)

            // 남은 이동 횟수보다 필요한 이동 횟수가 더 크면 중단
            if (requiredMoves > remainingMoves) {
                return false
            }

            // 탈출 조건
            if (moves == k) {
                return currentX == r && currentY == c
            }

            for ((dx, dy, direction) in directions) {
                val newX = currentX + dx
                val newY = currentY + dy

                // 범위 확인
                if (newX in 1..n && newY in 1..m) {
                    path.append(direction)

                    if (dfs(newX, newY, moves + 1)) {
                        return true
                    }

                    // 백트래킹
                    path.deleteCharAt(path.lastIndex)
                }
            }

            return false
        }

        dfs(x, y, 0)

        return path.toString()
    }
}
