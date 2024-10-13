import java.util.*

/**
 * 1. 구름이의 지인 수는 항상 짝수
 * 2. 모든 점수는 0점을 제외한 정수
 * 3. 지인 중 같은 점수를 가진 경우는 없음
 * 4. n점을 가진 사람이 있다면 -n점을 가진 사람도 항상 존재
 *
 * 지인들 중 점수의 합이 0이 되는 두 명을 짝지어서 소개팅을 진행
 * 실수로 4번째 규칙을 지키지 못해서 두 사람이 소개팅을 받지 못 하게 됨
 * 소개팅을 받지 못한 두 사람의 점수를 합한 값을 구하라
 *
 * 첫번째 줄에 지인 수 N 입력
 * 두번째 줄에 지인의 점수를 입력
 *
 * 2 <= N <= 100000 (짝수)
 * -200000 <= Si <= 200000
 * Si != 0
 */

fun main(args: Array<String>) {
    val count = readLine()!!.toInt()
    val scores = readLine()!!.split(" ").map { it.toInt() }

    val result = scores.sum()

    println(result)
}
