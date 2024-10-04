package leetcode

class LRUCache(capacity: Int) {
    private class Node(val key: Int, var value: Int, var next: Node?, var prev: Node?)

    private var head: Node? = null
    private var tail: Node? = null
    private var keyToNode: MutableMap<Int, Node> = mutableMapOf()
    private val capacity = capacity

    fun get(key: Int): Int {
        val node = keyToNode[key] ?: return -1
        moveToHead(node)
        return node.value
    }

    fun put(key: Int, value: Int) {
        if (key in keyToNode) {
            val node = keyToNode[key]!!
            node.value = value
            moveToHead(node)
        } else {
            append(key, value)
        }
    }

    private fun moveToHead(node: Node) {
        if (head == tail || node == head) {
        } else if (node == tail) {
            tail!!.prev!!.next = null
            tail = tail!!.prev
            node.next = head
            node.prev = null
            head!!.prev = node
            head = node
        } else {
            node.next!!.prev = node.prev
            node.prev!!.next = node.next
            node.next = head
            node.prev = null
            head!!.prev = node
            head = node
        }
    }

    private fun append(key: Int, value: Int) {
        val node = Node(key, value, null, null)
        keyToNode[key] = node
        if (head == null) {
            head = node
            tail = node
        } else {
            node.next = head
            head!!.prev = node
            head = node
        }
        if (keyToNode.size > capacity) {
            popBack()
        }
    }

    private fun popBack() {
        if (head == tail) {
            if (head != null) {
                keyToNode.remove(head!!.key)
            }
            head = null
            tail = null
        } else {
            val newTail = tail!!.prev!!
            newTail.next = null
            keyToNode.remove(tail!!.key)
            tail = newTail
        }
    }
}
