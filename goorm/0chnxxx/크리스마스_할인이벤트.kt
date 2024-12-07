/**
 * 구름이는 음식집을 운영하고 있습니다.
 * 이번 12월 크리스마스 이벤트로 카테고리 별로 가장 많이 팔린 매뉴를 두 개씩 모아 세트로 출시하고 같은 할인율을 적용합니다.
 * 다른 매뉴들도 동일한 방식으로 순차적으로 할인율을 적용하려고 합니다.
 *
 * 할인율을 적용하는 기준은 다음과 같습니다.
 * 1. 음식이 많이 팔린 카테고리에 더 높은 할인율이 적용됩니다.
 * 2. 카테고리 내에서 많이 팔린 매뉴에 더 높은 할인율이 적용됩니다.
 * 3. 카테고리 내에서 팔린 횟수가 같은 음식 중에서는 고유 번호가 낮은 음식에 더 높은 할인율이 적용됩니다.
 *
 * 음식마다 카테고리와 팔린 횟수가 주어질 때 할인율이 높은 순서대로 고유 번호를 출력하라.
 *
 * 첫번째 줄에 음식의 총 개수 N, 카테고리의 개수 M 입력
 * 두번째 줄부터 N번 고유번호가 i인 카테고리 Ci와 팔린 횟수 Bi가 공백을 두고 입력
 *
 * 1 <= N <= 100
 * 1 <= M <= 10
 * 1 <= Bi <= 1000
 */

fun main(args: Array<String>) {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val dishes = mutableListOf<Triple<Int, String, Int>>()

    for (i in 0 until n) {
        val (category, count) = readLine()!!.split(" ")

        dishes.add(Triple(i, category, count.toInt()))
    }

    val groupedDishes = dishes
        .groupBy { it.second }
        .entries
        .sortedWith(compareByDescending { it.value.sumOf { value -> value.third } })

    val sortedDished = groupedDishes
        .map { it.value
            .sortedWith(compareByDescending<Triple<Int, String, Int>> { value -> value.third }
            .thenBy { value -> value.first }) }

    val result = mutableListOf<Int>()

    for (dishes in sortedDished) {
        val maxSize = if (dishes.size >= 2) 2 else 1
        val subDishes = dishes.subList(0, maxSize)

        for (subDish in subDishes) {
            result.add(subDish.first)
        }
    }

    println(result.joinToString(" "))
}
