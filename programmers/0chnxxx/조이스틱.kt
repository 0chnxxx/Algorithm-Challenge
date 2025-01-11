/**
 * 조이스틱으로 알파벳 이름을 완성하세요.
 * 맨 처음엔 A로만 이루어져 있습니다.
 * 완성해야 하는 이름이 세 글자면 AAA, 네 글자면 AAAA
 *
 * 조이스틱을 각 방향으로 움직이면 아래와 같습니다.
 *
 * 위 - 다음 알파벳
 * 아래 - 이전 알파벳 (A에서 아래쪽으로 이동하면 Z로)
 * 왼쪽 - 커서를 왼쪽으로 이동 (첫 번째 위치에서 왼쪽으로 이동하면 마지막 문자에 커서)
 * 오른쪽 - 커서를 오른쪽으로 이동 (마지막 위치에서 오른쪽으로 이동하면 첫 번째 문자에 커서)
 *
 * 만들고자 하는 이름 name이 매개변수로 주어질 때, 이름에 대해 조이스틱 조작 횟수의 최솟값을 return 하도록 solution 함수를 만드세요.
 *
 * name은 알파벳 대문자로만 이루어져 있습니다.
 * name의 길이는 1 이상 20 이하입니다.
 */

fun main() {
    val name = "JAN"

    val solution = Solution().solution(name)

    println(solution)
}

class Solution {
    fun solution(name: String): Int {
        val n = name.length
        var count = 0
        var move = n - 1

        for (i in name.indices) {
            // 상하 이동 최적화
            val up = name[i].code - 'A'.code
            val down = 'Z'.code - name[i].code + 1

            count += minOf(up, down)

            // 좌우 이동 최적화
            var next = i + 1

            while (next < n && name[next] == 'A') {
                next++
            }

            if (next - i > 1) {
                val distanceToLeftEnd = i * 2 + n - next
                val distanceToRightEnd = (n - next) * 2 + i

                move = minOf(move, distanceToLeftEnd)
                move = minOf(move, distanceToRightEnd)
            }
        }

        // 상하로 알파벳을 바꾸는 횟수와 좌우로 자리를 이동하는 횟수를 더한 총 횟수를 반환
        return count + move
    }
}
