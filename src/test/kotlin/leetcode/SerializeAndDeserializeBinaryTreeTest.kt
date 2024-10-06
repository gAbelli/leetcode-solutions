package leetcode

import leetcode.datastructures.TreeNode
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SerializeAndDeserializeBinaryTreeTest {
    @Test
    fun testSerializeAndDeserializeBinaryTree() {
        val tree = TreeNode(-1).apply {
            left = TreeNode(2)
            right = TreeNode(3).apply {
                left = TreeNode(4)
                right = TreeNode(5)
            }
        }
        val codec = SerializeAndDeserializeBinaryTree()
        val serialized = codec.serialize(tree)
        val deserialized = codec.deserialize(serialized)
        assertEquals(-1, deserialized!!.`val`)
    }
}
