/**
 * A web developer needs to know how to design a web page's size.
 * So, given a specific rectangular web page’s area, your job by now is to design a rectangular web page, whose length L and width W satisfy the following requirements:
 * 1. The area of the rectangular web page you designed must equal to the given target area.
 * 2. The width W should not be larger than the length L, which means L >= W.
 * 3. The difference between length L and width W should be as small as possible.
 * Return an array [L, W] where L and W are the length and width of the web page you designed in sequence.
 *
 * Constraints:
 *
 * 1 <= area <= 10^7
 */

fun main() {
    val area = 4

    val result = Solution().constructRectangle(area)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(root(area))
    // 공간 복잡도 : O(1)
    fun constructRectangle(area: Int): IntArray {
        var w = Math.sqrt(area.toDouble()).toInt()

        while (w > 0) {
            if (area % w == 0) {
                val l = area / w
                return intArrayOf(l, w)
            }

            w--
        }

        return intArrayOf(area, 1)
    }
}