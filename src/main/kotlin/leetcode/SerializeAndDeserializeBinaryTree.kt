package leetcode

import leetcode.datastructures.TreeNode

class SerializeAndDeserializeBinaryTree {
    fun serialize(root: TreeNode?): String {
        if (root == null) return nullIndicator.toString()
        return root.`val`.toString() + separator + serialize(root.left) + separator + serialize(root.right)
    }

    fun deserialize(data: String): TreeNode? {
        fun helper(data: String): Pair<TreeNode?, Int> {
            if (data.first() == nullIndicator) return null to 1
            val firstSeparatorIndex = data.indexOf(separator)
            val value = data.substring(0..<firstSeparatorIndex).toInt()
            val (left, consumedLeft) = helper(data.substring(firstSeparatorIndex + 1))
            val secondSeparatorIndex = firstSeparatorIndex + 1 + consumedLeft
            val (right, consumedRight) = helper(data.substring(secondSeparatorIndex + 1))
            return TreeNode(value).apply {
                this.left = left
                this.right = right
            } to firstSeparatorIndex + 1 + consumedLeft + 1 + consumedRight
        }
        return helper(data).first
    }

    companion object {
        val separator = '#'
        val nullIndicator = '_'
    }
}
