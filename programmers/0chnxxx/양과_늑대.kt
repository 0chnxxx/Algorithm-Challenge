/**
 * 2진 트리 모양 초원의 각 노드에 늑대와 양이 한 마리씩 놓여 있습니다.
 * 이 초원의 루트 노드에서 출발하여 각 노드를 돌아다니며 양을 모으려 합니다.
 * 각 노드를 방문할 때 마다 해당 노드에 있던 양과 늑대가 당신을 따라오게 됩니다.
 * 이때, 늑대는 양을 잡아먹을 기회를 노리고 있으며, 당신이 모은 양의 수보다 늑대의 수가 같거나 더 많아지면 바로 모든 양을 잡아먹어 버립니다.
 * 당신은 중간에 양이 늑대에게 잡아먹히지 않도록 하면서 최대한 많은 수의 양을 모아서 다시 루트 노드로 돌아오려 합니다.
 *
 * 각 노드에 있는 양 또는 늑대에 대한 정보가 담긴 배열 info
 * 2진 트리의 각 노드들의 연결 관계를 담은 2차원 배열 endges
 *
 * 문제에 제시된 조건에 따라 각 노드를 방문하면서 모을 수 있는 양은 최대 몇 마리인지 return하도록 solution 함수를 완성해주세요.
 *
 * 2 <= info의 길이 <= 17
 * info[i]는 i번 노드에 있는 양 또는 늑대를 나타냅니다.
 * info의 원소는 0 또는 1 입니다.
 * 0은 양, 1은 늑대를 의미합니다.
 * info[0]의 값은 항상 0입니다. 즉, 0번 노드에는 항상 양이 있습니다.
 * edges의 세로 길이 = info의 길이 - 1
 * edges의 가로 길이 = 2
 * edges의 각 행은 [부모 노드 번호, 자식 노드 번호] 형태입니다.
 */

fun main() {
    val info = intArrayOf(0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1)
    val edges = arrayOf(
        intArrayOf(0, 1),
        intArrayOf(1, 2),
        intArrayOf(1, 4),
        intArrayOf(0, 8),
        intArrayOf(8, 7),
        intArrayOf(9, 10),
        intArrayOf(9, 11),
        intArrayOf(4, 3),
        intArrayOf(6, 5),
        intArrayOf(4, 6),
        intArrayOf(8, 9)
    )

    val solution = Solution().solution(info, edges)

    println(solution)
}

class Solution {
    data class Node(
        val number: Int,
        val animal: Int,
        var left: Node? = null,
        var right: Node? = null
    )

    fun solution(info: IntArray, edges: Array<IntArray>): Int {
        val tree = Array(info.size) { i -> Node(i, info[i]) }

        for ((parent, child) in edges) {
            val parentNode = tree[parent]
            val childNode = tree[child]

            if (parentNode.left == null) {
                parentNode.left = childNode
            } else if (parentNode.right == null) {
                parentNode.right = childNode
            }
        }

        var maxSheep = 0

        fun dfs(currentNode: Node, sheep: Int, wolves: Int, candidates: MutableList<Node>) {
            val newSheep = sheep + if (currentNode.animal == 0) 1 else 0
            val newWolves = wolves + if (currentNode.animal == 1) 1 else 0

            if (newSheep <= newWolves) {
                return
            }

            maxSheep = maxOf(maxSheep, newSheep)

            val nextNodes = candidates.toMutableList()

            nextNodes.remove(currentNode)

            currentNode.left?.let { nextNodes.add(it) }
            currentNode.right?.let { nextNodes.add(it) }

            for (nextNode in nextNodes) {
                dfs(nextNode, newSheep, newWolves, nextNodes)
            }
        }

        dfs(tree[0], 0, 0, mutableListOf(tree[0]))

        return maxSheep
    }
}
