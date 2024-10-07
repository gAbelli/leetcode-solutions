package leetcode

class CloneGraph {

    class Node(var `val`: Int) {
        var neighbors: ArrayList<Node?> = ArrayList()
    }

    fun cloneGraph(node: Node?): Node? {
        val m = mutableMapOf<Node, Node>()

        fun alterEgo(node: Node): Node {
            if (node in m) return m[node]!!
            val newNode = Node(node.`val`)
            m[node] = newNode
            newNode.neighbors.addAll(node.neighbors.map { alterEgo(it!!) })
            return newNode
        }

        return if (node == null) null else alterEgo(node)
    }
}
