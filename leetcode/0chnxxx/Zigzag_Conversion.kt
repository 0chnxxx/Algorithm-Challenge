/**
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
 *
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 *
 * Write the code that will take a string and make this conversion given a number of rows:
 *
 * string convert(string s, int numRows);
 *
 * 1 <= s.length <= 1000
 * s consists of English letters (lower-case and upper-case), ',' and '.'
 * 1 <= numRows <= 1000
 */

fun main() {
    val s = "PAYPALISHIRING"
    val numRows = 3
    
    val solution = Solution().convert(s, numRows)
    
    println(solution)
}

class Solution {
    // O(N) 풀이
    fun convert(s: String, numRows: Int): String {
        // 1줄짜리 문자열은 그대로 반환
        if (numRows == 1 || s.length <= numRows) return s

        // 각 층마다 StringBuilder를 갖는 배열
        val rows = Array(minOf(numRows, s.length)) { StringBuilder() }
        // 현재 층
        var currentRow = 0
        // 진행 방향 (-1 : 위, 1 : 아래)
        var direction = -1

        // 문자 순회
        for (char in s) {
            // 현재 층에 현재 문자 추가
            rows[currentRow].append(char)

            // 시작지점이거나 끝지점인 경우 방향 역전
            if (currentRow == 0 || currentRow == numRows - 1) {
                direction *= -1
            }

            // 진행
            currentRow += direction
        }

        // 각 층의 문자를 합쳐서 반환
        return rows.joinToString("")
    }
}
