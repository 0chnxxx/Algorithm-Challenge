/**
 * 마트는 일정한 금액을 지불하면 10일 동안 회원 자격을 부여한다.
 * 회원을 대상으로 매일 한 가지 제품을 할인하는 행사를 한다.
 * 할인하는 제품은 하루에 하나씩만 구매할 수 있다.
 *
 * 자신이 원하는 제품과 수량이 할인하는 날짜와 10일 연속으로 일치할 경우에 맞춰서 회원가입을 하려 한다.
 *
 * 원하는 제품을 나타내는 문자열 배열 want
 * 원하는 제품의 수량을 나타내는 정수 배열 number
 * 할인하는 제품을 나타내는 문자열 배열 discount
 *
 * 원하는 제품을 모두 할인 받을 수 있는 회원등록 날짜의 총 일수를 return하라.
 * 가능한 날이 없으면 0을 return
 *
 * 1 <= want의 길이 = number의 길이 <= 10
 * 1 <= number의 원소 <= 10
 * number 원소의 합은 10
 *
 * 10 <= discount의 길이 <= 100000
 * want와 discount의 원소들은 알파벳 소문자로 이루어진 문자열
 * 1 <= want의 원소의 길이, discount의 원소의 길이 <= 12
 */

class Solution {
    fun solution(want: Array<String>, number: IntArray, discount: Array<String>): Int {
        val result = mutableListOf<Int>()
        val discountByDay = mutableMapOf<Int, Map<String, List<String>>>()

        for (i in 0..discount.size - 10) {
            val slicingDiscount = discount.slice(i..i + 9)
            val groupedDiscount = slicingDiscount.groupBy { it }

            discountByDay[i + 1] = groupedDiscount
        }

        for (day in discountByDay) {
            var isAllContain = true

            want.forEachIndexed { index, item ->
                val count = number[index]

                if (day.value[item] == null || day.value[item]!!.size != count) {
                    isAllContain = false
                }
            }

            if (isAllContain) {
                result.add(day.key)
            }
        }

        return result.size
    }
}

fun main(args: Array<String>) {
    val want = arrayOf("banana", "apple", "rice", "pork", "pot")
    val number = intArrayOf(3, 2, 2, 2, 1)
    val discount = arrayOf("chicken", "apple", "apple", "banana", "rice", "apple", "pork", "banana", "pork", "rice", "pot", "banana", "apple", "banana")

    val result = Solution().solution(want, number, discount)

    println(result)
}
