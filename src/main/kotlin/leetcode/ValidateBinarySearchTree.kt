package leetcode

import leetcode.datastructures.TreeNode
import kotlin.math.max
import kotlin.math.min

class ValidateBinarySearchTree {
    fun isValidBST(root: TreeNode?): Boolean {
        return helper(root, Long.MIN_VALUE, Long.MAX_VALUE)
    }

    fun helper(root: TreeNode?, minAllowed: Long, maxAllowed: Long): Boolean {
        if (root == null) return true
        return root.`val` > minAllowed
                && root.`val` < maxAllowed
                && helper(root.left, minAllowed, min(maxAllowed, root.`val`.toLong()))
                && helper(root.right, max(minAllowed, root.`val`.toLong()), maxAllowed)
    }
}
