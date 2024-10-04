package leetcode

import leetcode.datastructures.TreeNode
import kotlin.math.max

class CountGoodNodesInBinaryTree {
    fun goodNodes(root: TreeNode?): Int {
        return helper(root, Int.MIN_VALUE)
    }

    private fun helper(root: TreeNode?, maxSoFar: Int): Int {
        if (root == null) return 0
        val rootCount = if (root.`val` >= maxSoFar) 1 else 0
        val maxSoFar = max(maxSoFar, root.`val`)
        return rootCount + helper(root.left, maxSoFar) + helper(root.right, maxSoFar)
    }
}
