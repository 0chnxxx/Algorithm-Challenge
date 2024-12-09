/**
 * 구름 레스토랑의 매니저 구름이는 매일 판매된 음식 매뉴를 기록하고, 가장 많이 팔린 매뉴를 확인해 다음날 추천 매뉴로 표시한다.
 * 오늘 하루 동안 판매된 음식 매뉴가 입력으로 주어졌을 때, 가장 많이 팔린 매뉴 이름을 출력하는 프로그램을 작성한다.
 * 만약 가장 많이 팔린 매뉴가 여러 개인 경우 사전순으로 가장 앞서는 매뉴를 출력한다.
 *
 * 첫번째 줄에 오늘 하루 동안 판매된 음식의 개수 N이 입력
 * 두번째 줄부터 N개 줄에 걸쳐 음식 매뉴 이름이 주어진다.
 *
 * 1 <= N <= 100
 */

fun main(args: Array<String>) {
    val n = readLine()!!.toInt()
    val menus = mutableListOf<String>()

    repeat(n) {
        val menu = readLine()!!

        menus.add(menu)
    }

    val result = menus
        .groupingBy { it }
        .eachCount()
        .entries
        .sortedWith(compareByDescending<Map.Entry<String, Int>> { it.value }.thenBy { it.key })
        .first()
        .key

    println(result)
}
