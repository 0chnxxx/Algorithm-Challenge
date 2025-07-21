/**
 * Implement the BSTIterator class that represents an iterator over the in-order traversal of a binary search tree (BST):
 * BSTIterator(TreeNode root) Initializes an object of the BSTIterator class.
 * The root of the BST is given as part of the constructor.
 * The pointer should be initialized to a non-existent number smaller than any element in the BST.
 * boolean hasNext() Returns true if there exists a number in the traversal to the right of the pointer, otherwise returns false.
 * int next() Moves the pointer to the right, then returns the number at the pointer.
 * Notice that by initializing the pointer to a non-existent smallest number, the first call to next() will return the smallest element in the BST.
 * You may assume that next() calls will always be valid.
 * That is, there will be at least a next number in the in-order traversal when next() is called.
 *
 * Constraints:
 * The number of nodes in the tree is in the range [1, 10^5].
 * 0 <= Node.val <= 10^6
 * At most 10^5 calls will be made to hasNext, and next.
 *
 * Follow up:
 * Could you implement next() and hasNext() to run in average O(1) time and use O(h) memory, where h is the height of the tree?
 */

fun main() {
    val root = TreeNode(7, TreeNode(3), TreeNode(15, TreeNode(9), TreeNode(20)))

    val bstIterator = BSTIterator(root)

    println(bstIterator.next())
    println(bstIterator.next())
    println(bstIterator.hasNext())
    println(bstIterator.next())
    println(bstIterator.hasNext())
    println(bstIterator.next())
    println(bstIterator.hasNext())
    println(bstIterator.next())
    println(bstIterator.hasNext())
}

class TreeNode(
    var `val`: Int,
    var left: TreeNode? = null,
    var right: TreeNode? = null
)

// 시간 복잡도 : O(1)
// 공간 복잡도 : O(H)
class BSTIterator(root: TreeNode?) {
    private val stack = ArrayDeque<TreeNode>()

    init {
        push(root)
    }

    private fun push(node: TreeNode?) {
        var current = node

        while (current != null) {
            stack.addLast(current)
            current = current.left
        }
    }

    fun next(): Int {
        val node = stack.removeLast()

        if (node.right != null) {
            push(node.right)
        }

        return node.`val`
    }

    fun hasNext(): Boolean {
        return stack.isNotEmpty()
    }
}
