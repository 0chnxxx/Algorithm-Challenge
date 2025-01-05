/**
 * 과제를 받은 루는 다음과 같은 순서대로 과제를 하려고 계획을 세웠습니다.
 *
 * 과제는 시작하기로 한 시각이 되면 시작합니다.
 * 새로운 과제를 시작할 시각이 되었을 때, 기존에 진행 중이던 과제가 있다면 진행 중이던 과제를 멈추고 새로운 과제를 시작합니다.
 * 진행 중이던 과제를 끝냈을 때, 잠시 멈춘 과제가 있다면 멈춰둔 과제를 이어서 진행합니다.
 * 만약 과제를 끝낸 시각에 새로 시작해야 되는 과제와 잠시 멈춰둔 과제가 모두 있다면 새로 시작해야 하는 과제부터 진행합니다.
 * 멈춰둔 과제가 여러 개일 경우, 가장 최근에 멈춘 과제부터 시작합니다.
 *
 * 과제 계획을 담은 이차원 문자열 배열 plans가 매개변수로 주어질 때 과제를 끝낸 순서대로 이름을 배열에 담아 return 하는 solution 함수를 완성하세요.
 *
 * 3 <= plans의 길이 <= 1000
 * plans의 원소는 [name, start, playtime] 으로 구성되어 있습니다.
 *
 * 2 <= name의 길이 <= 10
 * starts는 hh:mm 의 형태입니다.
 * 1 <= playtime <= 100
 */

fun main() {
    val plans = arrayOf(
        arrayOf("korean", "11:40", "30"),
        arrayOf("english", "12:10", "20"),
        arrayOf("math", "12:30", "40")
    )

    val solution = Solution().solution(plans)

    println(solution.joinToString(", "))
}

class Solution {
    fun solution(plans: Array<Array<String>>): Array<String> {
        fun timeToMinutes(time: String): Int {
            val (hour, minute) = time.split(":").map { it.toInt() }

            return hour * 60 + minute
        }

        val sortedPlans = plans
            .map { Triple(it[0], timeToMinutes(it[1]), it[2].toInt()) }
            .sortedBy { it.second }

        val result = mutableListOf<String>()
        val stack = mutableListOf<Pair<String, Int>>()
        var currentTime = 0

        for ((name, start, playtime) in sortedPlans) {
            while (stack.isNotEmpty() && currentTime < start) {
                val (pausedName, remainingTime) = stack.removeAt(stack.size - 1)

                if (currentTime + remainingTime <= start) {
                    currentTime += remainingTime
                    result.add(pausedName)
                } else {
                    stack.add(pausedName to (remainingTime - (start - currentTime)))
                    currentTime = start
                    break
                }
            }

            currentTime = start
            stack.add(name to playtime)
        }

        while (stack.isNotEmpty()) {
            val (pausedName, remainingTime) = stack.removeAt(stack.size - 1)

            result.add(pausedName)
        }

        return result.toTypedArray()
    }
}
