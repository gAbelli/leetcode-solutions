package leetcode

import leetcode.datastructures.ListNode
import java.util.PriorityQueue

class MergeKSortedLists {
    fun mergeKLists(lists: Array<ListNode?>): ListNode? {
        val pq = PriorityQueue<Int>(compareBy { lists[it]!!.`val` })
        val dummy = ListNode(0)
        var cur = dummy

        lists.indices.forEach { if (lists[it] != null) pq.add(it) }
        while (pq.isNotEmpty()) {
            val index = pq.poll()
            cur.next = lists[index]
            cur = cur.next!!
            lists[index] = cur.next
            if (lists[index] != null) pq.add(index)
        }

        return dummy.next
    }
}
