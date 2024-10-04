package leetcode

import leetcode.datastructures.TreeNode
import kotlin.math.abs
import kotlin.math.max

class BalancedBinaryTree {
    fun isBalanced(root: TreeNode?): Boolean {
        if (root == null) return true
        val leftDepth = depth(root.left)
        val rightDepth = depth(root.right)
        return (abs(leftDepth - rightDepth) <= 1) && isBalanced(root.left) && isBalanced(root.right)
    }

    fun depth(root: TreeNode?): Int {
        if (root == null) return 0
        return 1 + max(depth(root.left), depth(root.right))
    }
}
