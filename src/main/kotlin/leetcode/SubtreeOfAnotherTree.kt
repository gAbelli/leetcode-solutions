package leetcode

import leetcode.datastructures.TreeNode
import java.util.*

class SubtreeOfAnotherTree {
    fun isSubtree(root: TreeNode?, subRoot: TreeNode?): Boolean {
        fun hash(node: TreeNode?): Int {
            if (node == null) return 0
            val leftHash = hash(node.left)
            val rightHash = hash(node.right)
            return Objects.hash(leftHash, rightHash, node.`val`)
        }

        val subRootHash = hash(subRoot)

        // returns a boolean value that indicates if tree starting at node = tree starting at subRoot
        // and the hash of the tree starting at node
        fun helper(node: TreeNode?): Pair<Boolean, Int> {
            if (node == null) return false to 0
            val (leftOk, leftHash) = helper(node.left)
            val (rightOk, rightHash) = helper(node.right)
            if (leftOk || rightOk) return true to 0
            val thisHash = Objects.hash(leftHash, rightHash, node.`val`)
            return if (thisHash == subRootHash) isSameTree(node, subRoot) to thisHash
            else false to thisHash
        }

        return helper(root).first
    }

    private fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
        if ((p == null) != (q == null)) return false
        return (p == null) || (p.`val` == q!!.`val` && isSameTree(p.left, q.left) && isSameTree(p.right, q.right))
    }
}
