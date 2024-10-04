package leetcode

import leetcode.datastructures.TreeNode

class BinaryTreeRightSideView {
    fun rightSideView(root: TreeNode?): List<Int> {
        val result = mutableListOf<Int>()

        if (root == null) return result

        var currentQueue = ArrayDeque<TreeNode>()
        var nextQueue = ArrayDeque<TreeNode>()

        nextQueue.add(root)
        while (true) {
            if (currentQueue.isEmpty()) {
                currentQueue = nextQueue
                nextQueue = ArrayDeque()
                result.add(0)
            }
            if (currentQueue.isEmpty()) break
            val node = currentQueue.removeFirst()
            result[result.indices.last] = node.`val`
            if (node.left != null) nextQueue.add(node.left!!)
            if (node.right != null) nextQueue.add(node.right!!)
        }

        result.removeLast()
        return result
    }
}
