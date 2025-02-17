/**
 * 당신은 온라인 게임을 운영하고 있습니다.
 * 같은 시간대에 게임을 이용하는 사람이 m명 늘어날 때마다 서버 1대가 추가로 필요합니다.
 *
 * 어느 시간대의 이용자가 m명 미만이라면, 서버 증설이 필요하지 않습니다.
 * 어느 시간대의 이용자가 n * m명 이상 (n + 1) * m명 미만이라면 최소 n대의 증설된 서버가 운영 중이어야 합니다.
 * 한 번 증설한 서버는 k시간 동안 운영하고 그 이후에는 반납합니다.
 * 예를 들어, k = 5 일 때 10시에 증설한 서버는 10~15시에만 운영됩니다.
 *
 * 하루 동안 모든 게임 이용자가 게임을 하기 위해 서버를 최소 몇 번 증설해야 하는지 알고 싶습니다.
 * 같은 시간대에 서버를 x대 증설했다면 해당 시간대의 증설 횟수는 x회입니다.
 *
 * 0시에서 23시까지의 시간대별 게임 이용자의 수를 나타내는 1차원 정수 배열 players
 * 서버 한 대로 감당할 수 있는 최대 이용자의 수를 나타내는 정수 m
 * 서버 한 대가 운영 가능한 시간을 나타내는 정수 k
 * 이때, 모든 게임 이용자를 감당하기 위한 최소 서버 증설 횟수를 return하도록 solution 함수를 완성해 주세요.
 *
 * players의 길이 = 24
 * 0 <= players의 원소 <= 1000
 * players[i]는 i시 ~ i + 1시 사이의 게임 이용자의 수를 나타냅니다.
 * 1 <= m <= 1000
 * 1 <= k <= 24
 */

fun main() {
    val players = intArrayOf(0, 2, 3, 3, 1, 2, 0, 0, 0, 0, 4, 2, 0, 6, 0, 4, 2, 13, 3, 5, 10, 0, 1, 5)
    val m = 3
    val k = 5

    val solution = Solution().solution(players, m, k)

    println(solution)
}

class Solution {
    fun solution(players: IntArray, m: Int, k: Int): Int {
        val servers = IntArray(players.size) { 0 }
        var count = 0

        for (i in players.indices) {
            val player = players[i]
            val neededServer = (player / m) - servers[i]

            if (player < m) {
                continue
            }

            if (neededServer > 0) {
                count += neededServer

                for (j in 0 until k) {
                    if (i + j < 24) {
                        servers[i + j] += neededServer
                    }
                }
            }
        }

        return count
    }
}
