/**
 * 딱지에는 별(4), 동그라미(3), 네모(2), 세모(1) 중 하나 이상의 모양이 표시
 * 별, 동그라미, 네모, 세모 순으로 더 많은 쪽의 딱지가 이긴다.
 * 모든 모양의 갯수가 같다면 무승부
 * 
 * 첫번째 줄에 라운드 수 N 입력
 * 이후 2N줄에 각 라운드별 딱지 상태가 입력
 * 각 줄마다 첫 번째 값 a는 그림의 갯수
 *
 * A가 승자라면 A 출력, B가 승자라면 B 출력, 무승부라면 D 출력
 * 
 * 1 <= N <= 1000
 * 1 <= a <= 100
 */

fun main(args: Array<String>) {
    val round = readLine()!!.toInt()
    
    for (i in 1..round) {
        val personA = readLine()!!.split(" ").map { it.toInt() }
        val shapesOfPersonA = personA.subList(1, personA.size).groupingBy { it }.eachCount()
        val personB = readLine()!!.split(" ").map { it.toInt() }
        val shapesOfPersonB = personB.subList(1, personB.size).groupingBy { it }.eachCount()

        for (j in 4 downTo 1) {
            if (j == 1 && shapesOfPersonA[j] == shapesOfPersonB[j]) {
                println("D")
                break
            } else if (shapesOfPersonA[j] == shapesOfPersonB[j]) {
                continue
            }

            if ((shapesOfPersonA[j] ?: 0) > (shapesOfPersonB[j] ?: 0)) {
                println("A")
                break
            }

            if ((shapesOfPersonA[j] ?: 0) < (shapesOfPersonB[j] ?: 0)) {
                println("B")
                break
            }
        }
    }

    println()
}
