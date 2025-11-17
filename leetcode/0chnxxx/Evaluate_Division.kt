/**
 * You are given an array of variable pairs equations and an array of real numbers values, where equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i].
 * Each Ai or Bi is a string that represents a single variable.
 *
 * You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where you must find the answer for Cj / Dj = ?.
 * Return the answers to all queries. If a single answer cannot be determined, return -1.0.
 *
 * Note: The input is always valid. You may assume that evaluating the queries will not result in division by zero and that there is no contradiction.
 * Note: The variables that do not occur in the list of equations are undefined, so the answer cannot be determined for them.
 *
 * Constraints:
 * 1 <= equations.length <= 20
 * equations[i].length == 2
 * 1 <= Ai.length, Bi.length <= 5
 * values.length == equations.length
 * 0.0 < values[i] <= 20.0
 * 1 <= queries.length <= 20
 * queries[i].length == 2
 * 1 <= Cj.length, Dj.length <= 5
 * Ai, Bi, Cj, Dj consist of lower case English letters and digits.
 */

fun main() {
    val equations = listOf(
        listOf("a", "b"),
        listOf("b", "c")
    )
    val values = doubleArrayOf(2.0, 3.0)
    val queries = listOf(
        listOf("a", "c"),
        listOf("b", "a"),
        listOf("a", "e"),
        listOf("a", "a"),
        listOf("x", "x")
    )

    val result = Solution().calcEquation(equations, values, queries)

    println(result.joinToString(", "))
}

class Solution {
    // 시간 복잡도 : O(E)
    // 공간 복잡도 : O(E + V)
    fun calcEquation(equations: List<List<String>>, values: DoubleArray, queries: List<List<String>>): DoubleArray {
        val graph = mutableMapOf<String, MutableMap<String, Double>>()
        val result = DoubleArray(queries.size)

        for (i in equations.indices) {
            val a = equations[i][0]
            val b = equations[i][1]
            val value = values[i]

            graph.putIfAbsent(a, mutableMapOf())
            graph.putIfAbsent(b, mutableMapOf())

            graph[a]!![b] = value
            graph[b]!![a] = 1.0 / value
        }

        fun dfs(
            graph: Map<String, Map<String, Double>>,
            current: String,
            target: String,
            visited: MutableSet<String>,
            d: Double
        ): Double {
            if (current == target) {
                return d
            }

            visited.add(current)

            for ((next, value) in graph[current]!!) {
                if (!visited.contains(next)) {
                    val result = dfs(graph, next, target, visited, d * value)

                    if (result != -1.0) {
                        return result
                    }
                }
            }

            return -1.0
        }

        for (i in queries.indices) {
            val start = queries[i][0]
            val end = queries[i][1]

            if (!graph.containsKey(start) || !graph.containsKey(end)) {
                result[i] = -1.0
            } else if (start == end) {
                result[i] = 1.0
            } else {
                result[i] = dfs(graph, start, end, mutableSetOf(), 1.0)
            }
        }

        return result
    }
}
