package leetcode

import leetcode.datastructures.ListNode

class ReverseLinkedList {
    fun reverseList(head: ListNode?): ListNode? {
        var prev: ListNode? = null
        var cur: ListNode? = head
        var next: ListNode? = head

        while (cur != null) {
            next = cur.next
            cur.next = prev
            prev = cur
            cur = next
        }

        return prev
    }
}
