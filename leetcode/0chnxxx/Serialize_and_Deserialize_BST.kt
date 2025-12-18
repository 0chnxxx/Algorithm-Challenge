/**
 * Serialization is converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on how your serialization/deserialization algorithm should work. You need to ensure that a binary search tree can be serialized to a string, and this string can be deserialized to the original tree structure.
 *
 * The encoded string should be as compact as possible.
 *
 * Constraints:
 * The number of nodes in the tree is in the range [0, 10^4].
 * 0 <= Node.val <= 10^4
 * The input tree is guaranteed to be a binary search tree.
 */

fun main() {
    val root = TreeNode(2)
    val left = TreeNode(1)
    val right = TreeNode(3)

    root.left = left
    root.right = right

    val serializer = Codec()
    val deserializer = Codec()

    val tree: String = serializer.serialize(root)

    val result = deserializer.deserialize(tree)

    println(result)
}

class TreeNode(
    var `val`: Int,
) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class Codec() {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(N)
    fun serialize(root: TreeNode?): String {
        val builder = StringBuilder()

        fun preorder(node: TreeNode?) {
            if (node == null) return

            builder.append(node.`val`).append(",")
            preorder(node.left)
            preorder(node.right)
        }

        preorder(root)

        if (builder.isNotEmpty()) {
            builder.setLength(builder.length - 1)
        }

        return builder.toString()
    }

    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(N)
    fun deserialize(data: String): TreeNode? {
        if (data.isEmpty()) return null

        val values = data.split(",").map { it.toInt() }
        var index = 0

        fun build(min: Int, max: Int): TreeNode? {
            if (index == values.size) return null

            val value = values[index]

            if (value < min || value > max) return null

            index++

            val node = TreeNode(value)
            node.left = build(min, value)
            node.right = build(value, max)

            return node
        }

        return build(Int.MIN_VALUE, Int.MAX_VALUE)
    }
}