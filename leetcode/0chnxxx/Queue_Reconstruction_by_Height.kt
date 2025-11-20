/**
 * You are given an array of people, people, which are the attributes of some people in a queue (not necessarily in order).
 * Each people[i] = [hi, ki] represents the ith person of height hi with exactly ki other people in front who have a height greater than or equal to hi.
 * Reconstruct and return the queue that is represented by the input array people.
 * The returned queue should be formatted as an array queue, where queue[j] = [hj, kj] is the attributes of the jth person in the queue (queue[0] is the person at the front of the queue).
 *
 * Constraints:
 * 1 <= people.length <= 2000
 * 0 <= hi <= 10^6
 * 0 <= ki < people.length
 * It is guaranteed that the queue can be reconstructed.
 */

fun main() {
    val people = arrayOf(
        intArrayOf(7, 0),
        intArrayOf(4, 4),
        intArrayOf(7, 1),
        intArrayOf(5, 0),
        intArrayOf(6, 1),
        intArrayOf(5, 2)
    )

    val result = Solution().reconstructQueue(people)

    println(result.joinToString(", "))
}

class Solution {
    // 시간 복잡도 : O(n log n)
    // 공간 복잡도 : O(N^2)
    fun reconstructQueue(people: Array<IntArray>): Array<IntArray> {
        people.sortWith(compareBy<IntArray> { -it[0] }.thenBy { it[1] })

        val queue = ArrayList<IntArray>()

        for (p in people) {
            queue.add(p[1], p)
        }

        return queue.toTypedArray()
    }
}