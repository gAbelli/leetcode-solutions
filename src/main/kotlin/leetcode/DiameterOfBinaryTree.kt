package leetcode

import leetcode.datastructures.TreeNode
import kotlin.math.max

class DiameterOfBinaryTree {
    fun diameterOfBinaryTree(root: TreeNode?): Int {
        return helper(root).second
    }

    // Returns depth of tree and maximum diameter
    private fun helper(root: TreeNode?): Pair<Int, Int> {
        if (root == null) return 0 to 0
        val (leftDepth, leftDiameter) = helper(root.left)
        val (rightDepth, rightDiameter) = helper(root.right)
        return (1 + max(leftDepth, rightDepth)) to (max(max(leftDiameter, rightDiameter), leftDepth + rightDepth))
    }
}
