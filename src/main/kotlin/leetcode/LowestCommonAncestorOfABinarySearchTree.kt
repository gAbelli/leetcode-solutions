package leetcode

import leetcode.datastructures.TreeNode

class LowestCommonAncestorOfABinarySearchTree {
    fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        if (p == q) return p

        // returns foundP, foundQ, lca
        fun helper(node: TreeNode?): Triple<Boolean, Boolean, TreeNode?> {
            if (node == null) return Triple(false, false, null)
            val (leftFoundP, leftFoundQ, leftLca) = helper(node.left)
            val (rightFoundP, rightFoundQ, rightLca) = helper(node.right)
            if (leftLca != null) return Triple(true, true, leftLca)
            if (rightLca != null) return Triple(true, true, rightLca)
            if (node == p && (leftFoundQ || rightFoundQ)) return Triple(true, true, node)
            if (node == q && (leftFoundP || rightFoundP)) return Triple(true, true, node)
            if ((leftFoundP && rightFoundQ) || (leftFoundQ && rightFoundP)) return Triple(true, true, node)
            return Triple(leftFoundP || rightFoundP || (node == p), leftFoundQ || rightFoundQ || (node == q), null)
        }

        return helper(root).third
    }
}
