/**
 * N명의 친구가 있고 그 친구들 중에는 M쌍의 친구 관계가 있다.
 * 어떤 친구에게 소문을 퍼뜨리게 된다면 그 소문은 친구의 친구, 친구의 친구의 친구... 를 타고 퍼져나갈 것이다.
 * 1번 친구에게 소문을 퍼트렸을 때 그 소문을 듣게될 친구가 몇 명이나 될지를 구해보자.
 *
 * 첫번째 줄에 친구의 수 N 입력
 * 두번째 줄에 관계의 수 M 입력
 * 다음 M개 줄에는 서로 친구 관계에 있는 두 친구 번호인 u, v가 공백을 두고 입력
 * 모든 친구 관계는 양방향이다.
 * 친구 관계는 중복되지 않는다.
 *
 * 1 <= N <= 500
 * 1 <= M <= 1000
 * 1 <= u, v <= N
 * u != v
 */

import java.util.*

fun main(args: Array<String>) {
    val n = readLine()!!.toInt()
    val m = readLine()!!.toInt()
    val relations = Array(n + 1) { mutableListOf<Int>() }
    val visited = BooleanArray(n + 1)

    repeat(m) {
        val (a, b) = readLine()!!.split(" ").map { it.toInt() }

        relations[a].add(b)
        relations[b].add(a)
    }

    fun dfs(a: Int) {
        visited[a] = true

        for (friend in relations[a]) {
            if (!visited[friend]) {
                dfs(friend)
            }
        }
    }

    dfs(1)

    val result = visited.count { it }

    println(result)
}
