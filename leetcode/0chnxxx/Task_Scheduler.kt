/**
 * You are given an array of CPU tasks, each labeled with a letter from A to Z, and a number n.
 * Each CPU interval can be idle or allow the completion of one task.
 * Tasks can be completed in any order, but there's a constraint: there has to be a gap of at least n intervals between two tasks with the same label.
 * Return the minimum number of CPU intervals required to complete all tasks.
 *
 * Constraints:
 * 1 <= tasks.length <= 10^4
 * tasks[i] is an uppercase English letter.
 * 0 <= n <= 100
 */

fun main() {
    val tasks = charArrayOf(
        'A',
        'A',
        'A',
        'B',
        'B',
        'B'
    )
    val n = 2

    val result = Solution().leastInterval(tasks, n)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun leastInterval(tasks: CharArray, n: Int): Int {
        val frequency = IntArray(26)

        for (task in tasks) {
            frequency[task - 'A']++
        }

        var maxFrequency = 0

        for (f in frequency) {
            maxFrequency = maxOf(maxFrequency, f)
        }

        var maxCount = 0

        for (f in frequency) {
            if (f == maxFrequency) {
                maxCount++
            }
        }

        val partCount = maxFrequency - 1
        val partLength = n + 1
        val minTime = partCount * partLength + maxCount

        return maxOf(tasks.size, minTime)
    }
}