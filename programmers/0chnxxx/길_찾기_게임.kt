/**
 * 전무로 승진한 라이언은 기분이 너무 좋아 프렌즈를 이끌고 특별 휴가를 가기로 했다.
 * 내친김에 여행 계획까지 구상하던 라이언은 재미있는 게임을 생각해냈고 역시 전무로 승진할만한 인재라고 스스로에게 감탄했다.
 *
 * 라이언이 구상한 게임은, 카카오 프렌즈를 두 팀으로 나누고, 각 팀이 같은 곳을 다른 순서로 방문하도록 해서 먼저 순회를 마친 팀이 승리하는 것이다.
 *
 * 그냥 지도를 주고 게임을 시작하면 재미가 덜해지므로, 라이언은 방문할 곳의 2차원 좌표 값을 구하고 각 장소를 이진트리의 노드가 되도록 구성한 후, 순회 방법을 힌트로 주어 각 팀이 스스로 경로를 찾도록 할 계획이다.
 *
 * 라이언은 아래와 같은 특별한 규칙으로 트리 노드들을 구성한다.
 * - 트리를 구성하는 모든 노드의 x, y 좌표 값은 정수이다.
 * - 모든 노드는 서로 다른 x값을 가진다.
 * - 같은 레벨에 있는 노드는 같은 y 좌표를 가진다.
 * - 자식 노드의 y 값은 항상 부모 노드보다 작다.
 * - 임의의 노드 V의 왼쪽 서브 트리에 있는 모든 노드의 x값은 V의 x값보다 작다.
 * - 임의의 노드 V의 오른쪽 서브 트리에 있는 모든 노드의 x값은 V의 x값보다 크다.
 *
 * 노드의 수가 예시처럼 적다면 쉽게 해결할 수 있겠지만, 예상대로 라이언은 그렇게 할 생각이 전혀 없었다.
 * 곤경에 빠진 카카오 프렌즈를 위해 이진트리를 구성하는 노드들의 좌표가 담긴 배열 nodeinfo가 매개변수로 주어질 때,
 * 노드들로 구성된 이진트리를 전위 순회, 후위 순회한 결과를 2차원 배열에 순서대로 담아 return하도록 solution 함수를 완성하자.
 *
 * nodeinfo는 이진트리를 구성하는 각 노드의 좌표가 1번 노드부터 순서대로 들어있는 2차원 배열이다.
 * 1 <= nodeinfo의 길이 <= 10000
 * nodeinfo[i]는 i + 1번 노드의 좌표이며 [x축 좌표, y축 좌표] 순으로 들어있다.
 * 모든 노드의 좌표 값은 0 이상 100000 이하인 정수이다.
 * 트리의 깊이가 1000 이하인 경우만 입력으로 주어진다.
 */

fun main() {
    val nodeinfo = arrayOf(
        intArrayOf(5, 3),
        intArrayOf(11, 5),
        intArrayOf(13, 3),
        intArrayOf(3, 5),
        intArrayOf(6, 1),
        intArrayOf(1, 3),
        intArrayOf(8, 6),
        intArrayOf(7, 2),
        intArrayOf(2, 2)
    )

    val solution = Solution().solution(nodeinfo)

    println(solution)
}

class Solution {
    data class Node(
        val value: Int,
        val x: Int,
        val y: Int,
        var left: Node? = null,
        var right: Node? = null
    )

    class Tree {
        var root: Node? = null

        fun buildTree(nodes: List<Node>) {
            val sortedNodes = nodes.sortedWith(compareByDescending<Node> { it.y }.thenBy { it.x })

            root = sortedNodes[0]

            for (i in 1 until sortedNodes.size) {
                insert(root, sortedNodes[i])
            }
        }

        private fun insert(parent: Node?, child: Node) {
            if (parent == null) {
                return
            }

            if (child.x < parent.x) {
                if (parent.left == null) {
                    parent.left = child
                } else {
                    insert(parent.left, child)
                }
            } else {
                if (parent.right == null) {
                    parent.right = child
                } else {
                    insert(parent.right, child)
                }
            }
        }

        fun preOrderTraversal(root: Node?, result: MutableList<Int>): IntArray {
            if (root != null) {
                result.add(root.value)
                preOrderTraversal(root.left, result)
                preOrderTraversal(root.right, result)
            }

            return result.toIntArray()
        }

        fun postOrderTraversal(root: Node?, result: MutableList<Int>): IntArray {
            if (root != null) {
                postOrderTraversal(root.left, result)
                postOrderTraversal(root.right, result)
                result.add(root.value)
            }

            return result.toIntArray()
        }
    }


    fun solution(nodeinfo: Array<IntArray>): Array<IntArray> {
        val tree = Tree()
        val nodes = nodeinfo.mapIndexed { index, it -> Node(index + 1, it[0], it[1]) }.toList()

        tree.buildTree(nodes)

        return arrayOf(
            tree.preOrderTraversal(tree.root, mutableListOf()),
            tree.postOrderTraversal(tree.root, mutableListOf())
        )
    }
}
