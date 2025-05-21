package leetcode

import leetcode.datastructures.TreeNode
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SerializeAndDeserializeBstTest {
    @Test
    fun testSerializeAndDeserializeBst() {
        val root = TreeNode(1).apply {
            left = TreeNode(2)
        }
        val codec = SerializeAndDeserializeBst()
        val serialized = codec.serialize(root)
        val deserialized = codec.deserialize(serialized)
        assertEquals(1, deserialized?.`val`)
    }
}
