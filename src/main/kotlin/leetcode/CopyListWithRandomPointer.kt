package leetcode

class CopyListWithRandomPointer {
    class Node(var `val`: Int) {
        var next: Node? = null
        var random: Node? = null
    }

    fun copyRandomList(node: Node?): Node? {
        val m = mutableMapOf<Node, Node?>()

        fun getAssociatedNode(node: Node?): Node? {
            if (node == null) return null
            if (m.contains(node)) return m[node]
            val newNode = Node(node.`val`)
            m[node] = newNode
            newNode.next = getAssociatedNode(node.next)
            newNode.random = getAssociatedNode(node.random)
            return newNode
        }

        return getAssociatedNode(node)
    }
}
