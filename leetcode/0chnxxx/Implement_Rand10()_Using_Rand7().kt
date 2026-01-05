/**
 * Given the API rand7() that generates a uniform random integer in the range [1, 7], write a function rand10() that generates a uniform random integer in the range [1, 10]. You can only call the API rand7(), and you shouldn't call any other API. Please do not use a language's built-in random API.
 *
 * Each test case will have one internal argument n, the number of times that your implemented function rand10() will be called while testing. Note that this is not an argument passed to rand10().
 *
 * Constraints:
 * 1 <= n <= 10^5
 *
 * Follow up:
 * What is the expected value for the number of calls to rand7() function?
 * Could you minimize the number of calls to rand7()?
 */

fun main() {
    val n = 1

    val list = mutableListOf<Int>()

    repeat(n) {
        val result = Solution().rand10()
        list.add(result)
    }

    println(list)
}

abstract class SolBase {
    fun rand7(): Int {
        return (1..7).random()
    }
}

class Solution : SolBase() {
    // 시간 복잡도 : O(1)
    // 공간 복잡도 : O(1)
    fun rand10(): Int {
        while (true) {
            // rand7()은 균등 -> rand7() * rand7() 은 49가지 경우 균등
            val num = (rand7() - 1) * 7 + rand7()

            // 41 ~ 49 는 버리고 다시 뽑음
            if (num <= 40) {
                return (num - 1) % 10 + 1
            }
        }
    }
}