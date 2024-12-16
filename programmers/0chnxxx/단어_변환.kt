import java.util.*
import kotlin.collections.ArrayDeque

/**
 * 두 개의 단어 begin, target과 단어의 집합 words가 있습니다.
 * 아래와 같은 규칙을 이용하여 begin에서 target으로 변환하는 가장 짧은 변환 과정을 찾으려고 합니다.
 *
 * 1. 한 번에 한 개의 알파벳만 바꿀 수 있습니다.
 * 2. words에 있는 단어로만 변환할 수 있습니다.
 *
 * 예를 들어 begin이 "hit", target이 "cog", words가 ["hot", "dot", "dog", "lot", "log", "cog"] 라면
 * hit -> hot -> dot -> dog -> cog 와 같이 4단계를 거쳐 변환할 수 있습니다.
 *
 * 두 개의 단어 begin, target과 단어의 집합 words가 매개변수로 주어질 때, 최소 몇 단계의 과정을 거쳐 begin을 target으로 변환할 수 있는지 return 하도록 solution 함수를 작성해주세요.
 * 변환할 수 없는 경우에는 0을 return 합니다.
 *
 * 3 <= words의 길이 <= 50
 * 3 <= 단어의 길이 <= 10
 * 모든 단어의 길이는 같다.
 * 중복되는 단어는 없다.
 */

fun main() {
    val begin = "hit"
    val target = "cog"
    val words = arrayOf("hat","hot","dat","dot","cat","dog","cot","cog")

    val result = Solution().solution(begin, target, words)

    println(result)
}

class Solution {
    fun solution(begin: String, target: String, words: Array<String>): Int {
        if (target !in words) {
            return 0
        }

        val queue = ArrayDeque<Pair<String, Int>>()
        val visited = words.associateWith { false }.toMutableMap()

        queue.add(begin to 1)
        visited[begin] = true

        while (queue.isNotEmpty()) {
            val (word, step) = queue.removeFirst()

            for (i in word.indices) {
                for (c in 'a'..'z') {
                    val newWord = word.replaceRange(i, i + 1, c.toString())

                    if (newWord == target) {
                        return step
                    }

                    if (newWord in words && !visited[newWord]!!) {
                        queue.add(newWord to (step + 1))
                        visited[newWord] = true
                    }
                }
            }
        }

        return 0
    }
}
