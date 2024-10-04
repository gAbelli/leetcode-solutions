package leetcode

import leetcode.datastructures.TreeNode

class BinaryTreeLevelOrderTraversal {
    fun levelOrder(root: TreeNode?): List<List<Int>> {
        val result = mutableListOf<MutableList<Int>>()

        if (root == null) return result

        var currentQueue = ArrayDeque<TreeNode>()
        var nextQueue = ArrayDeque<TreeNode>()

        nextQueue.add(root)
        while (true) {
            if (currentQueue.isEmpty()) {
                currentQueue = nextQueue
                nextQueue = ArrayDeque()
                result.add(mutableListOf())
            }
            if (currentQueue.isEmpty()) break
            val node = currentQueue.removeFirst()
            result.last().add(node.`val`)
            if (node.left != null) nextQueue.add(node.left!!)
            if (node.right != null) nextQueue.add(node.right!!)
        }

        result.removeLast()
        return result
    }
}
