/**
 * 이중 우선순위 큐는 다음 연산을 할 수 있는 자료구조를 말합니다.
 *
 * I 숫자 : 큐에 주어진 숫자를 삽입합니다.
 * D 1 : 큐에서 최댓값을 삭제합니다.
 * D -1 : 큐에서 최솟값을 삭제합니다.
 * 최댓값 최솟값이 둘 이상인 경우, 하나만 삭제합니다.
 * 빈 큐에 데이터를 삭제하라는 연산이 주어질 경우, 해당 연산은 무시합니다.
 *
 * 이중 우선순위 큐가 할 연산 operations가 매개변수로 주어질 때, 모든 연산을 처리한 후 큐가 비어있으면 [0, 0] 비어있지 않으면 [최댓값, 최솟값]을 return하도록 solution 함수를 구현해주세요.
 *
 * 1 <= operations의 길이 <= 1000000
 */

fun main() {
    val operations = arrayOf(
        "I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"
    )

    val solution = Solution().solution(operations)

    println(solution.joinToString(", "))
}

class Solution {
    fun solution(operations: Array<String>): IntArray {
        val list = mutableListOf<Int>()

        for (operation in operations) {
            val (command, data) = operation.split(" ")
            val number = data.toInt()

            when (command) {
                "I" -> {
                    list.add(number)
                }
                "D" -> {
                    if (number == 1) {
                        val max = list.maxOrNull()

                        if (max != null) {
                            list.remove(max)
                        }
                    } else {
                        val min = list.minOrNull()

                        if (min != null) {
                            list.remove(min)
                        }
                    }
                }
            }
        }

        return if (list.isEmpty()) {
            intArrayOf(0, 0)
        } else {
            intArrayOf(list.maxOrNull()!!, list.minOrNull()!!)
        }
    }
}
