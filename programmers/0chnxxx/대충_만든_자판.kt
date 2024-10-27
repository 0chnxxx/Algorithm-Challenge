import java.util.*
import kotlin.math.min

/**
 * 키 하나에 여러 문자가 할당된 경우, 동일한 키를 연속해서 빠르게 누르면 할당된 순서대로 문자가 바뀐다.
 * 이 휴대폰 자판은 키의 개수가 1개부터 최대 100개까지 있을 수 있다.
 * 특정 키를 눌렀을 때 입력되는 문자들도 무작위로 배열되어 있다.
 * 같은 문자가 자판 전체에 여러 번 할당된 경우도 있다.
 * 키 하나에 같은 문자가 여러 번 할당된 경우도 있다.
 * 아예 할당되지 않은 경우도 있다.
 *
 * 특정 문자열을 작성할 때 키를 최소 몇 번 눌러야 그 문자열을 작성할 수 있는지 확인하라.
 * 1번 키부터 차례대로 할당된 문자들이 순서대로 담긴 문자열 배열 keymap
 * 입력하려는 문자들이 담긴 문자열 배열 targets
 *
 * 1 <= keymap의 길이 <= 100
 * 1 <= keymap의 원소의 길이 <= 100
 * 1 <= targets의 길이 <= 100
 * 1 <= targets의 원소의 길이 <= 100
 */

class Solution {
    fun solution(keymap: Array<String>, targets: Array<String>): IntArray {
        val result = IntArray(targets.size) { -1 }

        for (i in 0 until targets.size) {
            val target = targets[i]
            var sumIndex = 0

            for (j in 0 until target.length) {
                val char = target[j]
                var minIndex = Int.MAX_VALUE

                for (key in keymap) {
                    val index = key.indexOf(char)

                    if (index != -1) {
                        minIndex = Math.min(minIndex, index + 1)
                    }
                }

                if (minIndex == Int.MAX_VALUE) {
                    sumIndex = -1
                    break
                } else {
                    sumIndex += minIndex
                }
            }

            if (sumIndex != 0) {
                result[i] = sumIndex
            }
        }

        return result
    }
}

fun main(args: Array<String>) {
    println(Solution().solution(
        arrayOf("ABACD", "BCEFD"),
        arrayOf("ABCD", "AABB")
    ).joinToString(", "))
    println(Solution().solution(
        arrayOf("AA"),
        arrayOf("B")
    ).joinToString(", "))
    println(Solution().solution(
        arrayOf("AGZ", "BSSS"),
        arrayOf("ASA", "BGZ")
    ).joinToString(", "))
    println(Solution().solution(
        arrayOf("ABC"),
        arrayOf("DA")
    ).joinToString(", "))
}
