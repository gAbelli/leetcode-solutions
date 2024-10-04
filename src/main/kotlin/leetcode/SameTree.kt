package leetcode

import leetcode.datastructures.TreeNode

class SameTree {
    fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
        if ((p == null) != (q == null)) return false
        return (p == null) || (p.`val` == q!!.`val` && isSameTree(p.left, q.left) && isSameTree(p.right, q.right))
    }
}
