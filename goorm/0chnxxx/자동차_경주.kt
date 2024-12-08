/**
 * 구름이는 자동차 레이스 경기를 관람하고 있습니다.
 * 자동차는 최대 속도 N으로 출발합니다.
 * X초 동안 엑셀을 밟으면서 1초마다 Y만큼의 속도가 증가합니다.
 * X초 연속으로 가속을 하면 속도가 Z만큼 추가로 증가합니다.
 * 자동차는 최대 속도가 존재해 현재 속도가 최대 속도를 넘어갈 수 없습니다.
 *
 * i번째 오르막길은 Ti초에 나타나며 Di만큼 속도가 감소합니다.
 * 또한 오르막길을 지날 때에는 가속을 할 수가 없습니다.
 * 만약 속도가 0 이하가 되면 자동차는 문제가 생겨 완주할 수 없습니다.
 *
 * 구름이는 M개의 오르막길을 지나고 난 뒤에 자동차 속도가 얼마 인지가 궁금해졌습니다.
 * 경기가 끝난 후 자동차의 속도를 구하는 프로그램을 작성해주세요.
 * 만약 완주하지 못한다면 -1을 return 해주세요.
 *
 * 첫번째 줄에 최대 속도 N, 오르막길의 개수 M 입력
 * 두번째 줄에 연속 시간 X, 초당 증가 속도 Y, 추가 증가 속도 Z 입력
 * 세번째 줄부터 M개의 줄에 오르막길을 지나는 시간 Ti, 감소 속도 Di 입력
 *
 * 1 <= N <= 30
 * 1 <= M <= 10
 * 1 <= X <= 5
 * 1 <= Y <= 10
 * 1 <= Z <= 10
 * 1 <= Ti <= 100
 * 1 <= Di <= 10
 */

import java.util.*

fun main(args: Array<String>) {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val (x, y, z) = readLine()!!.split(" ").map { it.toInt() }
    val uphills = MutableList(m) {
        val (t, d) = readLine()!!.split(" ").map { it.toInt() }
        t to d
    }.sortedBy { it.first }.toMutableList()

    var time = 0
    var speed = n
    var boostCount = 0

    while (uphills.isNotEmpty()) {
        // 완주 불가능
        if (speed <= 0) {
            speed = -1
            break
        }

        // 방지턱
        val (t, d) = uphills.first()

        if (t == time) {
            speed -= d
            boostCount = 0
            time++
            uphills.removeAt(0)
            continue
        }

        // 일반 가속
        if (speed + y > n) {
            speed = n
        } else {
            speed += y
        }

        // 추가 가속
        if (boostCount == x) {
            if (speed + z > n) {
                speed = n
            } else {
                speed += z
            }

            boostCount = 0
        } else {
            boostCount++
        }

        // 시간 증가
        time++
    }

    println(speed)
}
