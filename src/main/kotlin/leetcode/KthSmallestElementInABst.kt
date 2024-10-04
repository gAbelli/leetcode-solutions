package leetcode

import leetcode.datastructures.TreeNode

class KthSmallestElementInABst {
    fun kthSmallest(root: TreeNode?, k: Int): Int {
        return helper(root, k).second!!
    }

    fun helper(root: TreeNode?, remaining: Int): Pair<Int, Int?> {
        if (root == null) return remaining to null
        val (leftRemaining, leftResult) = helper(root.left, remaining)
        if (leftResult != null) return leftRemaining to leftResult
        if (leftRemaining == 1) return 0 to root.`val`
        val (rightRemaining, rightResult) = helper(root.right, leftRemaining - 1)
        return rightRemaining to rightResult
    }
}
