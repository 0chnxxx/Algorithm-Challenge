/**
 * 택배상자는 크기가 모두 같으며 1번 상자부터 n번 상자까지 번호가 증가하는 순서대로 컨테이너 벨트에 일렬로 놓여 전달된다.
 * 컨테이너 벨트는 한 방향으로만 진행이 가능해서 벨트에 놓인 순서대로 상자를 내릴 수 있다.
 * 만약 컨테이너 벨트의 맨 앞에 놓인 상자가 현재 트럭에 실어야 하는 순서가 아니라면 그 상자를 트럭에 실을 순서가 될 때까지 잠시 보조 컨테이너 벨트에 보관한다.
 * 보조 컨테이너 벨트는 앞 뒤로 이동이 가능하지만 입구 외에 다른 면이 막혀 있어서 맨 앞의 상자만 뺄 수 있다. (Stack)
 * 보조 컨테이너 벨트를 이용해도 원하는 순서대로 상자를 싣지 못 한다면 더 이상 상자를 싣지 않는다.
 *
 * 1 <= order의 길이 <= 1000000
 * order는 1 이상 order의 길이 이하의 모든 정수가 한번씩 등장한다.
 * order[i]는 기존의 컨테이너 벨트에 order[i]번째 상자를 i+1번째로 트럭에 실어야 함을 의미한다.
 */

import java.util.*

class Solution {
    fun solution(order: IntArray): Int {
        var result = 0
        val stack = Stack<Int>()
        var current = 1

        for (i in 0 until order.size) {
            while (current <= order[i]) {
                stack.push(current)
                current++
            }

            if (stack.isEmpty() || stack.peek() > order[i]) {
                break
            }

            stack.pop()
            result++
        }

        return result
    }
}

fun main(args: Array<String>) {
    val order = intArrayOf(5, 4, 3, 2, 1)

    val result = Solution().solution(order)

    println(result)
}
