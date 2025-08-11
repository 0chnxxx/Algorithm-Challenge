import java.util.LinkedList

/**
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
 * You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return the ordering of courses you should take to finish all courses. If there are many valid answers, return any of them.
 * If it is impossible to finish all courses, return an empty array.
 *
 * Constraints:
 * 1 <= numCourses <= 2000
 * 0 <= prerequisites.length <= numCourses * (numCourses - 1)
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * ai != bi
 * All the pairs [ai, bi] are distinct.
 */

fun main() {
    val numCourses = 4
    val prerequisites = arrayOf(
        intArrayOf(1, 0),
        intArrayOf(2, 0),
        intArrayOf(3, 1),
        intArrayOf(3, 2)
    )

    val result = Solution().findOrder(numCourses, prerequisites)

    println(result.joinToString(", "))
}

class Solution {
    // 시간 복잡도 : O(V + E)
    // 공간 복잡도 : O(V + E)
    fun findOrder(numCourses: Int, prerequisites: Array<IntArray>): IntArray {
        val graph = Array(numCourses) { mutableListOf<Int>() }
        val indegree = IntArray(numCourses) { 0 }

        for ((a, b) in prerequisites) {
            graph[b].add(a)
            indegree[a]++
        }

        val queue = LinkedList<Int>()

        for (i in 0 until numCourses) {
            if (indegree[i] == 0) {
                queue.add(i)
            }
        }

        val order = mutableListOf<Int>()

        while (queue.isNotEmpty()) {
            val current = queue.poll()

            order.add(current)

            for (next in graph[current]) {
                indegree[next]--

                if (indegree[next] == 0) {
                    queue.add(next)
                }
            }
        }

        return if (order.size == numCourses) order.toIntArray() else intArrayOf()
    }
}