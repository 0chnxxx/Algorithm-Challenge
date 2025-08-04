/**
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
 * You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return true if you can finish all courses.
 * Otherwise, return false.
 *
 * Constraints:
 * 1 <= numCourses <= 2000
 * 0 <= prerequisites.length <= 5000
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * All the pairs prerequisites[i] are unique.
 */

fun main() {
    val numCourses = 2
    val prerequisites = arrayOf(
        intArrayOf(0, 1)
    )

    val result = Solution().canFinish(numCourses, prerequisites)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(V + E)
    // 공간 복잡도 : O(V + E)
    fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
        val graph = Array<MutableList<Int>>(numCourses) { mutableListOf() }
        val indegree = IntArray(numCourses)

        // 그래프 구성 및 진입 차수 계산
        for ((a, b) in prerequisites) {
            graph[b].add(a)
            indegree[a]++
        }

        val queue = ArrayDeque<Int>()

        // 진입 차수가 0인 노드부터 탐색
        for (i in 0 until numCourses) {
            if (indegree[i] == 0) {
                queue.add(i)
            }
        }

        // 수강 가능한 코스 수
        var count = 0

        // 진입 차수를 역으로 거슬러 탐색하며 수강하는 코스의 수 구하기
        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()

            count++

            for (next in graph[current]) {
                indegree[next]--

                if (indegree[next] == 0) {
                    queue.add(next)
                }
            }
        }

        // 총 코스의 수와 수강한 코스의 수가 동일하다면 정상적으로 구성
        return count == numCourses
    }
}