package leetcode

import leetcode.datastructures.TreeNode

class InvertBinaryTree {
    fun invertTree(root: TreeNode?): TreeNode? {
        if (root == null) return null
        return TreeNode(root.`val`).apply {
            right = invertTree(root.left)
            left = invertTree(root.right)
        }
    }
}
