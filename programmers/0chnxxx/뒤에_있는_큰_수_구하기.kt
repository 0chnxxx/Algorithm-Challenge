/**
 * 정수로 이루어진 배열 numbers가 있다.
 * 배열의 각 원소들에 대해 자신보다 뒤에 있는 숫자 중에서 자신보다 크면서 가장 가까이에 있는 수를 뒷 큰수라고 한다.
 * 모든 원소에 대한 뒷 큰수들을 차례로 담은 배열을 return하라.
 * 단, 뒷 큰수가 존재하지 않는 원소는 -1을 담는다.
 *
 * 4 <= numbers의 길이 <= 1000000
 * 1 <= number의 원소 <= 1000000
 */

class Solution {
    fun solution(numbers: IntArray): IntArray {
        val result = IntArray(numbers.size) { -1 }
        val stack = mutableListOf<Int>()

        // 시간복잡도 O(n^2)로 시간 초과
//        for (i in 0 until numbers.size) {
//            val number = numbers[i]
//            var backNumber = -1
//
//            for (j in i + 1 until numbers.size) {
//                val nextNumber = numbers[j]
//
//                if (number < nextNumber) {
//                    backNumber = nextNumber
//                    break
//                }
//            }
//
//            result[i] = backNumber
//        }

        for (i in 0 until numbers.size) {
            while (stack.isNotEmpty() && numbers[stack.last()] < numbers[i]) {
                val index = stack.removeAt(stack.size - 1)
                result[index] = numbers[i]
            }

            stack.add(i)
        }

        return result
    }
}

fun main(args: Array<String>) {
    val numbers = intArrayOf(9, 1, 5, 3, 6, 2)

    val result = Solution().solution(numbers)

    println(result.joinToString(" "))
}
