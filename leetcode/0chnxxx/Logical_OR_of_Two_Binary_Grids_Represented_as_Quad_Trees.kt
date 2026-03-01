/**
 * A Binary Matrix is a matrix in which all the elements are either 0 or 1.
 * Given quadTree1 and quadTree2. quadTree1 represents a n * n binary matrix and quadTree2 represents another n * n binary matrix.
 * Return a Quad-Tree representing the n * n binary matrix which is the result of logical bitwise OR of the two binary matrixes represented by quadTree1 and quadTree2.
 * Notice that you can assign the value of a node to True or False when isLeaf is False, and both are accepted in the answer.
 *
 * A Quad-Tree is a tree data structure in which each internal node has exactly four children. Besides, each node has two attributes:
 * val: True if the node represents a grid of 1's or False if the node represents a grid of 0's.
 * isLeaf: True if the node is leaf node on the tree or False if the node has the four children.
 *
 * class Node {
 *     public boolean val;
 *     public boolean isLeaf;
 *     public Node topLeft;
 *     public Node topRight;
 *     public Node bottomLeft;
 *     public Node bottomRight;
 * }
 *
 * We can construct a Quad-Tree from a two-dimensional area using the following steps:
 * If the current grid has the same value (i.e all 1's or all 0's) set isLeaf True and set val to the value of the grid and set the four children to Null and stop.
 * If the current grid has different values, set isLeaf to False and set val to any value and divide the current grid into four sub-grids as shown in the photo.
 * Recurse for each of the children with the proper sub-grid.
 * If you want to know more about the Quad-Tree, you can refer to the wiki.
 *
 * Quad-Tree format:
 * The input/output represents the serialized format of a Quad-Tree using level order traversal, where null signifies a path terminator where no node exists below.
 * It is very similar to the serialization of the binary tree. The only difference is that the node is represented as a list [isLeaf, val].
 * If the value of isLeaf or val is True we represent it as 1 in the list [isLeaf, val] and if the value of isLeaf or val is False we represent it as 0.
 *
 * Constraints:
 * quadTree1 and quadTree2 are both valid Quad-Trees each representing a n * n grid.
 * n == 2x where 0 <= x <= 9.
 */

class Node(
    var `val`: Boolean,
    var isLeaf: Boolean
) {
    var topLeft: Node? = null
    var topRight: Node? = null
    var bottomLeft: Node? = null
    var bottomRight: Node? = null
}

class Solution {
    // 시간 복잡도 : O(N^2)
    // 공간 복잡도 : O(N^2)
    fun intersect(quadTree1: Node?, quadTree2: Node?): Node? {
        if (quadTree1 == null) return quadTree2
        if (quadTree2 == null) return quadTree1

        if (quadTree1.isLeaf) {
            return if (quadTree1.`val`) {
                Node(true, true)
            } else {
                quadTree2
            }
        }

        if (quadTree2.isLeaf) {
            return if (quadTree2.`val`) {
                Node(true, true)
            } else {
                quadTree1
            }
        }

        val topLeft = intersect(quadTree1.topLeft, quadTree2.topLeft)
        val topRight = intersect(quadTree1.topRight, quadTree2.topRight)
        val bottomLeft = intersect(quadTree1.bottomLeft, quadTree2.bottomLeft)
        val bottomRight = intersect(quadTree1.bottomRight, quadTree2.bottomRight)

        if (topLeft!!.isLeaf &&
            topRight!!.isLeaf &&
            bottomLeft!!.isLeaf &&
            bottomRight!!.isLeaf &&
            topLeft.`val` == topRight.`val` &&
            topRight.`val` == bottomLeft.`val` &&
            bottomLeft.`val` == bottomRight.`val`
        ) {
            return Node(topLeft.`val`, true)
        }

        val node = Node(false, false)

        node.topLeft = topLeft
        node.topRight = topRight
        node.bottomLeft = bottomLeft
        node.bottomRight = bottomRight

        return node
    }
}
