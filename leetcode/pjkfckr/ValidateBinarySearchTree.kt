
class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class ValidateBinarySearchTree {

    fun isValidBST(root: TreeNode?): Boolean {
        return isValidBSTHelper(root, null, null)
    }

    private fun isValidBSTHelper(node: TreeNode?, min: Int?, max: Int?): Boolean {
        if (node == null) return true

        if ((min != null && node.`val` <= min) || (max != null && node.`val` >= max)) {
            return false
        }

        return isValidBSTHelper(node.left, min, node.`val`) && isValidBSTHelper(node.right, node.`val`, max)
    }
}

fun main() {
    val validate = ValidateBinarySearchTree()

}