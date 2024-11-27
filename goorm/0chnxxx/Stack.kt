/**
 * 크기가 K인 Stack 자료구조를 구현해보자.
 *
 * 1. 주어지는 명령은 push와 pop 두 가지이다.
 * 2. push는 Stack에 크기가 1인 정수를 추가하는 명령이다.
 * 3. 만약에 이미 Stack이 가득 차 있을 때 push 명령이 주어진다면, 대신 Overflow를 출력한다.
 * 4. pop은 Stack에서 가장 최근에 추가된 정수를 제거하고, 제거된 정수를 출력하는 명령이다.
 * 5. 만약에 이미 Stack이 비어있을 때 pop 명령이 주어진다면, 대신 Underflow를 출력한다.
 *
 * N개의 명령이 주어질 때, 위의 지시 사항에 따라 값을 출력하시오.
 *
 * 첫번째 줄에 주어지는 명령의 개수 N과 Stack의 크기 K가 공백을 두고 주어진다.
 * 다음 N개의 줄에는 명령이 주어진다.
 *
 * push <value> : Stack에 값이 <value>인 정수 데이터를 추가한다.
 * pop : Stack에서 가장 최근에 추가된 정수를 제거한다.
 *
 * 1 <= N <= 100000
 * 1 <= K <= 1000
 * 1 <= <value> <= 100
 */

import java.util.*

fun main(args: Array<String>) {
    val (n, k) = readLine()!!.split(" ").map { it.toInt() }
    val stack = Stack<Int>()
    var size = 0

    repeat(n) {
        val input = readLine()!!

        val (command, value) = if (input.contains(" ")) {
            input.split(" ")[0] to input.split(" ")[1]
        } else {
            input to "0"
        }

        when (command) {
            "push" -> {
                if (size >= k) {
                    println("Overflow")
                } else {
                    stack.push(value.toInt())
                    size++
                }
            }
            "pop" -> {
                if (size <= 0) {
                    println("Underflow")
                } else {
                    println(stack.pop())
                    size--
                }
            }
        }
    }
}
