package leetcode

import leetcode.datastructures.TreeNode
import kotlin.math.max

class BinaryTreeMaximumPathSum {
    fun maxPathSum(root: TreeNode?): Int {
        var maxValue = root!!.`val`

        fun helper(root: TreeNode?): Pair</* with diramations */ Int, /* without diramations (containing root) */ Int> {
            if (root == null) return 0 to 0
            maxValue = max(maxValue, root.`val`)
            val (leftWithDiramations, leftWithoutDiramations) = helper(root.left)
            val (rightWithDiramations, rightWithoutDiramations) = helper(root.right)
            return maxOf(
                leftWithDiramations,
                rightWithDiramations,
                root.`val` + max(leftWithoutDiramations, 0) + max(rightWithoutDiramations, 0)
            ) to maxOf(root.`val`, root.`val` + leftWithoutDiramations, root.`val` + rightWithoutDiramations)
        }

        val result = helper(root).first
        return if (result <= 0) maxValue else result
    }
}
