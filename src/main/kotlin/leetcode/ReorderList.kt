package leetcode

import leetcode.datastructures.ListNode

class ReorderList {
    fun reorderList(head: ListNode?): Unit {
        if (head == null || head.next == null) return
        val predecessor = mutableMapOf<ListNode, ListNode?>()
        var last = head

        run {
            var prev: ListNode? = null
            var cur = head
            while (cur != null) {
                last = cur
                predecessor[cur] = prev
                prev = cur
                cur = cur.next
            }
        }

        var front = head
        var back = last

        while (true) {
            if (front == back) {
                front!!.next = null
                break
            }
            if (front!!.next == back) {
                back!!.next = null
                break
            }
            val backupFrontNext = front.next
            front.next = back
            front = backupFrontNext
            back!!.next = front
            back = predecessor[back]
        }
    }
}
