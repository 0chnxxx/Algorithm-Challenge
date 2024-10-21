/**
 * 롤케이크를 두 조각으로 잘라서 공평하게 나눠먹으려 한다.
 * 잘린 조각들의 크기와 올려진 토핑의 개수에 상관없이 각 조각에 동일한 가짓수의 토핑이 올라가면 공평하게 나누어진 것으로 생각한다.
 * 롤케이크를 공평하게 자르는 방법의 수를 return하라.
 *
 * 1 <= topping의 길이 <= 1000000
 * 1 <= topping의 원소 <= 10000
 */

class Solution {
    fun solution(topping: IntArray): Int {
        val leftTopping = IntArray(topping.size)
        val rightTopping = IntArray(topping.size)
        val leftToppingSet = mutableSetOf<Int>()
        val rightToppingSet = mutableSetOf<Int>()

        for (i in 0 until topping.size) {
            leftToppingSet.add(topping[i])
            leftTopping[i] = leftToppingSet.size
            rightToppingSet.add(topping[topping.size - 1 - i])
            rightTopping[topping.size - 1 - i] = rightToppingSet.size
        }

        var result = 0

        for (i in 0 until leftTopping.size - 1) {
            if (leftTopping[i] == rightTopping[i + 1]) {
                result++
            }
        }

        return result
    }
}

fun main(args: Array<String>) {
    val topping = intArrayOf(1, 2, 3, 1, 4)

    val result = Solution().solution(topping)

    println(result)
}
