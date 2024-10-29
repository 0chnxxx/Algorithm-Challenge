/**
 * 해설진들은 선수들이 자기 바로 앞의 선수를 추월할 때 선수의 이름을 부른다.
 * 선수들의 이름이 1등부터 현재 등수까지 순서대로 담긴 문자열 배열 players
 * 해설진이 부른 이름을 담은 문자열 배열 callings
 * 경주가 끝났을 때 선수들의 이름을 1등부터 등수 순서대로 배열에 담아 return하라.
 *
 * 5 <= players의 길이 <= 50000
 * 3 <= players의 원소의 길이 <= 10
 * players에 중복된 이름은 없다.
 * 2 <= callings의 길이 <= 1000000
 * callings에 1등 선수의 이름은 없다.
 */

class Solution {
    fun solution(players: Array<String>, callings: Array<String>): Array<String> {
        val indexMap = mutableMapOf<String, Int>()

        players.forEachIndexed { index, player -> indexMap[player] = index }

        for (calling in callings) {
            val currentIndex = indexMap[calling]!!

            if (currentIndex > 0) {
                val forePlayer = players[currentIndex - 1]

                players[currentIndex] = forePlayer
                players[currentIndex - 1] = calling

                indexMap[calling] = currentIndex - 1
                indexMap[forePlayer] = currentIndex
            }
        }

        return players
    }
}

fun main(args: Array<String>) {
    println(Solution().solution(
        arrayOf("mumu", "soe", "poe", "kai", "mine"),
        arrayOf("kai", "kai", "mine", "mine")
    ).joinToString(", ®"))
}
