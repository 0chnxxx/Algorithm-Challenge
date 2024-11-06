import java.util.*

/**
 * 크기가 K인 Queue 자료구조를 구현해보자.
 *
 * 주어지는 명령은 push와 pop 두 가지이다.
 * push는 Queue에 크기가 1인 정수를 추가하는 명령이다.
 * 만약에 이미 Queue가 가득 차 있을 때 push 명령이 주어진다면, Overflow를 출력한다.
 * pop은 Queue에서 가장 추가된 지 오래된 정수를 제거하고 제거된 정수를 출력하는 명령이다.
 * 만약에 이미 Queue가 비어있을 때 pop 명령이 주어진다면, Underflow를 출력한다.
 *
 * N개의 명령이 주어질 때 위의 지시 사항에 따라 값을 출력하라.
 * 첫번째 줄에 명령의 개수 N과 Queue의 크기 K가 공백을 두고 입력
 * 다음 N개 줄에는 명령과 값이 입력
 *
 * 1 <= N <= 100000
 * 1 <= K <= 1000
 * 값은 1 이상 100 이하의 정수이다.
 */

fun main(args: Array<String>) {
    val (n, k) = readLine()!!.split(" ").map { it.toInt() }
    val commands = mutableListOf<Pair<String, Int>>()

    for (i in 0 until n) {
        val input = readLine()

        if (input!!.contains(" ")) {
            val (command, value) = input.split(" ")

            commands.add(command to value.toInt())
        } else {
            val (command, _) = input.split(" ")

            commands.add(command to 0)
        }
    }

    val queue = LinkedList<Int>()

    for ((command, value) in commands) {
        if (command == "push") {
            if (queue.size >= k) {
                println("Overflow")
                continue
            }

            queue.offer(value)
        }

        if (command == "pop") {
            if (queue.size == 0) {
                println("Underflow")
                continue
            }

            val pop = queue.poll()
            println(pop)
        }
    }
}
