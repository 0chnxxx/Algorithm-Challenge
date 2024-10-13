/**
 * 며칠 후 모든 단풍나무가 물드는지 출력
 *
 * N * N 크기의 공원
 * (i, j) 구역에는 S(i, j) 그루의 물들지 않은 단풍나무가 있다.
 *
 * 단풍나무가 물드는 규칙
 * 1. S(i, j)의 값이 0이면 해당 구역의 모든 단풍나무가 물들었음을 의미
 * 2. S(i, j)는 매일 밤마다 상하좌우로 인접한 구역 중 그날 아침 기준으로 단풍나무가 모두 물들어 있는 구역의 수만큼 줄어든다.
 * 3. 만약 그러한 구역의 수가 S(i, j)보다 크다면 S(i, j)의 값은 0이 된다.
 *
 * 첫번째 줄에 공원의 크기 N 입력
 * 이후 N개 줄에 공원 상태 입력
 *
 * 1 <= N <= 40
 * 0 <= S(i, j) <= 10
 */

fun main(args: Array<String>) {
    val length = readLine()!!.toInt()
    val land = Array(length) { IntArray(length) }

    for (i in 0 until length) {
        val status = readLine()!!.split(" ").map { it.toInt() }

        for (j in 0 until status.size) {
            land[i][j] = status[j]
        }
    }

    var day = 0

    while (!isComplete(land)) {
        val data = scan(land)
        process(land, data)
        day += 1
    }

    println(day)
}

private fun isComplete(land: Array<IntArray>): Boolean {
    return land.all { row -> row.all { item -> item == 0 } }
}

private fun scan(land: Array<IntArray>): List<Triple<Int, Int, Int>> {
    val sections = mutableListOf<Triple<Int, Int, Int>>()

    for (i in 0 until land.size) {
        for (j in 0 until land[i].size) {
            if (land[i][j] == 0) {
                continue
            }

            val tree = findStainedTree(land, i, j)

            sections.add(Triple(i, j, tree))
        }
    }

    return sections
}

private fun process(land: Array<IntArray>, data: List<Triple<Int, Int, Int>>) {
    data.forEach { (x, y, tree) ->
        land[x][y] = if ((land[x][y] - tree) >= 0) land[x][y] - tree else 0
    }
}

private fun findStainedTree(land: Array<IntArray>, x: Int, y: Int): Int {
    val directions = arrayOf(
        intArrayOf(x - 1, y),
        intArrayOf(x + 1, y),
        intArrayOf(x, y - 1),
        intArrayOf(x, y + 1)
    )

    var stainedTree = 0

    for (direction in directions) {
        try {
            val tree = land[direction[0]][direction[1]]

            if (tree == 0) {
                stainedTree += 1
            }
        } catch (e: ArrayIndexOutOfBoundsException) {
            continue
        }
    }

    return stainedTree
}
