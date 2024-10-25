import java.util.*

/**
 * 다음과 같은 규칙으로 카드에 적힌 단어들을 사용해 원하는 순서의 단어 배열을 만들 수 있는지 알고 싶다.
 * 1. 원하는 카드 뭉치에서 카드를 순서대로 한 장씩 사용한다.
 * 2. 한 번 사용한 카드는 다시 사용할 수 없다.
 * 3. 카드를 사용하지 않고 다음 카드로 넘어갈 수 없다.
 * 4. 기존에 주어진 카드 뭉치의 단어 순서는 바꿀 수 없다.
 *
 * 문자열로 이루어진 배열 cards1, cards2 와 원하는 단어 배열 goal이 매개변수로 주어진다.
 * 원하는 단어 배열을 만들었다면 "Yes", 만들 수 없다면 "No"를 return하라.
 *
 * 1 <= cards1, cards2의 길이 <= 10
 * 1 <= cards1, cards2의 원소의 길이 <= 10
 * 2 <= goal의 길이 <= cards1 + cards2 의 길이
 * 1 <= goal의 원소의 길이 <= 10
 */

class Solution {
    fun solution(cards1: Array<String>, cards2: Array<String>, goal: Array<String>): String {
        val queue1 = LinkedList(cards1.toList())
        val queue2 = LinkedList(cards2.toList())

        var result = "Yes"

        for (word in goal) {
            val word1 = queue1.firstOrNull()
            val word2 = queue2.firstOrNull()

            if (result == "No") {
                break
            }

            if (word1 != null && word == word1) {
                queue1.pop()
                continue
            }

            if (word2 != null && word == word2) {
                queue2.pop()
                continue
            }

            result = "No"
        }

        return result
    }
}

fun main(args: Array<String>) {
    println(Solution().solution(
            arrayOf("i", "drink", "water"),
            arrayOf("want", "to"),
            arrayOf("i", "want", "to", "drink", "water")
        )
    )
    println(Solution().solution(
            arrayOf("i", "water", "drink"),
            arrayOf("want", "to"),
            arrayOf("i", "want", "to", "drink", "water")
        )
    )
}
