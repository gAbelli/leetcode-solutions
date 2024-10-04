package leetcode

import leetcode.datastructures.TreeNode

class ConstructBinaryTreeFromPreorderAndInorderTraversal {
    fun buildTree(preorder: IntArray, inorder: IntArray): TreeNode? {
        val inorderPos = mutableMapOf<Int, Int>()
        for (i in inorder.indices) inorderPos[inorder[i]] = i

        fun helper(preorderStart: Int, inorderStart: Int, length: Int): TreeNode? {
            if (length == 0) return null
            val rootValue = preorder[preorderStart]
            val leftLength = inorderPos[rootValue]!! - inorderStart
            return TreeNode(rootValue).apply {
                left = helper(preorderStart + 1, inorderStart, leftLength)
                right = helper(preorderStart + 1 + leftLength, inorderStart + 1 + leftLength, length - leftLength - 1)
            }
        }

        return helper(0, 0, preorder.size)
    }
}
