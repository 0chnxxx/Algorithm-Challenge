/**
 * 연구소에는 N개의 물질이 있다.
 * 각 물질에 1번부터 N번까지 번호를 붙여 관리하고 있다.
 * 연구소에 있는 물질 중에서 가장 밀도가 높은 물질을 찾으려고 한다.
 * 어떤 물질의 밀도는 그 물질의 무게를 부피로 나눈 값으로 계산한다.
 * N개의 물질의 부피와 무게가 주어졌을 때, 가장 밀도가 높은 물질의 번호를 출력하라.
 * 물질의 밀도가 같다면 그 중 더 무거운 물질의 번호를 출력한다.
 * 물질의 밀도와 무게가 모두 같다면 그 중 번호가 가장 작은 물질의 번호를 출력한다.
 *
 * 첫번째 줄에 물질의 개수 N 입력
 * 다음 N개 줄에 i번 물질의 무게 Wi, 부피 Vi가 공백을 두고 입력
 *
 * 1 <= N <= 100000
 * 1 <= Wi <= 100000
 * 1 <= Vi <= 10000
 */

fun main(args: Array<String>) {
    val n = readLine()!!.toInt()
    val materials = mutableListOf<Triple<Int, Int, Int>>()

    for (i in 1..n) {
        val (w, v) = readLine()!!.split(" ").map { it.toInt() }

        materials.add(Triple(i, w, v))
    }

    val sortedMaterials = materials.sortedWith(
        compareByDescending<Triple<Int, Int, Int>> { it.second.toDouble() / it.third }
            .thenByDescending { it.second }
            .thenBy { it.first }
    )

    println(sortedMaterials.first().first)
}
