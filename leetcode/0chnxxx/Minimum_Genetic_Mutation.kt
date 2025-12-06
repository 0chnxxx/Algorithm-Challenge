/**
 * A gene string can be represented by an 8-character long string, with choices from 'A', 'C', 'G', and 'T'.
 * Suppose we need to investigate a mutation from a gene string startGene to a gene string endGene where one mutation is defined as one single character changed in the gene string.
 * For example, "AACCGGTT" --> "AACCGGTA" is one mutation.
 * There is also a gene bank bank that records all the valid gene mutations.
 * A gene must be in bank to make it a valid gene string.
 * Given the two gene strings startGene and endGene and the gene bank bank, return the minimum number of mutations needed to mutate from startGene to endGene.
 * If there is no such a mutation, return -1.
 * Note that the starting point is assumed to be valid, so it might not be included in the bank.
 *
 * Constraints:
 * 0 <= bank.length <= 10
 * startGene.length == endGene.length == bank[i].length == 8
 * startGene, endGene, and bank[i] consist of only the characters ['A', 'C', 'G', 'T'].
 */

fun main() {
    val startGene = "AACCGGTT"
    val endGene = "AACCGGTA"
    val bank = arrayOf("AACCGGTA")

    val result = Solution().minMutation(startGene, endGene, bank)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N * L)
    // 공간 복잡도 : O(N * L)
    fun minMutation(startGene: String, endGene: String, bank: Array<String>): Int {
        if (!bank.contains(endGene)) return -1

        val queue = ArrayDeque<String>()
        val visited = mutableSetOf<String>()
        val genes = charArrayOf('A', 'C', 'G', 'T')

        var mutation = 0

        queue.add(startGene)

        while (queue.isNotEmpty()) {
            val size = queue.size

            repeat(size) {
                val current = queue.removeFirst()

                if (current == endGene) return mutation

                for (i in current.indices) {
                    for (c in genes) {
                        val next = current.toCharArray()

                        next[i] = c

                        val nextGene = String(next)

                        if (nextGene in bank && nextGene !in visited) {
                            visited.add(nextGene)
                            queue.add(nextGene)
                        }
                    }
                }
            }

            mutation++
        }

        return -1
    }
}
