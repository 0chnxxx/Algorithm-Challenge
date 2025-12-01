/**
 * Given a string s containing an out-of-order English representation of digits 0-9, return the digits in ascending order.
 *
 * Constraints:
 * 1 <= s.length <= 10^5
 * s[i] is one of the characters ["e","g","f","i","h","o","n","s","r","u","t","w","v","x","z"].
 * s is guaranteed to be valid.
 */

fun main() {
    val s = "owoztneoer"

    val result = Solution().originalDigits(s)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun originalDigits(s: String): String {
        val count = IntArray(26)

        // 문자 개수 세기
        for (c in s) {
            count[c - 'a']++
        }

        val numCount = IntArray(10)

        // 0, 2, 4, 6, 8 은 고유 문자 하나로 찾을 수 있어서 먼저 제거
        numCount[0] = count['z' - 'a'] // zero
        numCount[2] = count['w' - 'a'] // two
        numCount[4] = count['u' - 'a'] // four
        numCount[6] = count['x' - 'a'] // six
        numCount[8] = count['g' - 'a'] // eight

        // 3, 5, 7, 1, 9 는 앞선 고유 문자를 제거하고나면 또 다시 고유 문자 하나로 찾을 수 있어서 추가 제거
        numCount[3] = count['h' - 'a'] - numCount[8] // three
        numCount[5] = count['f' - 'a'] - numCount[4] // five
        numCount[7] = count['s' - 'a'] - numCount[6] // seven
        numCount[1] = count['o' - 'a'] - numCount[0] - numCount[2] - numCount[4] // one
        numCount[9] = count['i' - 'a'] - numCount[5] - numCount[6] - numCount[8] // nine

        // 결과 반환
        val sb = StringBuilder()

        for (i in 0..9) {
            repeat(numCount[i]) {
                sb.append(i)
            }
        }

        return sb.toString()
    }
}