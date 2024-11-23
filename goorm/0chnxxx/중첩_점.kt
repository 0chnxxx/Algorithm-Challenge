/**
 * 한 변의 길이가 N인 정사각형이 있다.
 * 플레이어는 이 정사각형 위에 M개의 반직선을 그린 뒤, 두 반직선이 교차하는 점의 수를 세려고 한다.
 * 플레이어가 반직선을 그리는 과정은 아래와 같다.
 * 1. 반직선을 그리기 시작할 칸 (y, x)을 정한다.
 * (y, x)는 주어진 정사각형을 1 * 1 크기의 정사각형으로 나눴을 때, y번째 행의 x번째 열에 해당하는 칸이다.
 * 2. 반직선을 그릴 방향 d를 정한다.
 * 방향은 상하좌우 중 하나이며, 항상 정사각형 테두리의 가로 혹은 세로와 평행하다.
 * 3. 반직선을 그린다.
 * 반직선은 항상 시작 칸의 테두리에서부터 시작하며, 같은 칸을 지나는 평행한 직선이 서로 만나지 않도록 그린다.
 *
 * 모든 반직선을 그렸을 때 중첩 점의 개수를 출력하라.
 *
 * 첫번째 줄에 정사각형의 크기 N과 그리려는 반직선의 개수 M이 공백을 두고 주어진다.
 * 다음 M개 줄에는 플레이어가 그을 반직선의 정보를 나타내는 Yi, Xi, Di 가 공백을 두고 입력.
 * (Yi, Xi)칸에서 시작해 Di 방향으로 반직선을 긋는다는 의미이다.
 *
 * 1 <= N <= 100
 * 1 <= M <= 100000
 * 1 <= Xi, Yi <= N
 * Di는 U, D, L, R 의 네 문자 중 하나이다.
 */

fun main(args: Array<String>) {
    val (n, m) = readLine()!!.split(" ").map(String::toInt)
    val grid = Array(n) { Array(n) { Array(2) { 0L } } }

    repeat(m) {
        val line = readLine()!!.split(" ")
        val y = line[0].toInt() - 1
        val x = line[1].toInt() - 1
        val d = line[2]

        when (d) {
            "U" -> {
                for (i in y downTo 0) {
                    grid[i][x][0]++
                }
            }
            "D" -> {
                for (i in y until n) {
                    grid[i][x][0]++
                }
            }
            "L" -> {
                for (j in x downTo 0) {
                    grid[y][j][1]++
                }
            }
            "R" -> {
                for (j in x until n) {
                    grid[y][j][1]++
                }
            }
        }
    }

    println(grid.sumOf { it.sumOf { it[0] * it[1] } })
}
