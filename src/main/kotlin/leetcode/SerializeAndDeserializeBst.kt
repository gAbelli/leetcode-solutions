package leetcode

import leetcode.datastructures.TreeNode

class SerializeAndDeserializeBst {
    fun serialize(root: TreeNode?): String =
        if (root == null) "n"
        else "${root.`val`},${serialize(root.left)},${serialize(root.right)}"

    fun deserialize(data: String): TreeNode? {
        fun loop(i: Int): Pair<TreeNode?, Int> =
            if (data[i] == 'n') Pair(null, i)
            else {
                val commaIndex = data.indexOf(",", i)
                val rootVal = data.slice(i..<commaIndex).toInt()
                val (leftTree, lastLeftIndex) = loop(commaIndex + 1)
                val (rightTree, lastRightIndex) = loop(lastLeftIndex + 2)
                val node = TreeNode(rootVal).apply {
                    left = leftTree
                    right = rightTree
                }
                Pair(node, lastRightIndex)
            }

        return loop(0).first
    }
}
